package com.gh.entity;

import java.util.Date;

public class MonitorInfo2 {

	private int id;
	private String name;
	private String latitude;
	private String longitude;
	private int alarm;//0：设备正常运行 1 设备断开 2报警
	private int track1_up;//轨道1上接近点（列车到位=1，否则为零）
	private int track1_center;//轨道1到达点  （列车到位=1，否则为零）
	private int track1_down;//轨道1下接近点（列车到位=1，否则为零）
	private int track1_a;//轨道1方向A  （由上接近点到下接近点为1，否则为零）
	private int track1_b;//轨道1方向B  （由下接近点到上接近点为1，否则为零）
	private int track2_up;//轨道2上接近点（列车到位=1，否则为零）
	private int track2_center;//轨道2到达点  （列车到位=1，否则为零）
	private int track2_down;//轨道2下接近点（列车到位=1，否则为零）
	private int track2_a;//轨道2方向A  （由上接近点到下接近点为1，否则为零）
	private int track2_b;//轨道2方向B  （由下接近点到上接近点为1，否则为零）
	private String addTime;
	private String updateTime;
	private int altpower;// 1 停电 0 正常

	public int getAltpower() {
		return altpower;
	}
	public void setAltpower(int altpower) {
		this.altpower = altpower;
	}
	private int sound;
	private int reset;
	private int bell;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public int getAlarm() {
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}
	public int getTrack1_up() {
		return track1_up;
	}
	public void setTrack1_up(int track1_up) {
		this.track1_up = track1_up;
	}
	public int getTrack1_center() {
		return track1_center;
	}
	public void setTrack1_center(int track1_center) {
		this.track1_center = track1_center;
	}
	public int getTrack1_down() {
		return track1_down;
	}
	public void setTrack1_down(int track1_down) {
		this.track1_down = track1_down;
	}
	public int getTrack1_a() {
		return track1_a;
	}
	public void setTrack1_a(int track1_a) {
		this.track1_a = track1_a;
	}
	public int getTrack1_b() {
		return track1_b;
	}
	public void setTrack1_b(int track1_b) {
		this.track1_b = track1_b;
	}
	public int getTrack2_up() {
		return track2_up;
	}
	public void setTrack2_up(int track2_up) {
		this.track2_up = track2_up;
	}
	public int getTrack2_center() {
		return track2_center;
	}
	public void setTrack2_center(int track2_center) {
		this.track2_center = track2_center;
	}
	public int getTrack2_down() {
		return track2_down;
	}
	public void setTrack2_down(int track2_down) {
		this.track2_down = track2_down;
	}
	public int getTrack2_a() {
		return track2_a;
	}
	public void setTrack2_a(int track2_a) {
		this.track2_a = track2_a;
	}
	public int getTrack2_b() {
		return track2_b;
	}
	public void setTrack2_b(int track2_b) {
		this.track2_b = track2_b;
	}
	
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public int getSound() {
		return sound;
	}
	public void setSound(int sound) {
		this.sound = sound;
	}
	public int getReset() {
		return reset;
	}
	public void setReset(int reset) {
		this.reset = reset;
	}
	public int getBell() {
		return bell;
	}
	public void setBell(int bell) {
		this.bell = bell;
	}

	
	
}
