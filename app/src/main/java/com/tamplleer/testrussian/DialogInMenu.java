package com.tamplleer.testrussian;


        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.util.Log;
        import android.widget.Toast;
        import android.view.View;

/**
 * Created by Den on 3/20/2017.
 */

public class DialogInMenu {
    AlertDialog.Builder ad;
    Context context;
    int type;


    public DialogInMenu(MainActivity main){

        context = main;

        //"confirm exit";


        ad = new AlertDialog.Builder(context);
        ad.setTitle(selectTitle(type));  // заголовок
        ad.setMessage(selectMessage(type)); // сообщение
        ad.setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }
        });

        ad.setCancelable(true);

        ad.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialog) {

            }
        });
    }

    public int selectTitle(int type){
        switch (type) {
            case 1:
                return
                        R.string.GOVER;
            case 2:
                return R.string.TRAG;
            default:
                return R.string.instruct;


        }
    }
    public int selectMessage(int type){
        switch (type){
            case 1:
                return R.string.GOVER;
            case 2:
                return R.string.GOVER;
            default:
                return R.string.vopros;
        }}





    }
