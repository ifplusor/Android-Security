package com.JY.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.JY.UI.R;
import com.JY.UI.StartActivity;
import com.JY.packageInfo.AppInfo;
import com.JY.packageInfo.BrowseTrafficAdapter;
import com.JY.utils.DataTraffic;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class FlowActivity extends Activity {
	private long m_exitTime = 0;
	private PackageManager pm;
//	public List<AppInfo> mlistAppInfo;
	private ListView listview = null;
	private BrowseTrafficAdapter browseTrafficAdapter = null;
	private LinearLayout main_layout, first_layout, second_layout,
			third_layout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.flow);
		setupViewComponet();
		
//		mlistAppInfo = queryFilterAppInfo();
		browseTrafficAdapter = new BrowseTrafficAdapter(FlowActivity.this,
				StartActivity.static_listAppInfo);
		listview.setAdapter(browseTrafficAdapter);

		handler.post(option);
	}

	Handler handler = new Handler();
	Runnable option = new Runnable() {

		@Override
		public void run() {
			 //TODO Auto-generated method stub
			int size = StartActivity.static_listAppInfo.size();
			for (int i = 0; i < size; i++) {
				StartActivity.static_listAppInfo.get(i).setCurrentUpTraffic(DataTraffic.getUidTotalSendKB(StartActivity.static_listAppInfo.get(i).getUid()));
				StartActivity.static_listAppInfo.get(i).setIngUpTraffic(StartActivity.static_listAppInfo.get(i).getCurrentUpTraffic() - StartActivity.static_listAppInfo.get(i).getLastUpTraffic());
				StartActivity.static_listAppInfo.get(i).setLastUpTraffic(StartActivity.static_listAppInfo.get(i).getCurrentUpTraffic());

				StartActivity.static_listAppInfo.get(i).setCurrentDownTraffic(DataTraffic.getUidTotalRecvKB(StartActivity.static_listAppInfo.get(i).getUid()));
				StartActivity.static_listAppInfo.get(i).setIngDownTraffic(StartActivity.static_listAppInfo.get(i).getCurrentDownTraffic() - StartActivity.static_listAppInfo.get(i).getLastDownTraffic());
				StartActivity.static_listAppInfo.get(i).setLastDownTraffic(StartActivity.static_listAppInfo.get(i).getCurrentDownTraffic());
			}
			handler.postDelayed(option, 1000);
			browseTrafficAdapter.notifyDataSetChanged();
		}
	};

	private void setupViewComponet() {
		Intent intent = getIntent();
		boolean clickble = intent.getBooleanExtra("clickble", true);

		main_layout = (LinearLayout) findViewById(R.id.main_layout_ly);
		main_layout.setOnClickListener(clickListener_main);

		first_layout = (LinearLayout) findViewById(R.id.first_layout_ly);
		first_layout.setSelected(clickble);

		second_layout = (LinearLayout) findViewById(R.id.second_layout_ly);
		second_layout.setOnClickListener(clickListener_second);

		third_layout = (LinearLayout) findViewById(R.id.third_layout_ly);
		third_layout.setOnClickListener(clickListener_third);

		listview = (ListView) findViewById(R.id.TrafficListviewApp);
	}

	// @Override
	// protected void onStop() {
	// // TODO Auto-generated method stub
	// super.onStop();
	// this.finish();
	// }

	private OnClickListener clickListener_second = new OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			main_layout.setSelected(false);
			first_layout.setSelected(false);
			second_layout.setSelected(true);
			third_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(FlowActivity.this, AnalysisActivity.class);
			intent.putExtra("clickble", true);
			FlowActivity.this.finish();
			startActivity(intent);
			first_layout.setSelected(false);
		}
	};
	private OnClickListener clickListener_main = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			main_layout.setSelected(true);
			first_layout.setSelected(false);
			second_layout.setSelected(false);
			third_layout.setSelected(false);
			Intent intent = new Intent();
			intent.setClass(FlowActivity.this, PermissionActivity.class);
			intent.putExtra("clickble", true);
			FlowActivity.this.finish();
			startActivity(intent);
			second_layout.setSelected(false);
		}
	};
	private OnClickListener clickListener_third = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			main_layout.setSelected(false);
			first_layout.setSelected(false);
			second_layout.setSelected(false);
			third_layout.setSelected(true);
			Intent intent = new Intent();
			intent.setClass(FlowActivity.this, SettingActivity.class);
			intent.putExtra("clickble", true);
			FlowActivity.this.finish();
			startActivity(intent);
			third_layout.setSelected(false);
		}
	};

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - m_exitTime) > 2000) { // System.currentTimeMillis()无论何时调用，肯定大于2000
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				m_exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	// 根据查询条件，查询特定的ApplicationInfo
	public List<AppInfo> queryFilterAppInfo() {
		pm = this.getPackageManager();
		// 查询所有已经安装的应用程序
		List<ApplicationInfo> listAppcations = pm
				.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
		Collections.sort(listAppcations,
				new ApplicationInfo.DisplayNameComparator(pm));// 排序
		List<AppInfo> appInfos = new ArrayList<AppInfo>();

		appInfos.clear();
		for (ApplicationInfo app : listAppcations) {
			// 非系统程序
			if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				appInfos.add(getAppInfo(app));
			}
			// 本来是系统程序，被用户手动更新后，该系统程序也成为第三方应用程序了
			else if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
				appInfos.add(getAppInfo(app));
			}
		}
		return appInfos;
	}

	// 构造一个AppInfo对象 ，并赋值
	public AppInfo getAppInfo(ApplicationInfo app) {
		AppInfo appInfo = new AppInfo();
		appInfo.setAppLabel((String) app.loadLabel(pm));
		appInfo.setAppIcon(app.loadIcon(pm));
		appInfo.setPkgName(app.packageName);
		appInfo.setUid(app.uid);
		return appInfo;
	}
}
