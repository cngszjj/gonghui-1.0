package com.gh.daoimpl;

import org.springframework.stereotype.Component;

import com.gh.bean.Monitor;
import com.gh.dao.MonitorDao;

@Component("monitorDao")
public class MonitorDaoImpl extends GenericDaoImpl<Monitor, Integer> implements MonitorDao {

}
