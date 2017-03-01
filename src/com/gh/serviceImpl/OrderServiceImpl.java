package com.gh.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gh.bean.Monitor;
import com.gh.bean.Order;
import com.gh.dao.OrderDao;
import com.gh.service.OrderService;

@Component("orderService")
public class OrderServiceImpl implements OrderService{

	private OrderDao orderDao;
	
	public OrderDao getOrderDao() {
		return orderDao;
	}

	@Resource(name="orderDao")
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@SuppressWarnings("null")
	@Override
	public void addOrder(int monitor_id, String field, String type,int ordervalue) {
		// TODO Auto-generated method stub
		Order order=null;
		try {
			List<Order> list  = orderDao.find("from Order as o where o.monitor_id="+monitor_id+" and o.field='"+field+"'");
			if(list.size()>0){
				order = list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		if(null!=order){
			order.setOrdervalue(ordervalue);
			order.setAddTime(new Date());
			order.setState(1);
			orderDao.update(order);
			
		}else{
			order = new Order();
			order.setAddTime(new Date());
			order.setField(field);
			Monitor m = new Monitor();
			m.setId(monitor_id);
			order.setMonitor_id(monitor_id);
			order.setType(type);
			order.setState(0);
			order.setOrdervalue(ordervalue);
			order.setState(1);
			orderDao.save(order);
		}
		
	}

}
