package polymorphism;

public class BeanFactory {
	//인터페이스도 객체를 변경해야하는 번거로움이 있다. 디자인패턴을 이용하여 parameter의 값에 따라 변경되는 방법을 알아보자
	
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) {
			return new SamsungTV();
		}else if(beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
}
