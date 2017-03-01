package com.gh.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="t_order")
public class Order implements Serializable{

	/**
	 * 指令
	 */
	private static final long serialVersionUID = -584084505356359804L;
	
	
	private int id;
	private String name;
	private String type;
	private int monitor_id;
	private String field;
	private Date addTime;
	private int ordervalue;//命令
	private int state;//0未执行 1 已经下发到设备 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public int getOrdervalue() {
		return ordervalue;
	}
	public void setOrdervalue(int ordervalue) {
		this.ordervalue = ordervalue;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	
	

}
