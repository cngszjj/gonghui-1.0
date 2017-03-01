package com.gh.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_monitor")
public class Monitor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1150294839649618087L;
	private int id;
	private String name;
	private String longitude;//经度
	private String latitude;//纬度
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
	private int bell;//报警 1 0   报警继电器为1 时，报警  根据故障码来判断是否为故障报警
	private int sound;//声音   1  关， 0 不关
	private int reset;//  1:有效  反之为零
	private int visible;//是否可用  1 可用，0 不可用
	private int failure;//故障码  0:火车到时的报警
	private int altpower;//是否停电 1:停电   0：没有停电  正常
	private Date addTime;
	private Date updateTime;


	
	@Id
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

	

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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

	public int getBell() {
		return bell;
	}

	public void setBell(int bell) {
		this.bell = bell;
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

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}
	


	public int getFailure() {
		return failure;
	}

	public void setFailure(int failure) {
		this.failure = failure;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bell;
		result = prime * result + failure;
		result = prime * result + id;
		result = prime * result
				+ ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result
				+ ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + reset;
		result = prime * result + sound;
		result = prime * result + track1_a;
		result = prime * result + track1_b;
		result = prime * result + track1_center;
		result = prime * result + track1_down;
		result = prime * result + track1_up;
		result = prime * result + track2_a;
		result = prime * result + track2_b;
		result = prime * result + track2_center;
		result = prime * result + track2_down;
		result = prime * result + track2_up;
		result = prime * result + visible;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Monitor other = (Monitor) obj;
		if (bell != other.bell)
			return false;
		if (failure != other.failure)
			return false;
		if (id != other.id)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reset != other.reset)
			return false;
		if (sound != other.sound)
			return false;
		if (track1_a != other.track1_a)
			return false;
		if (track1_b != other.track1_b)
			return false;
		if (track1_center != other.track1_center)
			return false;
		if (track1_down != other.track1_down)
			return false;
		if (track1_up != other.track1_up)
			return false;
		if (track2_a != other.track2_a)
			return false;
		if (track2_b != other.track2_b)
			return false;
		if (track2_center != other.track2_center)
			return false;
		if (track2_down != other.track2_down)
			return false;
		if (track2_up != other.track2_up)
			return false;
		if (visible != other.visible)
			return false;
		return true;
	}

	public int getAltpower() {
		return altpower;
	}

	public void setAltpower(int altpower) {
		this.altpower = altpower;
	}

	


	
	

	
	
}
