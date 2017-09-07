package com.example.panda.view.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.panda.R;

public class AboutPandaActivity extends AppCompatActivity {

    private TextView common_title_left;
    private TextView versionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_panda);
        initView();
        initListener();
    }

    private void initListener() {
        common_title_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return getString(R.string.app_name) + " V" + version;
        } catch (Exception e) {
            e.printStackTrace();
            return getString(R.string.have_no_version_name);
        }
    }

    private void initView() {
        common_title_left = (TextView) findViewById(R.id.common_title_left);
        versionName = (TextView) findViewById(R.id.about_version);
        versionName.setText(getVersion());
    }
}
