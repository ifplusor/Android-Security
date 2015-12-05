package com.JY.activity;

import android.content.Intent;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.JY.UI.R;

public class Action extends Activity {
    public static final String TAG = "ServiceTimer";
    private Button passButton;
    private Button notFound_cancelButton;
    private Button notFound_confirmButton;
    private Button wrong_unloadButton;
    private Button wrong_cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        Log.v(TAG, "ACTION------------");
        Bundle bundle = getIntent().getExtras();
        int result = bundle.getInt("result");
        takeActions(result);

    }

    public void takeActions(int result) {
        switch (result) {
            case 0:
                // 签名不正确
                setContentView(R.layout.action_wrong);
//                wrong_cancelButton = (Button) findViewById(R.id.action_wrong_cancel);
//                wrong_cancelButton.setOnClickListener(cancelOnClickListener);
                wrong_unloadButton = (Button) findViewById(R.id.action_wrong_unload);
                wrong_unloadButton.setOnClickListener(wrong_unloadOnClickListener);
                break;
            case 1:
                // 签名验证通过
                setContentView(R.layout.action_pass);
                passButton = (Button) findViewById(R.id.action_pass);
                passButton.setOnClickListener(cancelOnClickListener);
                break;
            case -1:
                // 没有查询到
                setContentView(R.layout.action_notfound);
                notFound_cancelButton = (Button) findViewById(R.id.action_notpass_cancel);
                notFound_cancelButton.setOnClickListener(cancelOnClickListener);
                notFound_confirmButton = (Button) findViewById(R.id.action_notpass_confirm);
                notFound_confirmButton.setOnClickListener(notFound_confirmOnClickListener);
                break;

            default:
                break;
        }
    }

    private OnClickListener cancelOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Action.this.finish();
        }
    };
    private OnClickListener notFound_confirmOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent intent = new Intent();
            intent.setClass(Action.this, SettingActivity.class);
            startActivity(intent);
        }
    };
    private OnClickListener wrong_unloadOnClickListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Action.this.finish();
        }
    };
}
