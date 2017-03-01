package com.gh.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.gh.bean.Monitor;
import com.gh.bean.Order;
import com.gh.dao.MonitorDao;
import com.gh.dao.OrderDao;
import com.gh.entity.MonitorInfo;
import com.gh.entity.MonitorInfo2;
import com.gh.entity.OrderVO;
import com.gh.service.MonitorService;
import com.gh.util.CommonUtil;

@Component("monitorService")
public class MonitorServiceImpl implements MonitorService{
	
	private MonitorDao monitorDao;
	private OrderDao orderDao;
	
	
	public OrderDao getOrderDao() {
		return orderDao;
	}
	@Resource(name="orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	public MonitorDao getMonitorDao() {
		return monitorDao;
	}
	@Resource(name="monitorDao")
	public void setMonitorDao(MonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}
	@Override
	public String getMonitorListJson() {
		// TODO Auto-generated method stub
		List<Monitor> list = monitorDao.find("from Monitor as m where m.visible=1");
		List<MonitorInfo> monitorInfoList = new ArrayList<MonitorInfo>();
		for(Monitor m:list){
			MonitorInfo mInfo = new MonitorInfo();
			mInfo.setId(m.getId());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			mInfo.setUpdateTime(sdf.format(m.getUpdateTime()));
			mInfo.setLatitude(m.getLatitude());
			mInfo.setLongitude(m.getLongitude());
			/*//如果报警器没有开启  故障码为0   则为正常状态情形
			if(m.getFailure()==0&&m.getBell()==0){
				mInfo.setAlarm(0);
				mInfo.setInfo(null);
			}
			//如果报警器开启   故障码为0   则为正常报警
			if(m.getFailure()==0&&m.getBell()==1){
				mInfo.setAlarm(1);
				mInfo.setInfo(CommonUtil.TRACK_INFO_PASS);
			}
			//如果报警器开启  故障码大于0   则为故障 
			if(m.getFailure()>0&&m.getBell()==1){
				mInfo.setAlarm(2);
				if(m.getFailure()==1) mInfo.setInfo(CommonUtil.TRACK_INFO_FAULT1);
				if(m.getFailure()==2) mInfo.setInfo(CommonUtil.TRACK_INFO_FAULT2);
				if(m.getFailure()==3) mInfo.setInfo(CommonUtil.TRACK_INFO_FAULT3);
			}*/
			Date d = new Date();
			if(d.getTime()-m.getUpdateTime().getTime()>100000){
				mInfo.setAlarm(CommonUtil.TRACK_INFO_DISCON);
			}else{
				if(m.getBell()==1){
					mInfo.setAlarm(CommonUtil.TRACK_INFO_BELL);
				}else {
					mInfo.setAlarm(CommonUtil.TRACK_INFO_NORMAL);
				}
			}
			monitorInfoList.add(mInfo);
			
		}
		
		
		
		
		
		return com.gh.util.JSONUtils.toJSONString(monitorInfoList);
	}
	@Override
	public String updateOrSavaMonitor(String name, String longitude,
			String latitude, int track1_up, int track1_center,
			int track1_down, int track1_a, int track1_b,
			int track2_up, int track2_center, int track2_down,
			int track2_a, int track2_b, int bell, int sound,
			int reset, int visible, int id,
			int failure,int altpower) {
		// TODO Auto-generated method stub
		boolean bool =longitude.equals("")||latitude.equals("")||id==0;
		Monitor m1 = null;
		if(!bool){
			
			Monitor m = new Monitor();
			m.setBell(bell);
			m.setId(id);
			
			m.setLatitude(latitude);
			m.setLongitude(longitude);
			m.setName(name);
			m.setReset(reset);
			m.setSound(sound);
			m.setTrack1_a(track1_a);
			m.setTrack1_b(track1_b);
			m.setTrack1_center(track1_center);
			m.setTrack1_down(track1_down);
			m.setTrack1_up(track1_up);
			m.setTrack2_a(track2_a);
			m.setTrack2_b(track2_b);
			m.setTrack2_center(track2_center);
			m.setTrack2_down(track2_down);
			m.setTrack2_up(track2_up);
			m.setVisible(visible);
			m.setFailure(failure);
			m.setAltpower(altpower);
			m1 = monitorDao.get(id);
			/*if(m1!=null&&m1.equals(m)){
				m1.setAddTime(null);
				m1.setUpdateTime(null);
				return JSONArray.fromObject(m1).toString();
			}else{*/
				if(m1!=null){//不为空，更新并提取命令
					try {
						m.setUpdateTime(new Date());
						m.setAddTime(m1.getAddTime());
						monitorDao.update(m);
						List<Order> orderList = orderDao.find("from Order as o where o.state=1 and o.monitor_id="+m1.getId());
						List<OrderVO> ovoList = new ArrayList<OrderVO>();
						for(Order o:orderList){
							OrderVO orderVO = new OrderVO();
							orderVO.setField(o.getField());
							orderVO.setId(o.getMonitor_id());
							orderVO.setOrdervalue(o.getOrdervalue());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
							orderVO.setUpdatetime(sdf.format(o.getAddTime()));
							Date d  = new Date();
							//判断指令没有超时   才会下发给设备
							if(d.getTime()-o.getAddTime().getTime()<CommonUtil.ORDER_OVERDUE_TIME) ovoList.add(orderVO);
							//更新指令状态为已读
							o.setState(0);
							orderDao.update(o);
						}
						if(ovoList.size()>0)return JSONArray.fromObject(ovoList).toString();
						else return "no order";
						
					} catch (Exception e) {
						// TODO: handle exception
						return "no order";
					}	
				}else{
					try {
						m.setAddTime(new Date());
						m.setUpdateTime(new Date());
						monitorDao.save(m);
						
						
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						
					}
					//没有指令
					return "no order";
				}
				
				
//			}
		}else{
			//提交错误
			return "post error";
		}

	}
	@Override
	public String getMonitorInfo(int id) {
		// TODO Auto-generated method stub
		
		Monitor m = monitorDao.get(id);
		if(null!=m){
			MonitorInfo2 mInfo2 = new MonitorInfo2();
			mInfo2.setId(m.getId());
			mInfo2.setBell(m.getBell());
			mInfo2.setSound(m.getSound());
			mInfo2.setReset(m.getReset());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

			mInfo2.setAddTime(sdf.format(m.getAddTime()));
			mInfo2.setUpdateTime(sdf.format(m.getUpdateTime()));
			mInfo2.setLatitude(m.getLatitude());
			mInfo2.setLongitude(m.getLongitude());
			mInfo2.setName(m.getName());
			mInfo2.setTrack1_a(m.getTrack1_a());
			mInfo2.setTrack2_a(m.getTrack2_a());
			mInfo2.setTrack1_b(m.getTrack1_b());
			mInfo2.setTrack2_b(m.getTrack2_b());
			mInfo2.setTrack1_down(m.getTrack1_down());
			mInfo2.setTrack1_up(m.getTrack1_up());
			mInfo2.setTrack1_center(m.getTrack1_center());
			mInfo2.setTrack2_down(m.getTrack2_down());
			mInfo2.setTrack2_up(m.getTrack2_up());
			mInfo2.setTrack2_center(m.getTrack2_center());
			mInfo2.setAltpower(m.getAltpower());
		
			Date d = new Date();
			if(d.getTime()-m.getUpdateTime().getTime()>CommonUtil.DEVICE_CUT_TIME){
				mInfo2.setAlarm(CommonUtil.TRACK_INFO_DISCON);
			}else{
				if(mInfo2.getBell()==1){
					mInfo2.setAlarm(CommonUtil.TRACK_INFO_BELL);
				}else {
					mInfo2.setAlarm(CommonUtil.TRACK_INFO_NORMAL);
				}
			}
			
			/*//如果报警器没有开启  故障码为0   则为正常状态情形
			if(m.getFailure()==0&&m.getBell()==0){
				mInfo2.setAlarm(0);
				mInfo2.setInfo(CommonUtil.TRACK_INFO_NORMAL);
			}
			//如果报警器开启   故障码为0   则为正常报警
			if(m.getFailure()==0&&m.getBell()==1){
				mInfo2.setAlarm(1);
				mInfo2.setInfo(CommonUtil.TRACK_INFO_PASS);
			}*/
			/*//如果报警器开启  故障码大于0   则为故障 
			if(m.getFailure()>0&&m.getBell()==1){
				mInfo2.setAlarm(2);
				if(m.getFailure()==1) mInfo2.setInfo(CommonUtil.TRACK_INFO_FAULT1);
				if(m.getFailure()==2) mInfo2.setInfo(CommonUtil.TRACK_INFO_FAULT2);
				if(m.getFailure()==3) mInfo2.setInfo(CommonUtil.TRACK_INFO_FAULT3);
			}
			if(m.getTrack1_up()==1&&m.getTrack1_a()==1) mInfo2.setTrack1Info(CommonUtil.TRACK_UP_A);
			if(m.getTrack1_up()==1&&m.getTrack1_b()==1) mInfo2.setTrack1Info(CommonUtil.TRACK_UP_B);
			if(m.getTrack1_center()==1&&m.getTrack1_a()==1) mInfo2.setTrack1Info(CommonUtil.TRACK_CENTER_A);
			if(m.getTrack1_center()==1&&m.getTrack1_b()==1) mInfo2.setTrack1Info(CommonUtil.TRACK_CENTER_B);
			if(m.getTrack1_down()==1&&m.getTrack1_a()==1) mInfo2.setTrack1Info(CommonUtil.TRACK_DOWN_A);
			if(m.getTrack1_down()==1&&m.getTrack1_b()==1) mInfo2.setTrack1Info(CommonUtil.TRACK_DOWN_B);
			
			if(m.getTrack2_up()==1&&m.getTrack2_a()==1) mInfo2.setTrack2Info(CommonUtil.TRACK_UP_A);
			if(m.getTrack2_up()==1&&m.getTrack2_b()==1) mInfo2.setTrack2Info(CommonUtil.TRACK_UP_B);
			if(m.getTrack2_center()==1&&m.getTrack2_a()==1) mInfo2.setTrack2Info(CommonUtil.TRACK_CENTER_A);
			if(m.getTrack2_center()==1&&m.getTrack2_b()==1) mInfo2.setTrack2Info(CommonUtil.TRACK_CENTER_B);
			if(m.getTrack2_down()==1&&m.getTrack2_a()==1) mInfo2.setTrack2Info(CommonUtil.TRACK_DOWN_A);
			if(m.getTrack2_down()==1&&m.getTrack2_b()==1) mInfo2.setTrack2Info(CommonUtil.TRACK_DOWN_B);
			*/
			return JSONObject.fromObject(mInfo2).toString();
		}else{
			return null;
		}

	}

	public static void main(String[] args) {
		
		try {
			Date d = new Date();
			Thread.sleep(1000);
			Date d1 = new Date();
			System.out.println(d1.getTime()-d.getTime());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
}
