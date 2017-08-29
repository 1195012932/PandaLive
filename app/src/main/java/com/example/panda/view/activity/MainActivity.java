package com.example.panda.view.activity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.panda.R;
import com.example.panda.base.BaseActivity;
import com.example.panda.utils.FragmentBuilder;
import com.example.panda.utils.Netwoke;
import com.example.panda.view.fragment.BroadcastFragment;
import com.example.panda.view.fragment.ChinaFragment;
import com.example.panda.view.fragment.HomeFragment;
import com.example.panda.view.fragment.LiveFragment;
import com.example.panda.view.fragment.VideoFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout frame;
    private RadioButton rb_home;
    private RadioButton rb_live;
    private RadioButton rb_video;
    private RadioButton rb_broad;
    private RadioButton rb_china;
    private Netwoke netwoke;

    //方清正
    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {
        rb_home.setOnClickListener(this);
        rb_live.setOnClickListener(this);
        rb_video.setOnClickListener(this);
        rb_broad.setOnClickListener(this);
        rb_china.setOnClickListener(this);
        select(0);
    }

    private void select(int i) {
        switch (i) {
            case 0:
                FragmentBuilder.startFragment(HomeFragment.class, R.id.frame, null, true, true);
                break;
            case 1:
                FragmentBuilder.startFragment(LiveFragment.class, R.id.frame, null, true, true);
                break;
            case 2:
                FragmentBuilder.startFragment(VideoFragment.class, R.id.frame, null, true, true);
                break;
            case 3:
                FragmentBuilder.startFragment(BroadcastFragment.class, R.id.frame, null, true, true);
                break;
            case 4:
                FragmentBuilder.startFragment(ChinaFragment.class, R.id.frame, null, true, true);


        }
    }

    @Override
    protected void initData() {
        getnetwoke();
    }

    @Override
    protected void initView() {
        frame = (FrameLayout) findViewById(R.id.frame);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_live = (RadioButton) findViewById(R.id.rb_live);
        rb_video = (RadioButton) findViewById(R.id.rb_video);
        rb_broad = (RadioButton) findViewById(R.id.rb_broad);
        rb_china = (RadioButton) findViewById(R.id.rb_china);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                select(0);
                break;
            case R.id.rb_live:
                select(1);
                break;
            case R.id.rb_video:
                select(2);
                break;
            case R.id.rb_broad:
                select(3);
                break;
            case R.id.rb_china:
                select(4);
                break;
        }
    }
    private long firstTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(MainActivity.this, "连续点击两次退出应用!", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    //判断网络状态
    private void getnetwoke() {

        if (netwoke == null) {
            netwoke = new Netwoke();
        }

        String getnetwoke = netwoke.getnetwoke(MainActivity.this);

        Toast.makeText(MainActivity.this, getnetwoke, Toast.LENGTH_SHORT).show();

        if (getnetwoke.equals("网络无连接")) {
            setNetwork();
        }
    }

    private void setNetwork() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(R.mipmap.ic_launcher);

        builder.setTitle("无法连接网络");

        builder.setMessage("网络不可用，如果继续，请先设置网络！");

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {

                Intent intent = null;

                /**

                 * 判断手机系统的版本！如果API大于10 就是3.0+

                 * 因为3.0以上的版本的设置和3.0以下的设置不一样，调用的方法不同

                 */

                if (Build.VERSION.SDK_INT > 10) {

                    intent = new Intent(Settings.ACTION_WIFI_SETTINGS);

                } else {

                    intent = new Intent();

                    ComponentName component = new ComponentName(

                            "com.android.settings",

                            "com.android.settings.WirelessSettings");

                    intent.setComponent(component);

                    intent.setAction("android.intent.action.VIEW");

                }

                startActivity(intent);

            }

        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int which) {


            }

        });

        builder.create();

        builder.show();
    }
}
