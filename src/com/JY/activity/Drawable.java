package com.JY.activity;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import com.JY.UI.R;
import com.JY.SQLite.PermissionRecord;
import com.JY.SQLite.PermissionRecordDAO;
import com.JY.SQLite.SignatureRecord;
import com.JY.SQLite.SignatureRecordDao;
import com.JY.utils.DataTraffic;

public class Drawable extends Activity {
    private Button backButton = null;
    private Button suggestionButton = null;
    private TextView onlyOneTextView;
    private LinearLayout onlyong_layout;
    private int AppPkg = 0;
    private String AppLabel;
    private String pkgName;
    private int totalPermissionRecordNum = 0;
    private int dataNum1_sendMSG1 = 0;
    private int dataNum2_getMSG4 = 0;
    private int dataNum3_getPhoneNum8 = 0;
    private int dataNum4_getCallRecord16 = 0;
    private int dataNum5_getPosition32 = 0;
    private int dataNum6_getIMEI64 = 0;
    private int dataNum7_getROOT512 = 0;
    private int dataNum8_getInTelSta1024 = 0;
    private int danger0 = 1;//危险度
    private int danger1 = 4;
    private int danger2 = 2;
    private int danger3 = 3;
    private int danger4 = 1;
    private int danger5 = 4;
    private int danger6 = 2;
    private int danger7 = 3;
    int signalExit1 = 0;
    int signalExit2 = 0;
    int signalExit3 = 0;
    int signalExit4 = 0;
    int signalExit5 = 0;
    int signalExit6 = 0;
    int signalExit7 = 0;
    int signalExit8 = 0;
    private String backString1 = "";
    private String onePermisson = "";
    private long hadSendInfo = 0;//发送的数据量
    private WebView drawable_webView;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private static final String[] m = {"危险度分析", "隐私访问意图分析"};
    private int theNumOfSelected;//选中的类型 1为雷达图 2为饼状图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        Bundle bundle = getIntent().getExtras();
        AppLabel = bundle.getString("App");//应用程序名称
        AppPkg = bundle.getInt("pkg");//应用程序ID
        pkgName = bundle.getString("pkgName");
        System.out.println(AppLabel + AppPkg + pkgName);
        totalPermissionRecordNum = needToDraw(pkgName);//应用程序数据库中记录的权限记录总数
        hadSendInfo = DataTraffic.getUidTotalSendKB(AppPkg);
        if (hadSendInfo == 0) {
            backString1 = backString1 + AppLabel + "并没有上传数据流量";
        } else {
            backString1 = backString1 +AppLabel + "共上传数据" + hadSendInfo +"KB";
        }
        PermissionRecordDAO manageDAO = new PermissionRecordDAO(this);
//        SigRecordDao sigRecordDao = new SigRecordDao(this);
//        long hash = getSignature(pkgName);
//        sigRecordDao.add(new SigRecord(AppLabel,pkgName,hash));

        if (totalPermissionRecordNum == 0) {
            System.out.println("nothing!");
            setContentView(R.layout.drawable_nothing);
            backButton = (Button) findViewById(R.id.item_back);
            backButton.setOnClickListener(backListener);
            return;
        }
        setContentView(R.layout.drawable);
        setupViewComponet();
        dataNum1_sendMSG1 = manageDAO.getTyep_NumOf_pkg(1, AppPkg);
        dataNum2_getMSG4 = manageDAO.getTyep_NumOf_pkg(4, AppPkg);
        dataNum3_getPhoneNum8 = manageDAO.getTyep_NumOf_pkg(8, AppPkg);
        dataNum4_getCallRecord16 = manageDAO.getTyep_NumOf_pkg(16, AppPkg);
        dataNum5_getPosition32 = manageDAO.getTyep_NumOf_pkg(32, AppPkg);
        dataNum6_getIMEI64 = manageDAO.getTyep_NumOf_pkg(64, AppPkg);
        dataNum7_getROOT512 = manageDAO.getTyep_NumOf_pkg(512, AppPkg);
        dataNum8_getInTelSta1024 = manageDAO.getTyep_NumOf_pkg(1024, AppPkg);

        if (dataNum1_sendMSG1 != 0) {
            signalExit1 = 1;
            onePermisson = "发送短信";
        }
        if (dataNum2_getMSG4 != 0) {
            signalExit2 = 1;
            onePermisson = "读取短信内容";
        }
        if (dataNum3_getPhoneNum8 != 0) {
            signalExit3 = 1;
            onePermisson = "获取联系人数据";
        }
        if (dataNum4_getCallRecord16 != 0) {
            signalExit4 = 1;
            onePermisson = "读取通话记录";
        }
        if (dataNum5_getPosition32 != 0) {
            signalExit5 = 1;
            onePermisson = "获取位置信息";
        }
        if (dataNum6_getIMEI64 != 0) {
            signalExit6 = 1;
            onePermisson = "获取IMEI号码";
        }
        if (dataNum7_getROOT512 != 0) {
            signalExit7 = 1;
            onePermisson = "获取ROOT权限";
        }
        if (dataNum8_getInTelSta1024 != 0) {
            signalExit8 = 1;
            onePermisson = "监听来电状态";
        }


        setDangerNum();

        System.out.println("dataNum1_sendMSG1---" + dataNum1_sendMSG1);
        System.out.println("dataNum2_getMSG4---" + dataNum2_getMSG4);
        System.out.println("dataNum3_getPhoneNum8---" + dataNum3_getPhoneNum8);
        System.out.println("dataNum4_getCallRecord16---" + dataNum4_getCallRecord16);
        System.out.println("dataNum5_getPosition32---" + dataNum5_getPosition32);
        System.out.println("dataNum6_getIMEI64---" + dataNum6_getIMEI64);
        System.out.println("dataNum7_getROOT512---" + dataNum7_getROOT512);
        System.out.println("dataNum8_getInTelSta1024---" + dataNum8_getInTelSta1024);
        System.out.println("pkg---->" + AppPkg);
        System.out.println("App---->" + AppLabel);
        System.out.println("need to draw--->" + needToDraw(pkgName));
        System.out.println("getNumOf_Pkg--->" + manageDAO.getNumOf_Pkg(AppPkg));

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        theNumOfSelected = 1;
                        suggestionButton.setVisibility(View.VISIBLE);
                        onlyong_layout.setVisibility(View.GONE);
                        initViews_1();
                        break;
                    case 1:
                        suggestionButton.setVisibility(View.GONE);
                        theNumOfSelected = 2;
                        if ((signalExit1 + signalExit2 + signalExit3 + signalExit4 + signalExit5 + signalExit6 + signalExit7 + signalExit8) == 1) {
                            //只访问一种权限的处理
                            drawable_webView.setVisibility(View.GONE);
                            onlyOneTextView.setText(onePermisson);
                            onlyong_layout.setVisibility(View.VISIBLE);

                        } else {
                            initViews_2();
                        }

                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initViews_1() {
        drawable_webView.setVisibility(View.VISIBLE);
        drawable_webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                drawable_webView.loadUrl("javascript:(function basic_radar(container2) {" +
                        "" +
                        "  var" +
                        "    s1 = { label : '实际危险度', data : [[0, " + danger0 + "], [1, " + danger1 + "], [2, " + danger2 + "], [3, " + danger3 + "], [4, " + danger4 + "], [5, " + danger5 + "],[6," + danger6 + "],[7," + danger7 + "]]  }," +
                        "    s2 = { label : '预警门限值', data : [[0, 2], [1, 6], [2, 4], [3, 6], [4, 3], [5, 7],[6,5],[7,9]] }," +
                        "    graph, ticks;" +
                        "" +
                        "  ticks = [" +
                        "    [0, \"获取ROOT权限\"]," +
                        "    [1, \"读取通话记录\"]," +
                        "    [2, \"发送短信\"]," +
                        "    [3, \"获取位置信息\"]," +
                        "    [4, \"读取短信内容\"]," +
                        "    [5, \"监听来电状态\"]," +
                        "    [6,\"读取联系人\"]," +
                        "    [7,\"获取IMEI号码\"]" +
                        "  ];" +
                        "    " +
                        "   graph = Flotr.draw(container2, [ s1, s2 ], {" +
                        "HtmlText:true," +
                        "    title:'危险度分析雷达图'," +
                        "    radar : { show : true,HtmlText:true}, " +
                        "    grid  : { circular : true, minorHorizontalLines : true}, " +
                        "    yaxis : { min : 0, max : 10, minorTickFreq : 2}, " +
                        "    xaxis : { ticks : ticks}" +
                        "  });" +
                        "})(document.getElementById(\"container2\"));");
            }

        });
        drawable_webView.loadUrl("file:///android_asset/chart2.html");
    }

    private void initViews_2() {
        drawable_webView.setVisibility(View.VISIBLE);
        drawable_webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String functionString = "javascript:(function basic_pie(container2) {" +
                        "  var";
                String middle = "    graph;" +
                        "  graph = Flotr.draw(container2, [";
                String end = "  ], {" +
                        "    title:'隐私访问意图分析饼状图'," +
                        "    HtmlText : true," +
                        "    grid : {" +
                        "      verticalLines : false," +
                        "      horizontalLines : false" +
                        "    }," +
                        "    xaxis : { showLabels : false }," +
                        "    yaxis : { showLabels : false }," +
                        "    pie : {" +
                        "      show : true, " +
                        "      explode : 6" +
                        "    }," +
                        "    mouse : { track : false }," +
                        "    legend : {" +
                        "      position : 'se'," +
                        "      backgroundColor : '#D2E8FF'" +
                        "    }" +
                        "  });" +
                        "})(document.getElementById(\"container2\"));";
                if (dataNum1_sendMSG1 != 0) {
                    functionString = functionString + "    d1 = [[0, " + dataNum1_sendMSG1 + "]],";
                }
                if (dataNum2_getMSG4 != 0) {
                    functionString = functionString + "    d2 = [[0, " + dataNum2_getMSG4 + "]],";
                }
                if (dataNum3_getPhoneNum8 != 0) {
                    functionString = functionString + "    d3 = [[0, " + dataNum3_getPhoneNum8 + "]],";
                }
                if (dataNum4_getCallRecord16 != 0) {
                    functionString = functionString + "    d4 = [[0, " + dataNum4_getCallRecord16 + "]],";
                }
                if (dataNum5_getPosition32 != 0) {
                    functionString = functionString + "    d5 = [[0, " + dataNum5_getPosition32 + "]],";
                }
                if (dataNum6_getIMEI64 != 0) {
                    functionString = functionString + "    d6 = [[0, " + dataNum6_getIMEI64 + "]],";
                }
                if (dataNum7_getROOT512 != 0) {
                    functionString = functionString + "    d7 = [[0, " + dataNum7_getROOT512 + "]],";
                }
                if (dataNum8_getInTelSta1024 != 0) {
                    functionString = functionString + "    d8 = [[0, " + dataNum8_getInTelSta1024 + "]],";
                }

                functionString = functionString + middle;

                if (dataNum1_sendMSG1 != 0) {
                    functionString = functionString + "    { data : d1, label : '发送短信' },";
                }
                if (dataNum2_getMSG4 != 0) {
                    functionString = functionString + "    { data : d2, label : '获取短信内容' },";
                }
                if (dataNum3_getPhoneNum8 != 0) {
                    functionString = functionString + "    { data : d3, label : '读取联系人' },";
                }
                if (dataNum4_getCallRecord16 != 0) {
                    functionString = functionString + "    { data : d4, label : '读取通话记录' },";
                }
                if (dataNum5_getPosition32 != 0) {
                    functionString = functionString + "    { data : d5, label : '获取位置信息' },";
                }
                if (dataNum6_getIMEI64 != 0) {
                    functionString = functionString + "    { data : d6, label : '获取IMEI号码',color:'#000' },";
                }
                if (dataNum7_getROOT512 != 0) {
                    functionString = functionString + "    { data : d7, label : '获取ROOT权限' },";
                }
                if (dataNum8_getInTelSta1024 != 0) {
                    functionString = functionString + "    { data : d8, label : '监听来电状态' },";
                }
                functionString = functionString + end;
                drawable_webView.loadUrl(functionString);

//                drawable_webView.loadUrl("javascript:(function basic_pie(container2) {" +
//                        "" +
//                        "  var" +
//                        "    d1 = [[0, "+dataNum1_sendMSG1+"]]," +
//                        "    d2 = [[0, "+dataNum2_getMSG4+"]]," +
//                        "    d3 = [[0, "+dataNum3_getPhoneNum8+"]]," +
//                        "    d4 = [[0, "+dataNum4_getCallRecord16+"]]," +
//                        "    d5 = [[0, "+dataNum5_getPosition32+"]]," +
//                        "    d6 = [[0, "+dataNum6_getIMEI64+"]]," +
//                        "    d7 = [[0, "+dataNum7_getROOT512+"]]," +
//                        "    d8 = [[0, "+dataNum8_getInTelSta1024+"]]," +
//                        "    graph;" +
//                        "  graph = Flotr.draw(container2, [" +
//                        "    { data : d1, label : '发送短信' }," +
//                        "    { data : d2, label : '获取短信内容' }," +
//                        "    { data : d3, label : '读取联系人'}," +
//                        "    { data : d4, label : '读取通话记录' }," +
//                        "    { data : d5, label : '获取位置信息' }," +
//                        "    { data : d6, label : '获取IMEI号码' }," +
//                        "    { data : d7, label : '获取ROOT权限' }," +
//                        "    { data : d8, label : '监听来电状态' }" +
//                        "  ], {" +
//                        "    title:'隐私访问意图分析饼状图',"+
//                        "    HtmlText : true," +
//                        "    grid : {" +
//                        "      verticalLines : false," +
//                        "      horizontalLines : false" +
//                        "    }," +
//                        "    xaxis : { showLabels : false }," +
//                        "    yaxis : { showLabels : false }," +
//                        "    pie : {" +
//                        "      show : true, " +
//                        "      explode : 6" +
//                        "    }," +
//                        "    mouse : { track : false }," +
//                        "    legend : {" +
//                        "      position : 'se'," +
//                        "      backgroundColor : '#D2E8FF'" +
//                        "    }" +
//                        "  });" +
//                        "})(document.getElementById(\"container2\"));");
            }

        });
        drawable_webView.loadUrl("file:///android_asset/chart2.html");
    }


    private void setDangerNum() {

        if (dataNum1_sendMSG1 != 0) {
            backString1 = backString1 + "\n" +"发送过短信，可能泄漏个人信息或造成财产损害";
            danger2 = 8;
        }
        if (dataNum2_getMSG4 != 0) {
            backString1 = backString1 + "\n" +"读取过您的短信内容，短信隐私可能被泄漏";
            danger4 = 7;
        }
        if (hadSendInfo == 0) {
            if (dataNum8_getInTelSta1024 != 0) {
                backString1 = backString1 + "\n" +"监听过听话状态，危险系数在门限之内";
                danger5 = 3;
            }
            if (dataNum7_getROOT512 != 0) {
                backString1 = backString1 + "\n" +"获取过ROOT权限，危险系数在门限之内";
                danger0 = 2;
            }
            if (dataNum5_getPosition32 != 0) {
                backString1 = backString1 + "\n" +"获取过您的位置信息，危险系数在门限之内";
                danger3 = 6;
            }
            if (dataNum6_getIMEI64 != 0) {
                backString1 = backString1 + "\n" +"获得过您手机的IMEI号码，危险系数在门限之内";
                danger7 = 8;
            }
            if (dataNum4_getCallRecord16 != 0) {
                backString1 = backString1 + "\n" +"访问过您的通过记录，危险系数在门限之内";
                danger1 = 5;
            }
            if (dataNum3_getPhoneNum8 != 0) {
                backString1 = backString1 + "\n" +"访问过您的通讯录，危险系数在门限之内";
                danger6 = 4;
            }
            return;
        }
        if (dataNum8_getInTelSta1024 != 0) {
            backString1 = backString1 + "\n" +"监听过听话状态，可能泄漏您的通话信息";
            danger5 = 7;
        }
        if (dataNum7_getROOT512 != 0) {
            backString1 = backString1 + "\n" +"获取过ROOT权限，可能泄漏您的隐私信息";
            danger0 = 8;
        }
        if (dataNum5_getPosition32 != 0) {
            backString1 = backString1 + "\n" +"获取过您的位置信息，可能泄漏您的位置隐私";
            danger3 = 8;
        }
        if (dataNum6_getIMEI64 != 0) {
            backString1 = backString1 + "\n" +"获取过您的IMEI号码，可能泄漏您的手机信息";
            danger7 = 9;
        }
        if (dataNum4_getCallRecord16 != 0) {
            backString1 = backString1 + "\n" +"访问过您的通过记录，可能泄漏您的隐私信息";
            danger1 = 7;
        }
        if (dataNum3_getPhoneNum8 != 0) {
            backString1 = backString1 + "\n" +"访问过您的通讯录，可能泄漏您的联系人信息";
            danger6 = 8;
        }
    }

    private int checkSigHash(String AppLabel, String pkgName) {
        SignatureRecordDao sigRecordDao = new SignatureRecordDao(this);
        if (sigRecordDao.find(AppLabel) == null) {
            return -1;// 没有查询到
        }
        long sigNum = sigRecordDao.find(AppLabel).getSignature();
        if (sigNum == getSignature(pkgName)) {
            return 1;// 签名验证通过
        } else {
            return 0;// 签名不正确
        }

    }

    private void setupViewComponet() {
        backButton = (Button) findViewById(R.id.item_back);
        backButton.setOnClickListener(backListener);
        suggestionButton = (Button) findViewById(R.id.suggestionButton);
        suggestionButton.setOnClickListener(suggestListener);
        spinner = (Spinner) findViewById(R.id.spinner);
        drawable_webView = (WebView) findViewById(R.id.drawable_webView);
        drawable_webView.getSettings().setJavaScriptEnabled(true);
        drawable_webView.getSettings().setBuiltInZoomControls(true);
        drawable_webView.setWebChromeClient(new WebChromeClient());
        //drawable_webView.getSettings().setBuiltInZoomControls(true);
        drawable_webView.getSettings().setUseWideViewPort(true);
        onlyOneTextView = (TextView)findViewById(R.id.onlyone_text);
        onlyong_layout = (LinearLayout)findViewById(R.id.onlyong_layout);
    }


    public long getSignature(String packageName) {
        PackageManager pm = this.getPackageManager();
        PackageInfo packageInfo = null;
        long hash = 0;
        try {
            packageInfo = pm.getPackageInfo(packageName,
                    PackageManager.GET_SIGNATURES);
            android.content.pm.Signature[] signs = packageInfo.signatures;
            hash = signs[0].hashCode();
            // android.content.pm.Signature sign = signs[0];
            // parseSignature(sign.toByteArray());
        } catch (Exception e) {
            hash = 0;
        }
        return hash;
    }

    private OnClickListener backListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Drawable.this.finish();
        }
    };

    private OnClickListener suggestListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (theNumOfSelected == 1) {
                //雷达图
                System.out.println("雷达图");

                AlertDialog.Builder builder = new AlertDialog.Builder(Drawable.this);
                builder.setMessage(backString1)
                        .setCancelable(false)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
            if (theNumOfSelected == 2) {
                //饼状图
                System.out.println("饼状图");
            }
        }
    };

    public int needToDraw(String pkgName) {
        PermissionRecordDAO manageDAO = new PermissionRecordDAO(this);
        List<PermissionRecord> permissionRecords = manageDAO
                .getPermissionRecordOf_pkg(pkgName);
        return permissionRecords.size();
    }

}
