package com.dorukkangal.geoalert.util

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import androidx.appcompat.app.AlertDialog

class DialogHelper(private val context: Context) {

    private var progressDialog: Dialog? = null

    fun showError(message: String?) {
        var alertDialog: AlertDialog? = null

        val dismiss = {
            alertDialog?.dismiss()
        }

        alertDialog = AlertDialog.Builder(context)
            .setTitle(android.R.string.dialog_alert_title)
            .setMessage(message ?: "Unknown error")
            .setPositiveButton(android.R.string.ok) { _, _ ->
                dismiss.invoke()
            }
            .show()
    }

    fun showLoadingDialog() {
        progressDialog = ProgressDialog.show(
            context,
            "Loading",
            "Wait while loading..."
        )
    }

    fun dismissLoadingDialog() {
        progressDialog?.dismiss()
    }
}
