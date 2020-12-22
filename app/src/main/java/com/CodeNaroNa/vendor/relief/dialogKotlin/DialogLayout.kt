package com.CodeNaroNa.vendor.relief.dialogKotlin

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialogFragment
import com.CodeNaroNa.vendor.relief.databinding.DialogLayoutBinding

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class DialogLayout(private val listener: OtpDialogInterface) : AppCompatDialogFragment() {

    interface OtpDialogInterface {
        fun verify(otpEdit: String?)
    }

    private var _binding: DialogLayoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogLayoutBinding.inflate(requireActivity().layoutInflater)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)
                .setCancelable(false)
                .setTitle("Enter OTP")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Verify") { dialog, which -> listener.verify(binding.otpEditText.text.toString()) }

        val dialog: Dialog = builder.create()
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}