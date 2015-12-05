package com.JY.SQLite;

public class SignatureRecord {
	String appLabel;
	String pkgName;
	long signature;
	public SignatureRecord(String appLabel, String pkgName, long signature) {
		super();
		this.appLabel = appLabel;
		this.pkgName = pkgName;
		this.signature = signature;
	}
	public String getAppLabel() {
		return appLabel;
	}
	public void setAppLabel(String appLabel) {
		this.appLabel = appLabel;
	}
	public String getPkgName() {
		return pkgName;
	}
	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}
	public long getSignature() {
		return signature;
	}
	public void setSignature(long signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "SigRecord [appLabel=" + appLabel + ", pkgName=" + pkgName
				+ ", signature=" + signature + "]";
	}
	
	
	
}
