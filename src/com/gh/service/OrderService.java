package com.gh.service;

public interface OrderService {

	/**
	 * ���ָ��
	 * @param monitor_id
	 * @param field
	 * @param type
	 */
	void addOrder(int monitor_id, String field, String type,int ordervalue);

}
