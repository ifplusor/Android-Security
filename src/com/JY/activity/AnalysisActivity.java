package com.JY.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.JY.SQLite.PermissionRecordDAO;
import com.JY.UI.StartActivity;
import com.JY.packageInfo.AppInfo;
import com.JY.packageInfo.BrowseApplicationInfoAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.JY.UI.R;

public class AnalysisActivity extends Activity implements OnItemClickListener {
    private long m_exitTime = 0;
    private LinearLayout main_layout, first_layout, second_layout,
            third_layout;
    private ListView listview = null;
    private Button openButton;
    private Button closeButton;
    private WebView webView;
    private PackageManager pm;
    private BrowseApplicationInfoAdapter browseAppAdapter = null;
    private PermissionRecordDAO manageDao;
    int totalNumOfTyep_1;//发送短信
    int totalNumOfTyep_4;//读取短消息数据
    int totalNumOfTyep_8;//读取联系人数据
    int totalNumOfTyep_16;//读取通话记录
    int totalNumOfTyep_32;//获取您当前位置
    int totalNumOfTyep_64;//获得IMEI号码
    int totalNumOfTyep_512;//获得ROOT权限
    int totalNumOfTyep_1024;//监听来电状态


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.analysis);
        setupViewComponet();
        manageDao = new PermissionRecordDAO(this);
        browseAppAdapter = new BrowseApplicationInfoAdapter(AnalysisActivity.this,
                StartActivity.static_listAppInfo);
        listview.setAdapter(browseAppAdapter);
        initViews();
    }

    private void initViews() {
        final int test = 12;
        int i = 1;
        totalNumOfTyep_1 = manageDao.getTotalNumOf_type(1);
        totalNumOfTyep_4 = manageDao.getTotalNumOf_type(4);
        totalNumOfTyep_8 = manageDao.getTotalNumOf_type(8);
        totalNumOfTyep_16 = manageDao.getTotalNumOf_type(16);
        totalNumOfTyep_32 = manageDao.getTotalNumOf_type(32);
        totalNumOfTyep_64 = manageDao.getTotalNumOf_type(64);
        totalNumOfTyep_512 = manageDao.getTotalNumOf_type(512);
        totalNumOfTyep_1024 = manageDao.getTotalNumOf_type(1024);

        System.out.println(totalNumOfTyep_1);
        System.out.println(totalNumOfTyep_4);
        System.out.println(totalNumOfTyep_8);
        System.out.println(totalNumOfTyep_16);
        System.out.println(totalNumOfTyep_32);
        System.out.println(totalNumOfTyep_64);
        System.out.println(totalNumOfTyep_512);
        System.out.println(totalNumOfTyep_1024);
        System.out.println(manageDao.getNumOfRecord());
        webView = (WebView) findViewById(R.id.webView);
        //因为要调用页面的javascript,所以要启用javascript支持
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        //在我实现的过程中,没有设置setWebChromeClient时无法调用页面的javascript
        webView.setWebChromeClient(new WebChromeClient());

        //webView.getSettings().setBuiltInZoomControls(true);
        // webView.getSettings().setUseWideViewPort(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                //在网页加载完成之后,再调用网页上的javascript方法
//				webView.loadUrl("javascript:drawChart(["+data+"],"+option+")");
                webView.loadUrl("javascript:(function color_gradients(container) {" +
                        "" +
                        "  var" +
                        "    bars = {" +
                        "      data: []," +
                        "      bars: {" +
                        "        show: true," +
                        "        barWidth: 0.8," +
                        "        lineWidth: 0," +
                        "        fillColor: {" +
                        "          colors: ['#1874CD', '#00B2EE']," +
                        "          start: 'top'," +
                        "          end: 'bottom'" +
                        "        }," +
                        "        fillOpacity: 0.8" +
                        "      }" +
                        "    }, markers = {" +
                        "      data: []," +
                        "      markers: {" +
                        "        show: true," +
                        "        position: 'ct'" +
                        "      }" +
                        "    }, lines = {" +
                        "      data: []," +
                        "      lines: {" +
                        "        show: true," +
                        "        fillColor: ['#00A8F0', '#fff']," +
                        "        fill: true," +
                        "        fillOpacity: 1" +
                        "      }" +
                        "    }," +
                        "    point," +
                        "    graph," +
                        "    i;" +
                        "    point = [1, "+totalNumOfTyep_1+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [2, "+totalNumOfTyep_4+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [3, "+totalNumOfTyep_8+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [4, "+totalNumOfTyep_16+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [5, "+totalNumOfTyep_32+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [6, "+totalNumOfTyep_64+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [7, "+totalNumOfTyep_512+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "    point = [8, "+totalNumOfTyep_1024+"];" +
                        "    bars.data.push(point);" +
                        "    markers.data.push(point);" +
                        "  graph = Flotr.draw(" +
                        "    container," +
                        "    [bars, markers], {" +
                        "title:'手机隐私权限获取次数情况',       " +
                        "      yaxis: {" +
                        "        min: 0," +
                        "        max: 3500" +
                        "      }," +
                        "      xaxis: {" +
                        "        ticks:[[1,\"发送短信\"],[2,\"读取短信\"],[3,\"读取联系人\"],[4,\"读取通话记录\"],[5,\"获得位置信息\"],[6,\"获取IMEI号码\"],[7,\"获得ROOT权限\"],[8,\"监听来电状态\"]]," +
                        "        min: 0.5," +
                        "        max: 8.5" +
                        "      }," +
                        "      grid: {" +
                        "        verticalLines: false," +
                        "        backgroundColor: ['#fff', '#ccc']" +
                        "      }" +
                        "    }" +
                        "  );"+
                        "})(document.getElementById(\"container\"));");
            }

        });

        webView.loadUrl("file:///android_asset/chart.html");
    }


    private void setupViewComponet() {
        Intent intent = getIntent();
        boolean clickble = intent.getBooleanExtra("clickble", true);

        main_layout = (LinearLayout) findViewById(R.id.first_layout_ly);
        main_layout.setOnClickListener(clickListener_main);

        first_layout = (LinearLayout) findViewById(R.id.second_layout_ly);
        first_layout.setSelected(clickble);

        second_layout = (LinearLayout) findViewById(R.id.main_layout_ly);
        second_layout.setOnClickListener(clickListener_second);

        third_layout = (LinearLayout) findViewById(R.id.third_layout_ly);
        third_layout.setOnClickListener(clickListener_third);

        listview = (ListView) findViewById(R.id.listviewApp);
        listview.setOnItemClickListener(this);

        openButton = (Button) findViewById(R.id.openButton);
        openButton.setOnClickListener(openButton_listener);

        closeButton = (Button) findViewById(R.id.closeButton);
        closeButton.setOnClickListener(closeButton_listener);
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

    // @Override
    // protected void onStop() {
    // // TODO Auto-generated method stub
    // super.onStop();
    // this.finish();
    // }
    private OnClickListener openButton_listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            listview.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }
    };

    private OnClickListener closeButton_listener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            listview.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);
        }
    };

    private OnClickListener clickListener_main = new OnClickListener() {
        @Override
        public void onClick(View v) {
            main_layout.setSelected(true);
            first_layout.setSelected(false);
            second_layout.setSelected(false);
            third_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(AnalysisActivity.this, FlowActivity.class);
            intent.putExtra("clickble", true);
            AnalysisActivity.this.finish();
            startActivity(intent);
            main_layout.setSelected(false);
        }
    };
    private OnClickListener clickListener_second = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            main_layout.setSelected(false);
            first_layout.setSelected(false);
            second_layout.setSelected(false);
            third_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(AnalysisActivity.this, PermissionActivity.class);
            intent.putExtra("clickble", true);
            AnalysisActivity.this.finish();
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
            intent.setClass(AnalysisActivity.this, SettingActivity.class);
            intent.putExtra("clickble", true);
            AnalysisActivity.this.finish();
            startActivity(intent);
            third_layout.setSelected(false);
        }
    };

//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//		this.finish();
//	}

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        AppInfo appInfo = StartActivity.static_listAppInfo.get(position);
//		System.out.println("appInfo.getAppLabel()" + appInfo.getAppLabel());
//		System.out.println("appInfo.getPkgName()" + appInfo.getPkgName());
        Intent intent = new Intent(AnalysisActivity.this, Drawable.class);
        Bundle bundle = new Bundle();
        bundle.putString("App", appInfo.getAppLabel());
        bundle.putInt("pkg", appInfo.getUid());
        bundle.putString("pkgName", appInfo.getPkgName());
        intent.putExtras(bundle);
        startActivity(intent);
    }

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

}
