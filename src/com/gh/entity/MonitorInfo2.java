package com.gh.entity;

import java.util.Date;

public class MonitorInfo2 {

	private int id;
	private String name;
	private String latitude;
	private String longitude;
	private int alarm;//0���豸�������� 1 �豸�Ͽ� 2����
	private int track1_up;//���1�Ͻӽ��㣨�г���λ=1������Ϊ�㣩
	private int track1_center;//���1�����  ���г���λ=1������Ϊ�㣩
	private int track1_down;//���1�½ӽ��㣨�г���λ=1������Ϊ�㣩
	private int track1_a;//���1����A  �����Ͻӽ��㵽�½ӽ���Ϊ1������Ϊ�㣩
	private int track1_b;//���1����B  �����½ӽ��㵽�Ͻӽ���Ϊ1������Ϊ�㣩
	private int track2_up;//���2�Ͻӽ��㣨�г���λ=1������Ϊ�㣩
	private int track2_center;//���2�����  ���г���λ=1������Ϊ�㣩
	private int track2_down;//���2�½ӽ��㣨�г���λ=1������Ϊ�㣩
	private int track2_a;//���2����A  �����Ͻӽ��㵽�½ӽ���Ϊ1������Ϊ�㣩
	private int track2_b;//���2����B  �����½ӽ��㵽�Ͻӽ���Ϊ1������Ϊ�㣩
	private String addTime;
	private String updateTime;
	private int altpower;// 1 ͣ�� 0 ����

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
