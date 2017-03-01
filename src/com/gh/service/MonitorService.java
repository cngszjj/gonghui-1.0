package com.gh.service;

import java.util.List;

import com.gh.bean.Monitor;

public interface MonitorService {
	
	//返回list的json数据
	public String getMonitorListJson();

	/**
	 * 添加或者更新监控点状态
	 * @param name
	 * @param longitude
	 * @param latitude
	 * @param track1_up
	 * @param track1_center
	 * @param track1_down
	 * @param track1_a
	 * @param track1_b
	 * @param track2_up
	 * @param track2_center
	 * @param track2_down
	 * @param track2_a
	 * @param track2_b
	 * @param bell
	 * @param sound
	 * @param reset
	 * @param visible
	 * @param client_id
	 * @param failure

	 * @return
	 */
	public String updateOrSavaMonitor(String name, String longitude,
			String latitude, int track1_up, int track1_center,
			int track1_down, int track1_a, int track1_b,
			int track2_up, int track2_center, int track2_down,
			int track2_a, int track2_b, int bell, int sound,
			int reset, int visible, int id,
			int failure,int altpower);

	public String getMonitorInfo(int id);
}
