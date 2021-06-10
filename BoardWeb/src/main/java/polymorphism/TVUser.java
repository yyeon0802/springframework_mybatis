package polymorphism;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class TVUser {
	
	public static void main(String[] args) {
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		// LgTV로 수정
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		/* 클래스마다 메소드가 다른경우, 일일이 메소드명을 수정해야하는 단점이 있다.
		 * 인터페이스를 상속한다면, 결합도가 낮아져, 객체만 변경한다면 메소드명 변경없이 가능하다.
		 * */
		
		/* 인터페이스도 객체를 변경해야하는 번거로움이 있다. 
		 * 디자인패턴을 이용하여 parameter의 값에 따라 변경되는 방법을 알아보자 */
		
		//run configurations -> arguments : lg, samsung : parameter값 변경
		
//		BeanFactory factory = new BeanFactory();
//		TV tv = (TV)factory.getBean(args[0]);
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
//				
		
		/*Spring IoC
		 * 
		 * applicationContext의 id 값을 사용하여 객체생성에 간편함과 유지보수에 용이점을 준다.
		 * 
		 * */
		
		// 1. Spring 컨테이너를 구동한다
		/* 1-1. applicationContext에 id값은 없어도 Class가 꼭 존재한다면 컨테이너 구동이 가능하다. 
		 	->단 id값이 없다면, 어떤 bean인지 확실히 지정이 되지 않기 때문에 식별이 어렵다.(아래의 경우 lg samsung 둘다 올라옴)
		 	->id값이 중복이면, error
		*/
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2.Spring 컨테이너로부터 필요한 객체를 요청(Lookup)한다 
		
//		TV tv = (TV)factory.getBean("samsungTv"); // -> applicationContext의 bean id="samsungTv"
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
//		TV tv = (TV)factory.getBean("lgTv"); // -> applicationContext의 bean id="lgTv"
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();

		/*
		 * scope="singleton" => 객체를 3번 생성해도, singleton으로 객체를 한번만 생성하여 쓴다
		 * scope="prototype" => 객체 3번 생성하면 한개씩 전부 생성		
		*/ 
		
		TV tv = (TV)factory.getBean("tv"); 
//		TV tv2 = (TV)factory.getBean("tv"); 
//		TV tv3 = (TV)factory.getBean("tv"); 
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		// 3. Spring 컨테이너 종료
		factory.close();
		
		
	}
}