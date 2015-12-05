package com.JY.packageInfo;

import java.util.List;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.JY.UI.R;
import com.JY.utils.DataTraffic;

public class BrowseTrafficAdapter extends BaseAdapter{
	private long lastTraffic = 0;
	private List<AppInfo> mlistAppInfo = null;
	LayoutInflater inflater =null;
	private Drawable up_grayDrawable;
	private Drawable down_grayDrawable;
	private Drawable upDrawable;
	private Drawable downDrawable;
	
	//构造函数
	public BrowseTrafficAdapter(Context context,List<AppInfo> apps){
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlistAppInfo = apps;
		
        Resources resources = context.getResources();
        up_grayDrawable = resources.getDrawable(R.drawable.up_gray);
        down_grayDrawable = resources.getDrawable(R.drawable.down_gray);
        upDrawable = resources.getDrawable(R.drawable.up);
        downDrawable = resources.getDrawable(R.drawable.down);
    }
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mlistAppInfo.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mlistAppInfo.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = null;
		ViewHolder holder = null;
		if (convertView == null || convertView.getTag() == null) {
			view = inflater.inflate(R.layout.browse_traffic_item, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} 
		else {
			view = convertView ;
			holder = (ViewHolder) convertView.getTag() ;
		}
		AppInfo appInfo = (AppInfo) getItem(position);
		holder.appIcon.setImageDrawable(appInfo.getAppIcon());
		
		holder.upTraffic.setText(appInfo.getIngUpTraffic()+ "");
		holder.downTraffic.setText(appInfo.getIngDownTraffic() + "");
        if(appInfo.getIngUpTraffic() == 0){
            //holder.upTrafficImg.setVisibility(View.GONE);
            holder.upTrafficImg.setImageDrawable(up_grayDrawable);
        }
        else {
//            holder.upTrafficImg.setVisibility(View.VISIBLE);
            holder.upTrafficImg.setImageDrawable(upDrawable);
        }

        if(appInfo.getIngDownTraffic() == 0){
//            holder.downTrafficImg.setVisibility(View.GONE);
            holder.downTrafficImg.setImageDrawable(down_grayDrawable);
        }
        else {
//            holder.downTrafficImg.setVisibility(View.VISIBLE);
            holder.downTrafficImg.setImageDrawable(downDrawable);
        }
		return view;
	}
	
	class ViewHolder{
		ImageView appIcon;
        ImageView upTrafficImg;
		TextView upTraffic;
        ImageView downTrafficImg;
		TextView downTraffic;
		
		public ViewHolder(View view){
			this.appIcon = (ImageView)view.findViewById(R.id.TrafficImgApp);
            this.upTrafficImg = (ImageView)view.findViewById(R.id.upTrafficImage);
			this.upTraffic = (TextView)view.findViewById(R.id.upTraffic);
			this.downTraffic = (TextView)view.findViewById(R.id.downTraffic);
            this.downTrafficImg = (ImageView)view.findViewById(R.id.downTrafficImage);
		}
	}
	
	public long getDownTraffic(int pkg){
		long currentTraffic = DataTraffic.getUidTotalRecvKB(pkg);
		if (currentTraffic ==  lastTraffic) {
			return 0;
		} else {
			long temp = currentTraffic - lastTraffic;
			lastTraffic = currentTraffic;
			return temp;
		}
	}

}
