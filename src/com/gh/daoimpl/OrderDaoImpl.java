package com.gh.daoimpl;

import org.springframework.stereotype.Component;

import com.gh.bean.Order;
import com.gh.dao.OrderDao;

@Component("orderDao")
public class OrderDaoImpl extends GenericDaoImpl<Order, Integer> implements OrderDao{

}
