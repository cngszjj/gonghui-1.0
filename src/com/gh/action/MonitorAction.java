package com.gh.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;

import com.gh.service.MonitorService;
import com.opensymphony.xwork2.ActionSupport;


@Scope("prototype")
public class MonitorAction extends ActionSupport implements ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8574145843810667776L;
	private MonitorService monitorService;
	
	private HttpServletResponse response;
	private String name="";//名称
	private String longitude="";//经度
	private String latitude="";//纬度
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
	private int bell;//报警 1 0
	private int sound;//声音   1  关， 0 不关
	private int reset;//  1:有效  反之为零
	private int visible=1;//是否可用  1 可用，0 不可用
	private int failure;//
	private int id;//设备id
	private int altpower;//  1停电 0 正常
	
	
	
	
	
	
	public int getAltpower() {
		return altpower;
	}
	public void setAltpower(int altpower) {
		this.altpower = altpower;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
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
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}
	public MonitorService getMonitorService() {
		return monitorService;
	}
	@Resource(name="monitorService")
	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}
	public String getMonitorInfoList() {
		String json = monitorService.getMonitorListJson();
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(json.trim());
		} catch (IOException e) {
		}
		pw.flush();
		pw.close();
		return NONE;
	}
	public String getMonitorInfo() {
		String json = monitorService.getMonitorInfo(id);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(json.trim());
		} catch (IOException e) {
		}
		pw.flush();
		pw.close();
		return NONE;
	}
	public String updateMonitor(){
		String info = monitorService.updateOrSavaMonitor(
				name,longitude,latitude,track1_up,track1_center,
				track1_down,track1_a,track1_b,track2_up,track2_center,
				track2_down,track2_a,track2_b,bell,sound,reset,visible,
				id,failure,altpower);
		response.setCharacterEncoding("utf-8");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(info.trim());
		} catch (IOException e) {
		}
		pw.flush();
		pw.close();
		return NONE;
	}
	

}
