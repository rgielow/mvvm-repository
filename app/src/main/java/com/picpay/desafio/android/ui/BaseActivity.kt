package com.picpay.desafio.android.ui

import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.model.Status
import com.picpay.desafio.android.extensions.gone
import com.picpay.desafio.android.extensions.visible
import com.picpay.desafio.android.utils.Resource

open class BaseActivity : AppCompatActivity() {

    protected fun handleRequestStatusChange(requestStatus: Resource<Any>?, loader: ProgressBar) {
        requestStatus?.let {
            when (requestStatus.status) {
                Status.LOADING -> {
                    showLoader(loader)
                }
                Status.ERROR -> {
                    hideLoader(loader)
                    Toast.makeText(this, requestStatus.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    hideLoader(loader)
                }
            }
        }
    }

    private fun showLoader(loader: ProgressBar) {
        loader.visible()
    }

    private fun hideLoader(loader: ProgressBar) {
        loader.gone()
    }
}