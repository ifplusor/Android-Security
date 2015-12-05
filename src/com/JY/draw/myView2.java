package com.JY.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class myView2 extends View{
	private Paint mPaint;
	private int bottomBound,leftBound, rightBound,topBound;
	private PermissionModel permissionModle;
	private float scale;

	public myView2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public myView2(Context context,int bottomBound,int leftBound,int rightBound,
			int topBound,PermissionModel permissionModle,float scale){
		super(context);
		this.bottomBound=bottomBound;
		this.leftBound=leftBound;
		this.rightBound=rightBound;
		this.topBound=topBound;
		this.permissionModle=permissionModle;
		this.scale=scale;
		mPaint=new Paint();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int counter=permissionModle.getNumOfPermissions();
		if(counter==0){
			//放入一张图片
		}
		else{
		float horbetween=(rightBound-leftBound)/7;
		float verbetween=(bottomBound-topBound)/28;
		mPaint.setAntiAlias(true);
		//绘制坐标
		mPaint.setColor(Color.BLACK);
		canvas.drawLine(leftBound, bottomBound, rightBound, bottomBound, mPaint);
		canvas.drawLine(leftBound, topBound, leftBound,bottomBound, mPaint);
		//警戒线
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(5);
		canvas.drawLine(leftBound,bottomBound-((bottomBound-topBound)/28)*20 , 
				rightBound,bottomBound-((bottomBound-topBound)/28)*20 , mPaint);
		//App只获得一个权限
		if(counter==1){
			float x=0,y=0;
			if(permissionModle.isSendMsg()){x=horbetween;y=verbetween*1;setSendMsgColor();}
			if(permissionModle.isReadCtc()){x=horbetween;y=verbetween*2;setReadCtcColor();}
			if(permissionModle.isReadPhone()){x=horbetween;y=verbetween*3;setReadPhoneColor();}
			if(permissionModle.isTrack()){x=horbetween;y=verbetween*4;setTrackColor();}
			if(permissionModle.isIMEI()){x=horbetween;y=verbetween*5;setIMEIColor();}
			if(permissionModle.isRoot()){x=horbetween;y=verbetween*6;setRootColor();}
			if(permissionModle.isMonitorCall()){x=horbetween;y=verbetween*7;setMonitorCallColor();}
			mPaint.setStrokeWidth(8);
			canvas.drawPoint(x+leftBound,bottomBound-y, mPaint);
		}
		//App获得三个权限
		if(counter==3){
			float x1=0,y1=0,x2=0,y2=0,x3=0,y3=0;
			int j=0;
			int beforeOne=0,beforeTwo=0;
			int[] flags={1,2,3,4,5,6,7};
			x1=horbetween*1;
			x2=horbetween*2;
			x3=horbetween*3;
			while(j<3){
				if(permissionModle.isSendMsg()&&(flags[0]==1)){
					y1=verbetween*1;
					beforeOne=1;
				    flags[0]=0;
				    setSendMsgColor();
				
				}
				
				else if(permissionModle.isReadCtc()&&(flags[1]==2)){
					switch (j) {
					case 0:	y1=verbetween*2;
							beforeOne=2;
							break;
					case 1: y2=verbetween*(2+beforeOne);
				    		beforeTwo=2;
				    		break;
					case 2: y3=verbetween*(2+beforeOne+beforeTwo);
							break;

					default:
						break;
					}
					flags[1]=0;
					setReadCtcColor();
					
				}
				else if(permissionModle.isReadPhone()&&(flags[2]==3)){
					switch (j) {
					case 0:	y1=verbetween*3;
							beforeOne=3;
							break;
					case 1: y2=verbetween*(3+beforeOne);
		    				beforeTwo=3;
		    				break;
					case 2: y3=verbetween*(3+beforeOne+beforeTwo);
							break;


					default:
						break;
					}
					flags[2]=0;
					setReadPhoneColor();
					
				}
				else if(permissionModle.isTrack()&&(flags[3]==4)){
					switch (j) {
					case 0:	y1=verbetween*4;
							beforeOne=4;
							break;
					case 1: y2=verbetween*(4+beforeOne);
		    				beforeTwo=4;
		    				break;
					case 2: y3=verbetween*(4+beforeOne+beforeTwo);
							break;


					default:
						break;
					}
					flags[3]=0;
				    setTrackColor();
					
				}
				else if(permissionModle.isIMEI()&&(flags[4]==5)){
					switch (j) {
					case 0:	
							y1=verbetween*5;
							beforeOne=5;
							break;
					case 1: 
		    				y2=verbetween*(5+beforeOne);
		    				beforeTwo=5;
		    				break;
					case 2: 
							y3=verbetween*(5+beforeOne+beforeTwo);
							break;


					default:
						break;
					}
					flags[4]=0;
					setIMEIColor();
					
				}
				else if(permissionModle.isRoot()&&(flags[5]==6)){
					switch (j) {
					case 0:	
							y1=verbetween*6;
							beforeOne=6;
							break;
					case 1: 
		    				y2=verbetween*(6+beforeOne);
		    				beforeTwo=6;
		    				break;
					case 2: 
							y3=verbetween*(6+beforeOne+beforeTwo);
							break;

					default:
						break;
					}
					flags[5]=0;
					setRootColor();
					
				}
				else if(permissionModle.isMonitorCall()&&(flags[6]==7)){
					switch (j) {
					case 0:	
							y1=verbetween*7;
							beforeOne=7;
							break;
					case 1: 
		    				y2=verbetween*(7+beforeOne);
		    				beforeTwo=7;
		    				break;
					case 2: 
							y3=verbetween*(7+beforeOne+beforeTwo);
							break;


					default:
						break;
					}
					flags[6]=0;
				    setMonitorCallColor();
					
				}
				if(j==0){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x1, bottomBound-y1, mPaint);}
				if(j==1){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x2, bottomBound-y2, mPaint);}
				if(j==2){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x3, bottomBound-y3, mPaint);}
				j++;
				
			}
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.BLUE);
			canvas.drawLine(leftBound+x1, bottomBound-y1, leftBound+x2, bottomBound-y2, mPaint);
			canvas.drawLine(leftBound+x2, bottomBound-y2, leftBound+x3, bottomBound-y3, mPaint);
		}
		//App取得两个权限
		if(counter==2){
			float x1=0,y1=0,x2=0,y2=0;
			int j=0;
			int beforeOne=0;
			int[] flags={1,2,3,4,5,6,7};
			x1=horbetween*1;
			x2=horbetween*2;
			while(j<2){
				if(permissionModle.isSendMsg()&&(flags[0]==1)){
                    y1=verbetween*1;
					flags[0]=0;
					beforeOne=1;
					setSendMsgColor();
				}
				
				else if(permissionModle.isReadCtc()&&(flags[1]==2)){
					switch (j) {
					case 0:	y1=verbetween*2;
							beforeOne=2;
							break;
					case 1: y2=verbetween*(2+beforeOne);
						    break;
					default:
						break;
					}
					flags[1]=0;
					setReadCtcColor();
				}
				else if(permissionModle.isReadPhone()&&(flags[2]==3)){
					switch (j) {
					case 0: y1=verbetween*3;
					        beforeOne=3;
							break;
					case 1: y2=verbetween*(3+beforeOne);
						    break;

					default:
						break;
					}
					flags[2]=0;
					setReadPhoneColor();
				}
				else if(permissionModle.isTrack()&&(flags[3]==4)){
					switch (j) {
					case 0:	y1=verbetween*4;
					        beforeOne=4;
							break;
					case 1: y2=verbetween*(4+beforeOne);
						    break;

					default:
						break;
					}
					flags[3]=0;
					setTrackColor();
					
				}
				else if(permissionModle.isIMEI()&&(flags[4]==5)){
					switch (j) {
					case 0:	y1=verbetween*5;
					        beforeOne=5;
							break;
					case 1: y2=verbetween*(5+beforeOne);
						    break;

					default:
						break;
					}
					flags[4]=0;	
					setIMEIColor();
				}
				else if(permissionModle.isRoot()&&(flags[5]==6)){
					switch (j) {
					case 0:	y1=verbetween*6;
					        beforeOne=6;
							break;
					case 1: y2=verbetween*(6+beforeOne);
						    break;

					default:
						break;
					}
					flags[5]=0;
					setRootColor();
					
				}
				else if(permissionModle.isMonitorCall()&&(flags[6]==7)){
					switch (j) {
					case 0:	y1=verbetween*7;
					        beforeOne=7;
							break;
					case 1: y2=verbetween*(7+beforeOne);
						    break;

					default:
						break;
					}
					flags[6]=0;
					setMonitorCallColor();
					
				}
				if(j==0){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x1, bottomBound-y1, mPaint);}
				if(j==1){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x2, bottomBound-y2, mPaint);}
				j++;
			}
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.BLUE);
			canvas.drawLine(leftBound+x1, bottomBound-y1, leftBound+x2, bottomBound-y2, mPaint);
		}
		//App获得四个权限
		if(counter==4){
			float x1=0,y1=0,x2=0,y2=0,x3=0,y3=0,x4=0,y4=0;
			int j=0;
			int beforeOne=0,beforeTwo=0,beforeThree=0;
			int[] flags={1,2,3,4,5,6,7};
			x1=horbetween*1;x2=horbetween*2;x3=horbetween*3;x4=horbetween*4;
			while(j<4){
				if(permissionModle.isSendMsg()&&(flags[0]==1)){
					y1=verbetween*1;
					beforeOne=1;
					flags[0]=0;
					setSendMsgColor();
				
				}
				
				else if(permissionModle.isReadCtc()&&(flags[1]==2)){
					switch (j) {
					case 0:	
							y1=verbetween*2;
							beforeOne=2;
							break;
					case 1: 
				    		y2=verbetween*(2+beforeOne);
				    		beforeTwo=2;
				    		break;
					case 2: 
							y3=verbetween*(2+beforeOne+beforeTwo);
							break;
					case 3: 
							y4=verbetween*(2+beforeOne+beforeTwo+beforeThree);
							break;

					default:
						break;
					}
					flags[1]=0;
					setReadCtcColor();
					
				}
				else if(permissionModle.isReadPhone()&&(flags[2]==3)){
					switch (j) {
					case 0:	
							y1=verbetween*3;
							beforeOne=3;
							break;
					case 1: 
							y2=verbetween*(3+beforeOne);
							beforeTwo=3;
							break;
					case 2: 
							y3=verbetween*(3+beforeOne+beforeTwo);
							beforeThree=3;
							break;
					case 3: 
							y4=verbetween*(3+beforeOne+beforeTwo+beforeThree);
							break;


					default:
						break;
					}
					flags[2]=0;
					setReadPhoneColor();
					
				}
				else if(permissionModle.isTrack()&&(flags[3]==4)){
					switch (j) {
					case 0:	
							y1=verbetween*4;
							beforeOne=4;
							break;
					case 1: 
							y2=verbetween*(4+beforeOne);
							beforeTwo=4;
					break;
					case 2: 
							y3=verbetween*(4+beforeOne+beforeTwo);
							beforeThree=4;
					break;
					case 3: 
							y4=verbetween*(4+beforeOne+beforeTwo+beforeThree);
							break;



					default:
						break;
					}
					flags[3]=0;
				    setTrackColor();
					
				}
				else if(permissionModle.isIMEI()&&(flags[4]==5)){
					switch (j) {
					case 0:	
							y1=verbetween*5;
							beforeOne=5;
							break;
					case 1: 
		    				y2=verbetween*(5+beforeOne);
		    				beforeTwo=5;
		    				break;
					case 2: 
							y3=verbetween*(5+beforeOne+beforeTwo);
							beforeThree=5;
							break;
					case 3: 
							y4=verbetween*(5+beforeOne+beforeTwo+beforeThree);
							break;
					default:
						break;
					}
					flags[4]=0;
					setIMEIColor();
					
				}
				else if(permissionModle.isRoot()&&(flags[5]==6)){
					switch (j) {
					case 0:	
							y1=verbetween*6;
							beforeOne=6;
							break;
					case 1: 
    						y2=verbetween*(6+beforeOne);
    						beforeTwo=6;
    						break;
					case 2: 
							y3=verbetween*(6+beforeOne+beforeTwo);
							beforeThree=6;
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					default:
						break;
					}
					flags[5]=0;
					setRootColor();
				}
				else if(permissionModle.isMonitorCall()&&(flags[6]==7)){
					switch (j) {
					case 0:	
							y1=verbetween*7;
							break;
					case 1: 
							y2=verbetween*(7+beforeOne);
							break;
					case 2: 
							y3=verbetween*(7+beforeOne+beforeTwo);
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;


					default:
						break;
					}
					flags[6]=0;
				    setMonitorCallColor();
					
				}
				if(j==0){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x1, bottomBound-y1, mPaint);}
				if(j==1){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x2, bottomBound-y2, mPaint);}
				if(j==2){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x3, bottomBound-y3, mPaint);}
				if(j==3){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x4, bottomBound-y4, mPaint);}
				j++;
			}
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.BLUE);
			canvas.drawLine(leftBound+x1, bottomBound-y1, leftBound+x2, bottomBound-y2, mPaint);
			canvas.drawLine(leftBound+x2, bottomBound-y2, leftBound+x3, bottomBound-y3, mPaint);
			canvas.drawLine(leftBound+x3, bottomBound-y3, leftBound+x4, bottomBound-y4, mPaint);
		}
		//app获得6个权限
		if(counter==6){
			float x1=0,y1=0,x2=0,y2=0,x3=0,y3=0,x4=0,y4=0,x5=0,y5=0,x6=0,y6=0;
			int j=0;
			int beforeOne=0,beforeTwo=0,beforeThree=0,beforeFour=0,beforeFive=0;
			int[] flags={1,2,3,4,5,6,7};
			x1=horbetween*1;x2=horbetween*2; x3=horbetween*3;x4=horbetween*4;x5=horbetween*5;x6=horbetween*6;
			while(j<6){
				if(permissionModle.isSendMsg()&&(flags[0]==1)){
					
					y1=verbetween*1;
					beforeOne=1;
					flags[0]=0;
				    setSendMsgColor();
				}
				
				else if(permissionModle.isReadCtc()&&(flags[1]==2)){
					switch (j) {
					case 0:	
							y1=verbetween*2;
							beforeOne=2;
							break;
					case 1: 
				    		y2=verbetween*(2+beforeOne);
				    		beforeTwo=2;
				    		break;
					default:
						break;
					}
					flags[1]=0;
					setReadCtcColor();
					
				}
				else if(permissionModle.isReadPhone()&&(flags[2]==3)){
					switch (j) {
					case 0:	
							y1=verbetween*3;
							beforeOne=3;
							break;
					case 1: 
							y2=verbetween*(3+beforeOne);
							beforeTwo=3;
							break;
					case 2: 
							y3=verbetween*(3+beforeOne+beforeTwo);
							beforeThree=3;
							break;

					default:
						break;
					}
					flags[2]=0;
					setReadPhoneColor();
					
				}
				else if(permissionModle.isTrack()&&(flags[3]==4)){
					switch (j) {
					case 0:	
							y1=verbetween*4;
							beforeOne=4;
							break;
					case 1: 
							y2=verbetween*(4+beforeOne);
							beforeTwo=4;
					break;
					case 2: 
							y3=verbetween*(4+beforeOne+beforeTwo);
							beforeThree=4;
					break;
					case 3: 
							y4=verbetween*(4+beforeOne+beforeTwo+beforeThree);
							beforeFour=4;
							break;



					default:
						break;
					}
					flags[3]=0;
				    setTrackColor();
					
				}
				else if(permissionModle.isIMEI()&&(flags[4]==5)){
					switch (j) {
					case 0:	
							y1=verbetween*5;
							beforeOne=5;
							break;
					case 1: 
		    				y2=verbetween*(5+beforeOne);
		    				beforeTwo=5;
		    				break;
					case 2: 
							y3=verbetween*(5+beforeOne+beforeTwo);
							beforeThree=5;
							break;
					case 3: 
							y4=verbetween*(5+beforeOne+beforeTwo+beforeThree);
							beforeFour=5;
							break;
					case 4: 
					        y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
					        beforeFive=5;
					default:
						break;
					}
					flags[4]=0;
					setIMEIColor();
					
				}
				else if(permissionModle.isRoot()&&(flags[5]==6)){
					switch (j) {
					case 0:	
							y1=verbetween*6;
							beforeOne=6;
							break;
					case 1: 
    						y2=verbetween*(6+beforeOne);
    						beforeTwo=6;
    						break;
					case 2: 
							y3=verbetween*(6+beforeOne+beforeTwo);
							beforeThree=6;
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					case 4: 
							y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
							beforeFive=5;
							break;
					case 5: 
							y6=verbetween*(6+beforeOne+beforeTwo+beforeThree+beforeFour+beforeFive);
							break;
					default:
						break;
					}
					flags[5]=0;
					setRootColor();
				}
				else if(permissionModle.isMonitorCall()&&(flags[6]==7)){
					switch (j) {
					case 0:	
							y1=verbetween*7;
							break;
					case 1: 
							y2=verbetween*(7+beforeOne);
							break;
					case 2: 
							y3=verbetween*(7+beforeOne+beforeTwo);
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					case 4: 
			                y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
			                beforeFive=5;
			                break;
					case 5: 
							y6=verbetween*(7+beforeOne+beforeTwo+beforeThree+beforeFour+beforeFive);
							break;


					default:
						break;
					}
					flags[6]=0;
				    setMonitorCallColor();
					
				}
				if(j==0){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x1, bottomBound-y1, mPaint);}
				if(j==1){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x2, bottomBound-y2, mPaint);}
				if(j==2){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x3, bottomBound-y3, mPaint);}
				if(j==3){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x4, bottomBound-y4, mPaint);}
				if(j==4){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x5, bottomBound-y5, mPaint);}
				if(j==5){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x6, bottomBound-y6, mPaint);}
				j++;
			}
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.BLUE);
			canvas.drawLine(leftBound+x1, bottomBound-y1, leftBound+x2, bottomBound-y2, mPaint);
			canvas.drawLine(leftBound+x2, bottomBound-y2, leftBound+x3, bottomBound-y3, mPaint);
			canvas.drawLine(leftBound+x3, bottomBound-y3, leftBound+x4, bottomBound-y4, mPaint);
			canvas.drawLine(leftBound+x4, bottomBound-y4, leftBound+x5, bottomBound-y5, mPaint);
			canvas.drawLine(leftBound+x5, bottomBound-y5, leftBound+x6, bottomBound-y6, mPaint);
		}
		//App获得5个权限
		if(counter==5){
			float x1=0,y1=0,x2=0,y2=0,x3=0,y3=0,x4=0,y4=0,x5=0,y5=0;
			int j=0;
			int beforeOne=0,beforeTwo=0,beforeThree=0,beforeFour=0;
			int[] flags={1,2,3,4,5,6,7};
			x1=horbetween*1;x2=horbetween*2;x3=horbetween*3;x4=horbetween*4; x5=horbetween*5;
			while(j<5){
				if(permissionModle.isSendMsg()&&(flags[0]==1)){
					y1=verbetween*1;
					beforeOne=1;
					flags[0]=0;
					setSendMsgColor();
				
				}
				
				else if(permissionModle.isReadCtc()&&(flags[1]==2)){
					switch (j) {
					case 0:	
							y1=verbetween*2;
							beforeOne=2;
							break;
					case 1: 
				    		y2=verbetween*(2+beforeOne);
				    		beforeTwo=2;
				    		break;
					default:
						break;
					}
					flags[1]=0;
					setReadCtcColor();
					
				}
				else if(permissionModle.isReadPhone()&&(flags[2]==3)){
					switch (j) {
					case 0:	
							y1=verbetween*3;
							beforeOne=3;
							break;
					case 1: 
							y2=verbetween*(3+beforeOne);
							beforeTwo=3;
							break;
					case 2: 
							y3=verbetween*(3+beforeOne+beforeTwo);
							beforeThree=3;
							break;

					default:
						break;
					}
					flags[2]=0;
					setReadPhoneColor();
					
				}
				else if(permissionModle.isTrack()&&(flags[3]==4)){
					switch (j) {
					case 0:	
							y1=verbetween*4;
							beforeOne=4;
							break;
					case 1: 
							y2=verbetween*(4+beforeOne);
							beforeTwo=4;
					break;
					case 2: 
							y3=verbetween*(4+beforeOne+beforeTwo);
							beforeThree=4;
					break;
					case 3: 
							y4=verbetween*(4+beforeOne+beforeTwo+beforeThree);
							beforeFour=4;
							break;



					default:
						break;
					}
					flags[3]=0;
				    setTrackColor();
					
				}
				else if(permissionModle.isIMEI()&&(flags[4]==5)){
					switch (j) {
					case 0:	
							y1=verbetween*5;
							beforeOne=5;
							break;
					case 1: 
		    				y2=verbetween*(5+beforeOne);
		    				beforeTwo=5;
		    				break;
					case 2: 
							y3=verbetween*(5+beforeOne+beforeTwo);
							beforeThree=5;
							break;
					case 3: 
							y4=verbetween*(5+beforeOne+beforeTwo+beforeThree);
							beforeFour=5;
							break;
					case 4:
					        y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
					default:
						break;
					}
					flags[4]=0;
					setIMEIColor();
					
					
				}
				else if(permissionModle.isRoot()&&(flags[5]==6)){
					switch (j) {
					case 0:	
							y1=verbetween*6;
							beforeOne=6;
							break;
					case 1: 
    						y2=verbetween*(6+beforeOne);
    						beforeTwo=6;
    						break;
					case 2: 
							y3=verbetween*(6+beforeOne+beforeTwo);
							beforeThree=6;
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					case 4: 
							y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
					default:
						break;
					}
					flags[5]=0;
					setRootColor();
				}
				else if(permissionModle.isMonitorCall()&&(flags[6]==7)){
					switch (j) {
					case 0:	
							y1=verbetween*7;
							break;
					case 1: 
							y2=verbetween*(7+beforeOne);
							break;
					case 2: 
							y3=verbetween*(7+beforeOne+beforeTwo);
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					case 4: 
			                y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);


					default:
						break;
					}
					flags[6]=0;
				    setMonitorCallColor();
					
				}
				if(j==0){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x1, bottomBound-y1, mPaint);}
				if(j==1){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x2, bottomBound-y2, mPaint);}
				if(j==2){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x3, bottomBound-y3, mPaint);}
				if(j==3){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x4, bottomBound-y4, mPaint);}
				if(j==4){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x5, bottomBound-y5, mPaint);}
				j++;
			}
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.BLUE);
			canvas.drawLine(leftBound+x1, bottomBound-y1, leftBound+x2, bottomBound-y2, mPaint);
			canvas.drawLine(leftBound+x2, bottomBound-y2, leftBound+x3, bottomBound-y3, mPaint);
			canvas.drawLine(leftBound+x3, bottomBound-y3, leftBound+x4, bottomBound-y4, mPaint);
			canvas.drawLine(leftBound+x4, bottomBound-y4, leftBound+x5, bottomBound-y5, mPaint);
		}
		//App获得7个权限
		if(counter==7){
			float x1=0,y1=0,x2=0,y2=0,x3=0,y3=0,x4=0,y4=0,x5=0,y5=0,x6=0,y6=0,x7=0,y7=0;
			int j=0;
			int beforeOne=0,beforeTwo=0,beforeThree=0,beforeFour=0,beforeFive=0,beforeSix=0;
			int[] flags={1,2,3,4,5,6,7};
			x1=horbetween*1;x2=horbetween*2; x3=horbetween*3;x4=horbetween*4;x5=horbetween*5;x6=horbetween*6;
			x7=horbetween*7;
			while(j<7){
				if(permissionModle.isSendMsg()&&(flags[0]==1)){
					y1=verbetween*1;
					beforeOne=1;
					flags[0]=0;
					setSendMsgColor();
				
				}
				
				else if(permissionModle.isReadCtc()&&(flags[1]==2)){
					switch (j) {
					case 0:	
							y1=verbetween*2;
							beforeOne=2;
							break;
					case 1: 
				    		y2=verbetween*(2+beforeOne);
				    		beforeTwo=2;
				    		break;
					default:
						break;
					}
					flags[1]=0;
					setReadCtcColor();
					
				}
				else if(permissionModle.isReadPhone()&&(flags[2]==3)){
					switch (j) {
					case 0:	
							y1=verbetween*3;
							beforeOne=3;
							break;
					case 1: 
							y2=verbetween*(3+beforeOne);
							beforeTwo=3;
							break;
					case 2: 
							y3=verbetween*(3+beforeOne+beforeTwo);
							beforeThree=3;
							break;

					default:
						break;
					}
					flags[2]=0;
					setReadPhoneColor();
					
				}
				else if(permissionModle.isTrack()&&(flags[3]==4)){
					switch (j) {
					case 0:
							y1=verbetween*4;
							beforeOne=4;
							break;
					case 1: 
							y2=verbetween*(4+beforeOne);
							beforeTwo=4;
					        break;
					case 2:
							y3=verbetween*(4+beforeOne+beforeTwo);
							beforeThree=4;
					        break;
					case 3: 
							y4=verbetween*(4+beforeOne+beforeTwo+beforeThree);
							beforeFour=4;
							break;



					default:
						break;
					}
					flags[3]=0;
				    setTrackColor();
					
				}
				else if(permissionModle.isIMEI()&&(flags[4]==5)){
					switch (j) {
					case 0:	
							y1=verbetween*5;
							beforeOne=5;
							break;
					case 1: 
		    				y2=verbetween*(5+beforeOne);
		    				beforeTwo=5;
		    				break;
					case 2: 
							y3=verbetween*(5+beforeOne+beforeTwo);
							beforeThree=5;
							break;
					case 3: 
							y4=verbetween*(5+beforeOne+beforeTwo+beforeThree);
							beforeFour=5;
							break;
					case 4: 
					        y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
					        beforeFive=5;
					default:
						break;
					}
					flags[4]=0;
					setIMEIColor();
					
				}
				else if(permissionModle.isRoot()&&(flags[5]==6)){
					switch (j) {
					case 0:	
							y1=verbetween*6;
							beforeOne=6;
							break;
					case 1: 
    						y2=verbetween*(6+beforeOne);
    						beforeTwo=6;
    						break;
					case 2: 
							y3=verbetween*(6+beforeOne+beforeTwo);
							beforeThree=6;
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					case 4: 
							y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
							beforeFive=5;
							break;
					case 5: 
							y6=verbetween*(6+beforeOne+beforeTwo+beforeThree+beforeFour+beforeFive);
							beforeSix=6;
							break;
					default:
						break;
					}
					flags[5]=0;
					setRootColor();
				}
				else if(permissionModle.isMonitorCall()&&(flags[6]==7)){
					switch (j) {
					case 0: 
							y1=verbetween*7;
							break;
					case 1: 
							y2=verbetween*(7+beforeOne);
							break;
					case 2: 
							y3=verbetween*(7+beforeOne+beforeTwo);
							break;
					case 3: 
							y4=verbetween*(6+beforeOne+beforeTwo+beforeThree);
							break;
					case 4: 
			                y5=verbetween*(5+beforeOne+beforeTwo+beforeThree+beforeFour);
			                beforeFive=5;
			                break;
					case 5: 
							y6=verbetween*(7+beforeOne+beforeTwo+beforeThree+beforeFour+beforeFive);
							break;
					case 6: 
							y7=verbetween*(7+beforeOne+beforeTwo+beforeThree+beforeFour
									+beforeFive+beforeSix);
							break;


					default:
						break;
					}
					flags[6]=0;
				    setMonitorCallColor();
					
				}
				if(j==0){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x1, bottomBound-y1, mPaint);}
				if(j==1){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x2, bottomBound-y2, mPaint);}
				if(j==2){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x3, bottomBound-y3, mPaint);}
				if(j==3){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x4, bottomBound-y4, mPaint);}
				if(j==4){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x5, bottomBound-y5, mPaint);}
				if(j==3){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x6, bottomBound-y6, mPaint);}
				if(j==4){mPaint.setStrokeWidth(8);canvas.drawPoint(leftBound+x7, bottomBound-y7, mPaint);}
				j++;
			}
			mPaint.setStrokeWidth(5);
			mPaint.setColor(Color.BLUE);
			canvas.drawLine(leftBound+x1, bottomBound-y1, leftBound+x2, bottomBound-y2, mPaint);
			canvas.drawLine(leftBound+x2, bottomBound-y2, leftBound+x3, bottomBound-y3, mPaint);
			canvas.drawLine(leftBound+x3, bottomBound-y3, leftBound+x4, bottomBound-y4, mPaint);
			canvas.drawLine(leftBound+x4, bottomBound-y4, leftBound+x5, bottomBound-y5, mPaint);
			canvas.drawLine(leftBound+x5, bottomBound-y5, leftBound+x6, bottomBound-y6, mPaint);
			canvas.drawLine(leftBound+x6, bottomBound-y6, leftBound+x7, bottomBound-y7, mPaint);
		}
	
		
		}
		
	}
	private void setSendMsgColor(){
		mPaint.setARGB(0xff, 0x57, 0x89, 0xf7);
	}
	private void setReadCtcColor() {
		mPaint.setARGB(0xff, 0x4d, 0xf7, 0x77);
	}
	private void setReadPhoneColor(){
		mPaint.setARGB(0xff, 0x92, 0xf2, 0x5a);
	}
	private void setTrackColor(){
		mPaint.setARGB(0xff, 0xed, 0x7b, 0x34);
	}
	private void setIMEIColor() {
		mPaint.setARGB(0xff, 0xf7, 0xec, 0x1e);
	}
	private void setRootColor() {
		mPaint.setARGB(0xff, 0xcc, 0xcc, 0xb0);
	}
	private void setMonitorCallColor(){
		mPaint.setARGB(0xff, 0xff, 0x6c, 0x54);
	}

}
