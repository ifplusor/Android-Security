package com.JY.draw;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.view.View;

public class myView extends View {
    private Paint mPaint;
    private List<PermissionModel> permissionModles;
    private int bottomBound, leftBound, rightBound;
    private int fiveDip, fortyfiveDip, fiftyDip;


    public myView(Context context) {
        super(context);
        mPaint = new Paint();
        // TODO Auto-generated constructor stub
    }

    public myView(Context context, List<PermissionModel> permissionModles,
                  int bottomBound, int leftBound, int rightBound, float scale) {
        super(context);
        mPaint = new Paint();
        this.permissionModles = permissionModles;
        this.bottomBound = bottomBound;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        fiveDip = DisplayUtil.dip2px(5f, scale);
        fortyfiveDip = DisplayUtil.dip2px(45f, scale);
        fiftyDip = DisplayUtil.dip2px(50f, scale);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //super.onDraw(canvas);

        mPaint.setAntiAlias(true);
        //绘制坐标
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(leftBound, bottomBound, rightBound, bottomBound, mPaint);
        canvas.drawLine(leftBound, 0, leftBound, bottomBound, mPaint);
        //绘制图形

        for (int i = 0; i < permissionModles.size(); i++) {
            PermissionModel permissionModle = permissionModles.get(i);
            int counter = permissionModle.getNumOfPermissions();
            //App没有取得权限
            if (counter == 0) {
                mPaint.setColor(Color.WHITE);
                canvas.drawRect(leftBound, bottomBound - i * fiftyDip - fortyfiveDip, rightBound,
                        bottomBound - i * fiftyDip - fiveDip, mPaint);
            }
            //App取得一个权限
            if (counter == 1) {
                if (permissionModle.isSendMsg()) setSendMsgColor();
                if (permissionModle.isReadCtc()) setReadCtcColor();
                if (permissionModle.isReadPhone()) setReadPhoneColor();
                if (permissionModle.isTrack()) setTrackColor();
                if (permissionModle.isIMEI()) setIMEIColor();
                if (permissionModle.isRoot()) setRootColor();
                if (permissionModle.isMonitorCall()) setMonitorCallColor();
                if(permissionModle.isReadMsg())setReadMsgColor();
                canvas.drawRect(leftBound, bottomBound - i * fiftyDip - fortyfiveDip, rightBound,
                        bottomBound - i * fiftyDip - fiveDip, mPaint);
            }
            //App取得两个权限
            if (counter == 2) {
                int j = 0;
                float between = (rightBound - leftBound) / counter;
                boolean[] flags = {true, true, true, true, true, true, true,true};
                while (j < 2) {
                    if (permissionModle.isSendMsg() && flags[0]) {
                        setSendMsgColor();
                        flags[0] = false;
                    } else if (permissionModle.isReadCtc() && flags[1]) {
                        setReadCtcColor();
                        flags[1] = false;

                    } else if (permissionModle.isReadPhone() && flags[2]) {
                        setReadPhoneColor();
                        flags[2] = false;
                    } else if (permissionModle.isTrack() && flags[3]) {
                        setTrackColor();
                        flags[3] = false;
                    } else if (permissionModle.isIMEI() && flags[4]) {
                        setIMEIColor();
                        flags[4] = false;
                    } else if (permissionModle.isRoot() && flags[5]) {
                        setRootColor();
                        flags[5] = false;
                    } else if (permissionModle.isMonitorCall() && flags[6]) {
                        setMonitorCallColor();
                        flags[6] = false;
                    }else if(permissionModle.isReadMsg()&&flags[7]){
                    	setReadMsgColor();
                    	flags[7]=false;
                    }
                    canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                            leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                    j++;
                }
            }
            //App取得三个权限
            if (counter == 3) {
                int j = 0;
                float between = (rightBound - leftBound) / counter;
                boolean[] flags = {true, true, true, true, true, true, true,true};
                while (j < 3) {
                    if (permissionModle.isSendMsg() && flags[0]) {
                        setSendMsgColor();
                        flags[0] = false;
                    } else if (permissionModle.isReadCtc() && flags[1]) {
                        setReadCtcColor();
                        flags[1] = false;

                    } else if (permissionModle.isReadPhone() && flags[2]) {
                        setReadPhoneColor();
                        flags[2] = false;
                    } else if (permissionModle.isTrack() && flags[3]) {
                        setTrackColor();
                        flags[3] = false;
                    } else if (permissionModle.isIMEI() && flags[4]) {
                        setIMEIColor();
                        flags[4] = false;
                    } else if (permissionModle.isRoot() && flags[5]) {
                        setRootColor();
                        flags[5] = false;
                    } else if (permissionModle.isMonitorCall() && flags[6]) {
                        setMonitorCallColor();
                        flags[6] = false;
                    }else if(permissionModle.isReadMsg()&&flags[7]){
                    	setReadMsgColor();
                    	flags[7]=false;
                    }
                    canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                            leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                    j++;
                }
            }
            //App取得四个权限
            if (counter == 4) {
                int j = 0;
                float between = (rightBound - leftBound) / counter;
                boolean[] flags = {true, true, true, true, true, true, true,true};
                while (j < 4) {
                    if (permissionModle.isSendMsg() && flags[0]) {
                        setSendMsgColor();
                        flags[0] = false;
                    } else if (permissionModle.isReadCtc() && flags[1]) {
                        setReadCtcColor();
                        flags[1] = false;

                    } else if (permissionModle.isReadPhone() && flags[2]) {
                        setReadPhoneColor();
                        flags[2] = false;
                    } else if (permissionModle.isTrack() && flags[3]) {
                        setTrackColor();
                        flags[3] = false;
                    } else if (permissionModle.isIMEI() && flags[4]) {
                        setIMEIColor();
                        flags[4] = false;
                    } else if (permissionModle.isRoot() && flags[5]) {
                        setRootColor();
                        flags[5] = false;
                    } else if (permissionModle.isMonitorCall() && flags[6]) {
                        setMonitorCallColor();
                        flags[6] = false;
                    }else if(permissionModle.isReadMsg()&&flags[7]){
                    	setReadMsgColor();
                    	flags[7]=false;
                    }
                    canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                            leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                    j++;
                }
            }
            //App取得五个权限
            if (counter == 5) {
                int j = 0;
                float between = (rightBound - leftBound) / counter;
                boolean[] flags = {true, true, true, true, true, true, true,true};
                while (j < 5) {
                    if (permissionModle.isSendMsg() && flags[0]) {
                        setSendMsgColor();
                        flags[0] = false;
                    } else if (permissionModle.isReadCtc() && flags[1]) {
                        setReadCtcColor();
                        flags[1] = false;

                    } else if (permissionModle.isReadPhone() && flags[2]) {
                        setReadPhoneColor();
                        flags[2] = false;
                    } else if (permissionModle.isTrack() && flags[3]) {
                        setTrackColor();
                        flags[3] = false;
                    } else if (permissionModle.isIMEI() && flags[4]) {
                        setIMEIColor();
                        flags[4] = false;
                    } else if (permissionModle.isRoot() && flags[5]) {
                        setRootColor();
                        flags[5] = false;
                    } else if (permissionModle.isMonitorCall() && flags[6]) {
                        setMonitorCallColor();
                        flags[6] = false;
                    }else if(permissionModle.isReadMsg()&&flags[7]){
                    	setReadMsgColor();
                    	flags[7]=false;
                    }
                    canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                            leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                    j++;
                }
            }
            //App取得六个权限
            if (counter == 6) {
                int j = 0;
                float between = (rightBound - leftBound) / counter;
                boolean[] flags = {true, true, true, true, true, true, true,true};
                while (j < 6) {
                    if (permissionModle.isSendMsg() && flags[0]) {
                        setSendMsgColor();
                        flags[0] = false;
                    } else if (permissionModle.isReadCtc() && flags[1]) {
                        setReadCtcColor();
                        flags[1] = false;

                    } else if (permissionModle.isReadPhone() && flags[2]) {
                        setReadPhoneColor();
                        flags[2] = false;
                    } else if (permissionModle.isTrack() && flags[3]) {
                        setTrackColor();
                        flags[3] = false;
                    } else if (permissionModle.isIMEI() && flags[4]) {
                        setIMEIColor();
                        flags[4] = false;
                    } else if (permissionModle.isRoot() && flags[5]) {
                        setRootColor();
                        flags[5] = false;
                    } else if (permissionModle.isMonitorCall() && flags[6]) {
                        setMonitorCallColor();
                        flags[6] = false;
                    }else if(permissionModle.isReadMsg()&&flags[7]){
                    	setReadMsgColor();
                    	flags[7]=false;
                    }
                    canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                            leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                    j++;
                }
            }
            //App取得七个权限
            if (counter == 7) {
                int j = 0;
                float between = (rightBound - leftBound) / counter;
                boolean[] flags = {true, true, true, true, true, true, true,true};
                while (j < 7) {
                    if (permissionModle.isSendMsg() && flags[0]) {
                        setSendMsgColor();
                        flags[0] = false;
                    } else if (permissionModle.isReadCtc() && flags[1]) {
                        setReadCtcColor();
                        flags[1] = false;

                    } else if (permissionModle.isReadPhone() && flags[2]) {
                        setReadPhoneColor();
                        flags[2] = false;
                    } else if (permissionModle.isTrack() && flags[3]) {
                        setTrackColor();
                        flags[3] = false;
                    } else if (permissionModle.isIMEI() && flags[4]) {
                        setIMEIColor();
                        flags[4] = false;
                    } else if (permissionModle.isRoot() && flags[5]) {
                        setRootColor();
                        flags[5] = false;
                    } else if (permissionModle.isMonitorCall() && flags[6]) {
                        setMonitorCallColor();
                        flags[6] = false;
                    }else if(permissionModle.isReadMsg()&&flags[7]){
                    	setReadMsgColor();
                    	flags[7]=false;
                    }
                    canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                            leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                    j++;
                }
            }
            //App获得八个权限
            if(counter==8){
            	 int j = 0;
                 float between = (rightBound - leftBound) / counter;
                 boolean[] flags = {true, true, true, true, true, true, true,true};
                 while (j < 8) {
                     if (permissionModle.isSendMsg() && flags[0]) {
                         setSendMsgColor();
                         flags[0] = false;
                     } else if (permissionModle.isReadCtc() && flags[1]) {
                         setReadCtcColor();
                         flags[1] = false;

                     } else if (permissionModle.isReadPhone() && flags[2]) {
                         setReadPhoneColor();
                         flags[2] = false;
                     } else if (permissionModle.isTrack() && flags[3]) {
                         setTrackColor();
                         flags[3] = false;
                     } else if (permissionModle.isIMEI() && flags[4]) {
                         setIMEIColor();
                         flags[4] = false;
                     } else if (permissionModle.isRoot() && flags[5]) {
                         setRootColor();
                         flags[5] = false;
                     } else if (permissionModle.isMonitorCall() && flags[6]) {
                         setMonitorCallColor();
                         flags[6] = false;
                     }else if(permissionModle.isReadMsg()&&flags[7]){
                     	setReadMsgColor();
                     	flags[7]=false;
                     }
                     canvas.drawRect(leftBound + j * between, bottomBound - i * fiftyDip - fortyfiveDip,
                             leftBound + j * between + between, bottomBound - i * fiftyDip - fiveDip, mPaint);
                     j++;
                 }
            	
            }
        }


    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setMeasuredDimension(rightBound - leftBound,
                permissionModles.size() * fiftyDip);
    }


    private void setSendMsgColor() {//发送短信
        mPaint.setARGB(0xff, 52, 73, 94);

    }

    private void setReadCtcColor() {//读取联系人数据
        mPaint.setARGB(0xff, 156, 89, 184);
    }

    private void setReadPhoneColor() {//通话记录
        mPaint.setARGB(0xff, 27, 188, 157);
    }

    private void setTrackColor() {//位置
        mPaint.setARGB(0xff, 47, 204, 113);
    }

    private void setIMEIColor() {//IMEI
        mPaint.setARGB(0xdd, 0xEB, 0x7A, 0xEB);
    }

    private void setRootColor() {//ROOT
        mPaint.setARGB(0xff, 232, 75, 61);
    }

    private void setMonitorCallColor() {//监听通话状态
        mPaint.setARGB(0xff, 53, 152, 220);
    }
    private void setReadMsgColor(){//读取短信
    	mPaint.setARGB(0xff, 241, 196, 15);
    }
}

	