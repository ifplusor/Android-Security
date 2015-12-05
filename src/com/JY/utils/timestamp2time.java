package com.JY.utils;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class timestamp2time {


	public static String getDate(String month, String day) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 24小时制
		java.util.Date d = new java.util.Date();
		String str = sdf.format(d);
		String nowday = str.substring(8, 10);
		String result = null;

		int temp = Integer.parseInt(nowday) - Integer.parseInt(day);
		switch (temp) {
		case 0:
			result = "今天";
			break;
		case 1:
			result = "昨天";
			break;
		case 2:
			result = "前天";
			break;
		default:
			StringBuilder sb = new StringBuilder();
			sb.append(Integer.parseInt(month) + "月");
			sb.append(Integer.parseInt(day) + "日");
			result = sb.toString();
			break;
		}
		return result;
	}

	public static String getTime(long timestamp) {
		int timestamp_int = (int)(timestamp/1000);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = null;
		try {
			java.util.Date currentdate = new java.util.Date();// 当前时间

			long i = (currentdate.getTime() / 1000 - timestamp_int) / (60);//相差的分钟数
			System.out.println(currentdate.getTime());
			System.out.println(i);
			Timestamp now = new Timestamp(System.currentTimeMillis());// 获取系统当前时间
			System.out.println("now-->" + now);// 返回结果精确到毫秒。

			String str = sdf.format(new Timestamp(IntToLong(timestamp_int)));
			System.out.println("str--->" + str);
			
			time = str.substring(11, 16);
			System.out.println("time--->" + time);
			
			String month = str.substring(5, 7);
			
			String day = str.substring(8, 10);
			System.out.println("getdata___>"+getDate(month, day));
			
			time = getDate(month, day) + time;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return time;
	}

	//java Timestamp构造函数需传入Long型
	public static long IntToLong(int i) {
		long result = (long) i;
		result *= 1000;
		return result;
	}

//	public static void main(String[] args) {
//		long timestamp = 1365822059000L; // 返回时间戳为毫秒
//		String time = timestamp2time.getTime(timestamp);
//		System.out.println("timestamp-->" + time);
//		
//	}

}