package com.smsrn.movieshowcase.util

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.smsrn.movieshowcase.R


object DialogUtils {
    private var progressDialog: AlertDialog? = null
    /*
     * Comment:
     * This is replacement of Alert Dialog
     * */
    private fun progressDialog(context: Context): AlertDialog {
        val inflater = LayoutInflater.from(context)
        val alertLayout = inflater.inflate(R.layout.progress_dialog, null)
        val alertDialog = AlertDialog.Builder(context, R.style.alertDialogStyle)
            .setView(alertLayout)
            .setCancelable(false)
        return alertDialog.create()
    }

    fun showProgressDialog(context: Context) {
        try {
            progressDialog = progressDialog(context)
            progressDialog?.setCancelable(false)
            progressDialog?.show()
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog?.dismiss()
                progressDialog?.cancel()
            }
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
