package com.example.daggerkotlin.cache;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;
import android.util.MalformedJsonException;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;

public abstract class NetworkBoundResource<T, V> {

    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    protected NetworkBoundResource() {
        result.setValue((Resource<T>) Resource.loading(null));

        // Always load the data from DB intially so that we have
        final LiveData<T> dbSource = loadFromDb();

        // Fetch the data from network and add it to the resource
        result.addSource(dbSource, new Observer<T>() {
            @Override
            public void onChanged(T data) {
                result.removeSource(dbSource);
                if (NetworkBoundResource.this.shouldFetch()) {
                    NetworkBoundResource.this.fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, new Observer<T>() {
                        @Override
                        public void onChanged(T newData) {
                            if (null != newData)
                                result.setValue(Resource.success(newData));
                        }
                    });
                }
            }
        });
    }

    /**
     * This method fetches the data from remoted service and save it to local db
     *
     * @param dbSource - Database source
     */
    private void fetchFromNetwork(final LiveData<T> dbSource) {
        result.addSource(dbSource, new Observer<T>() {
            @Override
            public void onChanged(T newData) {
                result.setValue(Resource.loading(newData));
            }
        });
        createCall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<V>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(V v) {
                        result.removeSource(dbSource);
                        saveResultAndReInit(v);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        result.removeSource(dbSource);
                        result.addSource(dbSource, new Observer<T>() {
                            @Override
                            public void onChanged(T newData) {
                                result.setValue(Resource.error(getCustomErrorMessage(e), newData));
                            }
                        });
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private String getCustomErrorMessage(Throwable error) {

        if (error instanceof SocketTimeoutException) {
            return "Time Error";
        } else if (error instanceof MalformedJsonException) {
            return "responseMalformedJson";
        } else if (error instanceof IOException) {
            return "networkError";
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return "unknownError";
        }

    }

    @SuppressLint("StaticFieldLeak")
    @MainThread
    private void saveResultAndReInit(final V response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                saveCallResult(response);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                result.addSource(loadFromDb(), new Observer<T>() {
                    @Override
                    public void onChanged(T newData) {
                        if (null != newData)
                            result.setValue(Resource.success(newData));
                    }
                });
            }
        }.execute();
    }

    @WorkerThread
    protected abstract void saveCallResult(V item);

    @MainThread
    private boolean shouldFetch() {
        return true;
    }

    @NonNull
    @MainThread
    protected abstract LiveData<T> loadFromDb();

    @NonNull
    @MainThread
    protected abstract Observable<V> createCall();

    public final LiveData<Resource<T>> getAsLiveData() {
        return result;
    }


}
