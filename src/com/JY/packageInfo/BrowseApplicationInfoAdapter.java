package com.JY.packageInfo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.JY.UI.R;

public class BrowseApplicationInfoAdapter extends BaseAdapter{

	private List<AppInfo> mlistAppInfo = null;
	LayoutInflater infater =null;
	
	//构造函数
	public BrowseApplicationInfoAdapter(Context context,List<AppInfo> apps){
		infater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mlistAppInfo = apps;
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
			view = infater.inflate(R.layout.browse_app_item, null);
			holder = new ViewHolder(view);
			view.setTag(holder);
		} 
		else {
			view = convertView ;
			holder = (ViewHolder) convertView.getTag() ;
		}
		AppInfo appInfo = (AppInfo) getItem(position);
		holder.appIcon.setImageDrawable(appInfo.getAppIcon());
		holder.tvAppLabel.setText(appInfo.getAppLabel());
		holder.tvPkgName.setText(appInfo.getPkgName());
		holder.tvAppUid.setText("" + appInfo.getUid());
		return view;
	}
	class ViewHolder{
		ImageView appIcon;
		TextView tvAppLabel;
		TextView tvPkgName;
		TextView tvAppUid;
		public ViewHolder(View view){
			this.appIcon = (ImageView)view.findViewById(R.id.imgApp);
			this.tvAppLabel = (TextView)view.findViewById(R.id.tvAppLabel);
			this.tvPkgName = (TextView)view.findViewById(R.id.tvPkgName);
			this.tvAppUid = (TextView)view.findViewById(R.id.tvAppUid);
		}
	}

}
