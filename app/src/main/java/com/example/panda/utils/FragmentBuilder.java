package com.example.panda.utils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.panda.app.MyApp;
import com.example.panda.base.BaseFragment;

/**
 * Created by lenovo on 2017/8/23.
 */

public class FragmentBuilder {

    private static FragmentManager manager;
    private static String tagName;
    private static BaseFragment currentfragment;
    private static BaseFragment lastFragment;
    public static Fragment startFragment(Class<?extends BaseFragment>  fragment, int containerId, Bundle params, boolean isBack, boolean isHidden){
        manager = MyApp.activity.getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        tagName = fragment.getSimpleName();
        currentfragment = (BaseFragment) manager.findFragmentByTag(tagName);
        if (currentfragment == null) {
            try {
                currentfragment=fragment.newInstance();
                transaction.add(containerId,currentfragment,tagName);

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(isHidden) {
            //隐藏上一个fragment
            if (lastFragment != null)
                transaction.hide(lastFragment);
            //显示当前fragment
            transaction.show(currentfragment);
        }else {
            transaction.replace(containerId,currentfragment,tagName);
        }
        //传递参数
        if(params != null)
            //currentfragment.setParams(params);
        currentfragment.setArguments(params);
        //是否添加到回退栈
        if(isBack){
            transaction.addToBackStack(tagName);
        }

        transaction.commit();
        lastFragment = currentfragment;
        return currentfragment;
    }
}
