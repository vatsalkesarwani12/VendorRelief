package com.CodeNaroNa.vendor.relief.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.CodeNaroNa.vendor.relief.R;

public class DialogLayout extends AppCompatDialogFragment {

    private EditText editTextOtp;
    private OtpDialogInterface listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout,null);

        builder.setView(view)
                .setTitle("Enter OTP")
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Verify", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.verify(editTextOtp.getText().toString());
                    }
                });

        editTextOtp = view.findViewById(R.id.otp_editText);

        
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OtpDialogInterface) context;
        } catch (Exception e) {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT);
        }
    }


    public interface OtpDialogInterface{
        void verify(String otpEdit);
    }

}


