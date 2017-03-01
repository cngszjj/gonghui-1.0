package com.gh.util;

import java.util.Date;

public class APIResult {

	private String add_time;
	private String upload_time;
	public APIResult() {
		super();
	}
	public APIResult(String add_time, String upload_time) {
		super();
		this.add_time = add_time;
		this.upload_time = upload_time;
	}
	public String getAdd_time() {
		return add_time;
	}
	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}
	public String getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}
	
}
