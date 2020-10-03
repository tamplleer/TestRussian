package com.tamplleer.testrussian.activities.database.components;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Handler;
import android.util.Pair;

import com.tamplleer.testrussian.R;
import com.tamplleer.testrussian.activities.database.components.fragments.Edit_data_base;
import com.tamplleer.testrussian.activities.database.components.fragments.Fragment_data_base;
import com.tamplleer.testrussian.data.DataBaseWords;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WordFragmentManager implements ICallBackFragment {
    private Fragment_data_base myFragment;
    private FragmentTransaction fragmentTransaction;
    private Map<UUID, Fragment_data_base> fragmentMap;
    private Edit_data_base editDataBaseWord;
    // private ArrayList<Fragment_data_base> listFragment = new ArrayList<>();
    private Context context;
    Handler handler = new Handler();
    DataBaseWords dataBaseWords;
    private androidx.fragment.app.FragmentManager fragmentManagerSupport;

    public WordFragmentManager(Context context, DataBaseWords dataBaseWords, androidx.fragment.app.FragmentManager fragmentManagerSupport) {
        this.context = context;
        myFragment = new Fragment_data_base();
        fragmentMap = new HashMap<>();
        this.dataBaseWords = dataBaseWords;
        editDataBaseWord = new Edit_data_base();
        this.fragmentManagerSupport = fragmentManagerSupport;

    }

    public void createFragments(String type) {


        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        makeEmptyListFragment(fragmentMap);
        dataBaseWords.startRead(type, false);

        while (dataBaseWords.isFinishDataBase()) {

            myFragment = new Fragment_data_base();
            //   listFragment.add(myFragment);
            UUID key = UUID.randomUUID();
            fragmentMap.put(key, myFragment);
            myFragment.setParams(dataBaseWords.readFromDataBaseWord(), type, this, key);
            fragmentTransaction.add(R.id.container, myFragment);

        }
        dataBaseWords.closeDataBase();
        fragmentTransaction.commit();


    }

    private void makeEmptyListFragment(Map fragmentMap) {
        if (!fragmentMap.isEmpty()) {


            fragmentMap.forEach((k, v) -> {
                fragmentTransaction.remove((Fragment) fragmentMap.get(k));
            });

      /*      for (HashMap.Entry<UUID, Fragment_data_base> map : fragmentMap.entrySet()) {

            }*/

            fragmentMap.clear();
        }

    }

    /*    public String testB(DataBaseWords databaseWords, String type) {
            Thread thread = new Thread(() -> {
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                makeEmptyListFragment(listFragment);
                databaseWords.startRead(type);
                for (int i = 0; i < 150; i++) {
                    databaseWords.isFinishDataBase();
                    myFragment = new Fragment_data_base();
                    listFragment.add(myFragment);
                    myFragment.setParams(databaseWords.readFromDataBaseAllWord(type), databaseWords, type, this);
                    fragmentTransaction.add(R.id.container, myFragment);
                }


            });
            thread.start();
            if (thread.isInterrupted()) {
                databaseWords.closeDataBase();
                fragmentTransaction.commit();
            }

            return "cat";
        }*/
    public void addWord(String word, String type) {
        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        myFragment = new Fragment_data_base();
        UUID key = UUID.randomUUID();
        fragmentMap.put(key, myFragment);
        myFragment.setParams(new Pair<>(word, true), type, this, key);
        fragmentTransaction.add(R.id.container, myFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void execute(String message, String word, String type, UUID id) {
        FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        switch (message) {
            case "delete": {
                fragmentTransaction.remove(fragmentMap.get(id));
                dataBaseWords.deleteElem(word, type);
                fragmentTransaction.commit();
            }
            break;
            case "enableTrue": {
                dataBaseWords.updateEnable(word, type, true);
            }
            break;
            case "enableFalse": {
                dataBaseWords.updateEnable(word, type, false);
            }
            break;
            case "edit": {
                editDataBaseWord.setParams(word, this, type, id);
                editDataBaseWord.show(fragmentManagerSupport, "dialog");
            }
            break;
            default: {
                dataBaseWords.updateWord(word, type, message);
                fragmentMap.get(id).setText(word);

            }

        }

    }

}

