package com.gh.util;


public class CommonUtil {
	/**
	 * 来自pc分享
	 */
	public static final String FROMTYPE_PC_SHARE = "pc_share";//
	//设备正常运行
	public static final int TRACK_INFO_NORMAL = 0;
	//报警器开
	public static final int TRACK_INFO_BELL = 2;
	//设备超过一定时间没有和服务器通信，当作断开
	public static final int TRACK_INFO_DISCON = 1;
	public static final String TRACK_INFO_FAULT1 = "故障1";
	public static final String TRACK_INFO_FAULT2 = "故障2";
	public static final String TRACK_INFO_FAULT3 = "故障3";
	public static final String TRACK_INFO_FAULT4 = "故障4";
	public static final String TRACK_INFO_FAULT5 = "故障5";
	public static final String TRACK_INFO_FAULT6 = "故障6";
	
	public static final String TRACK_UP_A = "接近上接近点，方向由上到下";
	public static final String TRACK_UP_B = "接近上接近点，方向由下到上";
	public static final String TRACK_CENTER_A = "接近到达点，方向由上到下";
	public static final String TRACK_CENTER_B = "接近到达点，方向由下到上";
	public static final String TRACK_DOWN_A = "接近下接近点，方向由上到下";
	public static final String TRACK_DOWN_B = "接近下接近点，方向由下到上";

	
	
	/**
	 * 指令过期时间   毫秒数
	 */
	public static final int ORDER_OVERDUE_TIME=60*60*1000;
	/**
	 * 设备超时断开   超时时间   毫秒数
	 */
	public static final int DEVICE_CUT_TIME=10000;
	
	


	
}
