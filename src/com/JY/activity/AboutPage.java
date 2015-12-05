package com.JY.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import com.JY.UI.R;

/**
 * Created by tantan on 13-5-17.
 */
public class AboutPage extends Activity {
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.about);

        mButton = (Button)findViewById(R.id.item_back_about);
        mButton.setOnClickListener(backListener);
    }

    private View.OnClickListener backListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            AboutPage.this.finish();
        }
    };
}
