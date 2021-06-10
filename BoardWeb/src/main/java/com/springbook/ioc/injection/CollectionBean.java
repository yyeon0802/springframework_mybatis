package com.springbook.ioc.injection;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.Properties;

public class CollectionBean {

	// List -> Set 변경
	// Set -> Map 변경
	// Map -> Properties 변경
	
	private Properties addressList;


	public void setAddressList(Properties addressList) {
		this.addressList = addressList;
	}
	
	public Properties getAddressList() {
		return addressList;
	}
	
	
}
