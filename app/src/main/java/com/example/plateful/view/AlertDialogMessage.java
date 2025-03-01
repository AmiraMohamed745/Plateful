package com.example.plateful.view;

import android.content.Context;
import androidx.appcompat.app.AlertDialog;

public class AlertDialogMessage {

    public static void makeAlertDialog(String errorMessage, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(errorMessage).setTitle("An error occurred");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
