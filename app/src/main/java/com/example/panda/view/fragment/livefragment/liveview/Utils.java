package com.example.panda.view.fragment.livefragment.liveview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.panda.model.entity.home.DaoMaster;
import com.example.panda.model.entity.home.DaoSession;
import com.example.panda.model.live.bean.LookBeanDao;

/**
 * Created by admin on 2017/8/29.
 */

public class Utils {
    private static Utils utils;
    private DaoSession daoSession;
    private LookBeanDao lookDao;

    public static Utils ss(){
        if(utils==null) {
            utils = new Utils();

        }
        return utils;
    }
    public LookBeanDao look(Context context){

        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context.getApplicationContext(), "db", null);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        DaoMaster mDaoMaster = new DaoMaster(db);
        daoSession = mDaoMaster.newSession();
        lookDao = daoSession.getLookBeanDao();
        return lookDao;
    }
}
