package com.example.panda.view.home;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.panda.model.entity.home.KanDianDao;
import com.example.panda.model.live.bean.DaoMaster;
import com.example.panda.model.live.bean.DaoSession;


/**
 * Created by admin on 2017/8/31.
 */

public class KanDianUtils {
    private static KanDianUtils utils;
    private DaoSession daoSession;
    private KanDianDao kandianDao;

    public static KanDianUtils ss(){
        if(utils==null) {
            utils = new KanDianUtils();

        }
        return utils;
    }
    public KanDianDao look(Context context){

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "new", null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        daoSession = mDaoMaster.newSession();
        kandianDao = daoSession.getKanDianDao();
        return kandianDao;
    }
}


