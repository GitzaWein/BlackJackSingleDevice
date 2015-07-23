package com.example.student.blackjacksingledevice1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Pessy on 7/20/2015.
 */
public class HitOrStickDialogBox extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity()).setTitle(  "Would you like to..").setMessage("...Hit or Stick?").setPositiveButton("Hit", pListener).setNegativeButton("Stick", nListener);
        return alertDialogBuilder.create();
    }
    public DialogInterface.OnClickListener pListener = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface arg0, int arg1){
            //when user clicks Hit this will happen
            GamePlayActivity activity = (GamePlayActivity) getActivity();
            activity.hitOrStick = true;
            activity.infinite = false;
        }
    };
    DialogInterface.OnClickListener nListener = new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface arg0, int arg1){
            //when user clicks Stick this will happen
            GamePlayActivity activity = (GamePlayActivity) getActivity();
            activity.hitOrStick = false;
            activity.infinite = false;
        }
    };
}
