package com.springbook.ioc.injection;

import java.util.List;
import java.util.Properties;
import java.util.Map;
import java.util.Set;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.ioc.injection.CollectionBean;

public class CollectionBeanClient {

	public static void main(String[] args) {
		
		// list -> set -> map -> Properties
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
		Properties addressList = bean.getAddressList();
		
		//Properties 일때, .toString(); 추가 => value가 정해 지지 않았기 때문에 값을 받아 String으로 변환이 필요함
		String address = addressList.get("고길동").toString();
		String address2 = addressList.get("마이콜").toString();
		
		System.out.println("고길동 주소 : " + address);
		System.out.println("마이콜 주소 : " + address2);
		
		/*
		 * for(String address : addressList) { System.out.println(address.toString()); }
		 */
		
		factory.close();
	}
}
