package com.CodeNaroNa.vendor.relief.activityKotlin

import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.CodeNaroNa.vendor.relief.R
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    private lateinit var progressbarDialog: ProgressDialog


    fun showProgressDialog(message: String) {
        progressbarDialog = ProgressDialog(this)
        progressbarDialog.setMessage(message)
        progressbarDialog.setCanceledOnTouchOutside(false)
        progressbarDialog.setCancelable(false)
        progressbarDialog.show()
    }

    fun hideProgressDialog() {
        if (this::progressbarDialog.isInitialized)
            progressbarDialog.dismiss()
    }

    fun closeKeyBoard(view: View?) {
        if (view != null) {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }

    }

    fun showErrorSnackMessage(message: String) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        snackBarView.setBackgroundColor(
                ContextCompat.getColor(this,
                        R.color.red_700
                ))
        snackBar.show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}