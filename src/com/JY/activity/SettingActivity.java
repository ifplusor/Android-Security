package com.JY.activity;

import com.JY.UI.R;
import android.app.ActivityManager;
import android.content.Context;
import android.widget.*;

import com.JY.service.JYservice;
import com.JY.utils.HttpDownloader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.ArrayList;


public class SettingActivity extends Activity {
    private long m_exitTime = 0;
    private LinearLayout permission_layout, traffic_layout, analysis_layout,
            setting_layout;
    private Switch mSwitch;
    private Context mcontext;
    private LinearLayout downloadLineralayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setting);
        setupViewComponet();
        
        downloadLineralayout = (LinearLayout)findViewById(R.id.downloadLineralayout);
        downloadLineralayout.setOnClickListener(downloadListener);

        mSwitch = (Switch) findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(switchCheckedChangeListener);
        mSwitch.setChecked(serviceIsWorking());
    }
    private OnClickListener downloadListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println("求toast");
            HttpDownloader httpDownloader = new HttpDownloader();
            int result = httpDownloader.downFile("http://192.168.1.100/signature/signature.db", "FTDsignature/", "signature.db");
            System.out.println(result);
            if(result == 0)
            Toast.makeText(SettingActivity.this,"数据库更新T成功",Toast.LENGTH_SHORT).show();
        }
    };


    public void onFeedbackItemClick(View v){
        //建立Intent 对象
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        //设置文本格式
        emailIntent.setType("plain/text");
        //设置对方邮件地址
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"616205539@qq.com"});
        //emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"97321776@qq.com"});
        //设置标题内容
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "用户反馈");
        //设置邮件文本内容
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        //启动一个新的ACTIVITY,"Sending mail..."是在启动这个ACTIVITY的等待时间时所显示的文字
        startActivity(Intent.createChooser(emailIntent, "发送用户反馈..."));
    }

    public void onAboutBtnClick(View v) {
        this.startActivity(new Intent(this, AboutPage.class));
    }

    private OnCheckedChangeListener switchCheckedChangeListener = new OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            // TODO Auto-generated method stub
            if (isChecked) {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, JYservice.class);
                startService(intent);
                System.out.println("打开----------------");
            } else {
                Intent intent = new Intent();
                intent.setClass(SettingActivity.this, JYservice.class);
                stopService(intent);
                System.out.println("关闭----------------");
            }
        }
    };

    private OnClickListener startServiceListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, JYservice.class);
            startService(intent);
        }
    };

    private OnClickListener stopServiceListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, JYservice.class);
            stopService(intent);
        }
    };

    private void setupViewComponet() {
        Intent intent = getIntent();
        boolean clickble = intent.getBooleanExtra("clickble", true);

        permission_layout = (LinearLayout) findViewById(R.id.permission_layout_ly);
        permission_layout.setOnClickListener(clickListener_permission);

        traffic_layout = (LinearLayout) findViewById(R.id.traffic_layout_ly);
        traffic_layout.setOnClickListener(clickListener_triffic);

        analysis_layout = (LinearLayout) findViewById(R.id.analysis_layout_ly);
        analysis_layout.setOnClickListener(clickListener_analysis);

        setting_layout = (LinearLayout) findViewById(R.id.setting_layout_ly);
        setting_layout.setSelected(clickble);

        mcontext = this.getBaseContext();
    }

    // @Override
    // protected void onStop() {
    // // TODO Auto-generated method stub
    // super.onStop();
    // this.finish();
    // }

    private OnClickListener clickListener_triffic = new OnClickListener() {
        @Override
        public void onClick(View v) {
            permission_layout.setSelected(false);
            traffic_layout.setSelected(true);
            analysis_layout.setSelected(false);
            setting_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, TrafficActivity.class);
            intent.putExtra("clickble", true);
            SettingActivity.this.finish();
            startActivity(intent);
            permission_layout.setSelected(false);
        }
    };
    private OnClickListener clickListener_analysis = new OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            permission_layout.setSelected(false);
            traffic_layout.setSelected(false);
            analysis_layout.setSelected(true);
            setting_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, AnalysisActivity.class);
            intent.putExtra("clickble", true);
            SettingActivity.this.finish();
            startActivity(intent);
            traffic_layout.setSelected(false);
        }
    };
    private OnClickListener clickListener_permission = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            permission_layout.setSelected(true);
            traffic_layout.setSelected(false);
            analysis_layout.setSelected(false);
            setting_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(SettingActivity.this, PermissionActivity.class);
            intent.putExtra("clickble", true);
            SettingActivity.this.finish();
            startActivity(intent);
            analysis_layout.setSelected(false);
        }
    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - m_exitTime) > 2000) { // System.currentTimeMillis()无论何时调用，肯定大于2000
                Toast.makeText(SettingActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                m_exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    //本方法判断自己些的一个Service-->com.FTD.service.FTDservice是否已经运行
    public boolean serviceIsWorking()
    {
        ActivityManager myManager=(ActivityManager)mcontext.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(30);
        for(int i = 0 ; i<runningService.size();i++)
        {
            if(runningService.get(i).service.getClassName().toString().equals("com.FTD.service.FTDservice"))
            {
                return true;
            }
        }
        return false;
    }


}
