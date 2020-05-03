package com.tamplleer.testrussian.utils;


        import android.app.AlertDialog;
        import android.content.Context;

        import com.tamplleer.testrussian.R;
        import com.tamplleer.testrussian.activities.result.EndGame;

/**
 * Created by Den on 3/20/2017.
 */

public class DialogInMenu {
    public AlertDialog.Builder ad;
    EndGame endgame;



    public DialogInMenu(Context context,int type){

        //"confirm exit";
        endgame = new EndGame();
        ad = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
        ad.setTitle(selectTitle(type));// заголовок
        ad.setMessage(selectMessage(type)); // сообщение
        if (type == 1){
        ad.setNegativeButton(R.string.open, (dialog, arg1) -> endgame.loadAd());}
        ad.setPositiveButton(R.string.close, (dialog, arg1) -> dialog.cancel());

        ad.setCancelable(true);

        ad.setOnCancelListener(dialog -> {

        });
    }

    public int selectTitle(int type){
        switch (type) {
            case 1:
                return R.string.deletADS;
            case 2:
                return R.string.TRAG;
            default:
                return R.string.instruct;


        }
    }

    public int selectMessage(int type){
        switch (type){
            case 1:
                return R.string.deletADStext;
            case 2:
                return R.string.GOVER;
            default:
                return R.string.vopros;
        }}





    }
