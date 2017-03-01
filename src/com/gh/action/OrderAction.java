package com.gh.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.context.annotation.Scope;

import com.gh.service.OrderService;
import com.opensymphony.xwork2.ActionSupport;

@Scope("prototype")
public class OrderAction extends ActionSupport implements ServletResponseAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8572384552716943306L;

	private HttpServletResponse response;
	private OrderService orderService;
	private int monitor_id;
	private String field;
	private String type;
	private int ordervalue;
	
	
	public OrderService getOrderService() {
		return orderService;
	}

	@Resource(name="orderService")
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public int getMonitor_id() {
		return monitor_id;
	}

	public void setMonitor_id(int monitor_id) {
		this.monitor_id = monitor_id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getOrdervalue() {
		return ordervalue;
	}

	public void setOrdervalue(int ordervalue) {
		this.ordervalue = ordervalue;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}
	
	public String addOrder(){
		
		
		orderService.addOrder(monitor_id,field,type,ordervalue);
		return NONE;
	}
	
	

}
