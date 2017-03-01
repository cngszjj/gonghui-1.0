package com.gh.util;

public class PathParameter {

	/**
	 * 主页静态文件名。
	 */
	public static final String HOMEPAGE_FILENAME = "homepage.json";// homepage的静态文件名
	/**
	 * 主页静态文件名。
	 */
	public static final String HOMEPAGE_FILENAME1 = "homepage1.json";// homepage的静态文件名
	/**
	 * 主页静态化路径
	 */
	public static final String HOMEPAGE_ROOT_PATH = "json/homepage";//跟路径
	/**
	 * 频道视频静态化路径
	 */
	public static final String STATIC_CHANNEL_VIDEO_PATH = "json/channel-video";//频道视频静态化跟路径
	/**
	 * 节目视频静态化跟路径
	 */
	public static final String STATIC_SHOW_VIDEO_PATH = "json/show-video";//节目视频静态化跟路径
	/**
	 * 专辑视频静态化跟路径
	 */
	public static final String STATIC_PLAYLIST_VIDEO_PATH = "json/playlist-video";//
	/**
	 * 节目列表静态化跟路径
	 */
	public static final String STATIC_SHOW_LIST_PATH = "json/show-list";//
	/**
	 * 专辑列表静态化跟路径
	 */
	public static final String STATIC_PLAYLIST_LIST_PATH = "json/playlist-list";//
	

	//过期时间
	
	/**
	 * 专辑列表静态化过期时间
	 */
	public static final int PLAYLIST_LIST_TIME =100*60*1000;
	/**
	 *主页静态化过期时间
	 */
	public static final int HOMEPAGE_TIME =100*1000;
	/**
	 * 节目列表过期时间
	 */
	public static final int SHOW_LIST_TIME =100*60*1000;//
	/**
	 * 频道视频静态化过期时间
	 */
	public static final int CHANNEL_VIDEO_TIME =12*60*60*1000;//专辑列表静态化过期时间
	/**
	 * 节目视频静态化过期时间
	 */
	public static final int SHOW_VIDEO_TIME =30*60*1000;//专辑列表静态化过期时间
	/**
	 * 专辑视频静态化过期时间
	 */
	public static final int PLAYLIST_VIDEO_TIME =30*60*1000;//专辑列表静态化过期时间
	
}
