package com.JY.SQLite;

public class PermissionRecord {
	String title;
	String content;
	int pkg;
	long timestamp;
	int id;
	int action;
	int type;
	
	public PermissionRecord(String title,String content,int pkg,long timestamp,int id,int action,int type){
		this.id = id;
		this.title = title;
		this.content = content;
		this.pkg = pkg;
		this.timestamp = timestamp;
		this.action = action;
		this.type = type;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public int getPkg(){
		return pkg;
	}
	
	public void setPkg(int pkg){
		this.pkg = pkg;
	}
	
	public long getTimestamp(){
		return timestamp;
	}
	
	public void setTimestamp(long timestamp){
		this.timestamp = timestamp;
	}
	
	public int getId(){
		return id;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getAction(){
		return action;
	}
	
	public void setAction(int action){
		this.action = action;
	}
	
	public int getType(){
		return type;
	}
	
	public void setType(int type){
		this.type = type;
	}

	@Override
	public String toString() {
		return "PermissionRecord [title=" + title + ", content=" + content
				+ ", pkg=" + pkg + ", timestamp=" + timestamp + ", id=" + id
				+ ", action=" + action + ", type=" + type + "]";
	}
	
}
