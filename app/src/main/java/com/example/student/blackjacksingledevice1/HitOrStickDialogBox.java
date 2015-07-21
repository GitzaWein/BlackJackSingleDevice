package com.example.student.blackjacksingledevice1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by student on 7/20/2015.
 */
public class HitOrStickDialogBox extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity()).setTitle("Would you like to..").setMessage("...Hit or Stick?").setPositiveButton("Hit",hListener).setNegativeButton("Stick", sListener);
        return alertDialogBuilder.create();
    }
    DialogInterface.OnClickListener hListener = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface arg0, int arg1){
            //when user clicks Hit this will happen
        }
    };
    DialogInterface.OnClickListener sListener = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface arg0, int arg1){
            //when user clicks Stick this will happen
        }
    };
}
