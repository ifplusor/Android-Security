package com.JY.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import android.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.ImageView;

import com.JY.SQLite.PermissionRecord;
import com.JY.SQLite.PermissionRecordDAO;
import com.JY.UI.StartActivity;
import com.JY.draw.DisplayUtil;
import com.JY.draw.PermissionModel;
import com.JY.draw.myView;
import com.JY.packageInfo.AppInfo;
import com.JY.utils.SystemManager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.JY.UI.R;

public class PermissionActivity extends Activity
        implements View.OnTouchListener, GestureDetector.OnGestureListener {
    private long m_exitTime = 0;
    private LinearLayout permission_layout, traffic_layout,
            analysis_layout, setting_layout, loading_layout;
    private LinearLayout sumPicLayout = null;
    private LinearLayout sumPicLayout1 = null;
    private int bottom, left, right;
    private float scale;
    private int fiftyDip;
    private PackageManager pm = null;
    String[] pkgNames = null;
    private GestureDetector mGestureDetector;
    private List<PermissionModel> permissionModels = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.permission);
        setupViewComponent();

        loading_layout = (LinearLayout) findViewById(R.id.loading_LinearLayout);
        loading_layout.setVisibility(View.INVISIBLE);

        DisplayMetrics metric = new DisplayMetrics();
        metric = getResources().getDisplayMetrics();
        int ScreenWidth = metric.widthPixels;
        scale = metric.density;
        fiftyDip = DisplayUtil.dip2px(50f, scale);
        left = 0;
        right = ScreenWidth - DisplayUtil.dip2px(50f, scale) - 10;
        PermissionRecordDAO pr = new PermissionRecordDAO(this);
        pkgNames = pr.getAllPkgName();
        permissionModels = toPermissionModel(pkgNames);
        bottom = DisplayUtil.dip2px(50f, scale) * pkgNames.length;
//        getRoot();
//        fileFunction.copyDB();
//        listAppInfo = queryFilterAppInfo();
        showdraw();


    }

    private void showdraw() {

        // 绘制图标
        for (int i = pkgNames.length - 1; i >= 0; i--) {
            ImageView appIcon = new ImageView(this);
            Drawable appImage = getIconByPkg(pkgNames[i], StartActivity.static_listAppInfo);
            appIcon.setImageDrawable(appImage);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    fiftyDip, fiftyDip);
            sumPicLayout = (LinearLayout) findViewById(R.id.sumpic_layout);
            sumPicLayout.addView(appIcon, lp);
        }

        myView mView = new myView(this, permissionModels, bottom, left, right,
                scale);

        LinearLayout.LayoutParams lp1 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        sumPicLayout1 = (LinearLayout) findViewById(R.id.sumpic_layout1);
        sumPicLayout1.addView(mView, lp1);
        mGestureDetector = new GestureDetector(this);
        mView.setOnTouchListener(this);
        mView.setFocusable(true);
        mView.setClickable(true);
        mView.setLongClickable(true);
        mGestureDetector.setIsLongpressEnabled(true);

    }

    public List<PermissionModel> toPermissionModel(String[] pkgNames) {
        List<PermissionModel> permissionModels = new ArrayList<PermissionModel>();
        for (int i = 0; i < pkgNames.length; i++) {
        	PermissionModel permissionModel = new PermissionModel();
            PermissionRecord[] permissionRecords = getRecordArray_pkg(pkgNames[i]);
            for (int j = 0; j < permissionRecords.length; j++) {
                switch (permissionRecords[j].getType()) {
                    case 1://发送短信
                        permissionModel.setSendMsg(true);
                        permissionModel.setSendMsgTimestamp(permissionRecords[j].getTimestamp());
                        break;
                    case 4://读取短信
                    	permissionModel.setReadMsg(true);
                    	permissionModel.setReadMsgTimestamp(permissionRecords[j].getTimestamp());
                    	break;
                    case 8://读取联系人
                        permissionModel.setReadCtc(true);
                        permissionModel.setReadCtcTimestamp(permissionRecords[j].getTimestamp());
                        break;
                    case 16://读取通话记录
                        permissionModel.setReadPhone(true);
                        permissionModel.setReadPhoneTimestamp(permissionRecords[j].getTimestamp());
                        break;
                    case 32://获取位置信息
                        permissionModel.setTrack(true);
                        permissionModel.setTrackTimestamp(permissionRecords[j].getTimestamp());
                        break;
                    case 64://获取IMEI号码
                        permissionModel.setIMEI(true);
                        permissionModel.setIMEITimestamp(permissionRecords[j].getTimestamp());
                        break;
                    case 512://获取root权限
                        permissionModel.setRoot(true);
                        permissionModel.setRootTimestamp(permissionRecords[j].getTimestamp());
                        break;
                    case 1024://监听来电状态
                        permissionModel.setMonitorCall(true);
                        permissionModel.setMonitorCallTimestamp(permissionRecords[j].getTimestamp());
                        break;
                    default:
                        break;
                }
            }
            permissionModels.add(permissionModel);
        }
        return permissionModels;
    }

    public PermissionRecord[] getRecordArray_pkg(String pkgName) {
        PermissionRecordDAO manageDAO = new PermissionRecordDAO(this);
        List<PermissionRecord> permissionRecords = manageDAO
                .getPermissionRecordOf_pkg(pkgName);
        if (permissionRecords.size() == 0) {
            return new PermissionRecord[0];
        } else {
            PermissionRecord pr[] = new PermissionRecord[1];
            PermissionRecord records[] = permissionRecords.toArray(pr);
            return records;
        }
    }

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

    private Drawable getIconByPkg(String pkgName, List<AppInfo> listAppInfo) {
        AppInfo appInfo = new AppInfo();
        for (int i = 0; i < listAppInfo.size(); i++) {
            appInfo = listAppInfo.get(i);
            if (appInfo.getPkgName().equals(pkgName)) {
                break;
            }
        }
        return appInfo.getAppIcon();
    }

    // 构造一个AppInfo对象 ，并赋值
    private AppInfo getAppInfo(ApplicationInfo app) {
        AppInfo appInfo = new AppInfo();
        appInfo.setAppLabel((String) app.loadLabel(pm));
        appInfo.setAppIcon(app.loadIcon(pm));
        appInfo.setPkgName(app.packageName);
        appInfo.setUid(app.uid);
        return appInfo;
    }

    // 设置视图组件
    private void setupViewComponent() {
        Intent intent = getIntent();
        boolean clickble = intent.getBooleanExtra("clickble", true);

        permission_layout = (LinearLayout) findViewById(R.id.permission_layout_ly);
        permission_layout.setSelected(clickble);

        traffic_layout = (LinearLayout) findViewById(R.id.traffic_layout_ly);
        traffic_layout.setOnClickListener(clickListener_traffic);

        analysis_layout = (LinearLayout) findViewById(R.id.analysis_layout_ly);
        analysis_layout.setOnClickListener(clickListener_analysis);

        setting_layout = (LinearLayout) findViewById(R.id.setting_layout_ly);
        setting_layout.setOnClickListener(clickListener_setting);

    }

    public void getRoot() {
        String apkRoot = "chmod 777 " + getPackageCodePath();
        SystemManager.RootCommand(apkRoot);
    }

//	@Override
//	protected void onStop() {
//		// TODO Auto-generated method stub
//		super.onStop();
//		this.finish();
//	}

    private OnClickListener clickListener_traffic = new OnClickListener() {
        @Override
        public void onClick(View v) {
            permission_layout.setSelected(true);
            traffic_layout.setSelected(false);
            analysis_layout.setSelected(false);
            setting_layout.setSelected(false);
            Intent intent = new Intent();
            intent.setClass(PermissionActivity.this, TrafficActivity.class);
            intent.putExtra("clickble", true);
            PermissionActivity.this.finish();
            startActivity(intent);
            traffic_layout.setSelected(false);
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
            intent.setClass(PermissionActivity.this, AnalysisActivity.class);
            intent.putExtra("clickble", true);
            PermissionActivity.this.finish();
            startActivity(intent);
            analysis_layout.setSelected(false);
        }
    };
    
    private OnClickListener clickListener_setting = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            permission_layout.setSelected(false);
            traffic_layout.setSelected(false);
            analysis_layout.setSelected(false);
            setting_layout.setSelected(true);
            Intent intent = new Intent();
            intent.setClass(PermissionActivity.this, SettingActivity.class);
            intent.putExtra("clickble", true);
            PermissionActivity.this.finish();
            startActivity(intent);
            setting_layout.setSelected(false);
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        // TODO Auto-generated method stub
        // Log.i("MyGesture", "onLongPress");
//        int getX = (int) e.getX();
//        int getY = (int) e.getY();
//        new AlertDialog.Builder(this).setTitle("权限")
//                .setMessage(getPermissionInfoByAxis(getX, getY))
//                .setPositiveButton("关闭", null).show();
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        // TODO Auto-generated method stub
//        int getX = (int) e.getX();
//        int getY = (int) e.getY();
//        new AlertDialog.Builder(this).setTitle("权限")
//                .setMessage(getPermissionInfoByAxis(getX, getY))
//                .setPositiveButton("关闭", null).show();

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        // TODO Auto-generated method stub
        int getX = (int) e.getX();
        int getY = (int) e.getY();
        new AlertDialog.Builder(this).setTitle("权限")
                .setMessage(getPermissionInfoByAxis(getX, getY))
                .setPositiveButton("关闭", null).show();
        return false;
    }

    public String getPermissionInfoByAxis(int x, int y) {
        PermissionModel permissionModel = new PermissionModel();
        StringBuilder sbInfo = new StringBuilder();
        String infoTime = null;
        String infoName = null;
        List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < pkgNames.length; i++) {
            if (y < fiftyDip * (pkgNames.length - i)
                    && y > fiftyDip * (pkgNames.length - i) - fiftyDip) {
                permissionModel = permissionModels.get(i);
                break;
            }
        }
        System.out.println(permissionModel.toString());
        int counter = permissionModel.getNumOfPermissions();
        if (counter == 0) {
            return "该应用没有获取任何权限";
        }
        int between = (right - left) / counter;
        int n = 0;
        for (int i = 0; i < counter; i++) {
            if (x > between * i && x < between * i + between) {
                n = i + 1;
                break;
            }
        }
        list = permissionModel.getPermissionNameByCounter(n, permissionModel);
        infoName = list.get(0).get("PermissionName");
        infoTime = list.get(1).get("time");
        sbInfo.append("此应用在");
        sbInfo.append(infoTime);
        sbInfo.append("访问");
        sbInfo.append(infoName);
        if (sbInfo.toString() == null) {
            return "该应用没有获取任何权限";
        }
        return sbInfo.toString();
    }
}
