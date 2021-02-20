package com.example.daggerkotlin.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class BaseActivity<D : ViewDataBinding, V : ViewModel> : DaggerAppCompatActivity() {


    @LayoutRes
    protected abstract fun layoutRes(): Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    protected lateinit var dataBinding: D
    protected lateinit var viewModel: V

    protected abstract fun getViewModel(): Class<V>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, layoutRes())
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())

    }


}