package polymorphism;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")//id(bean없이 하기위해 "tv"값 설정)
public class LgTV implements TV{
	
	@Autowired 				// speaker에 apple sony 두개의 객체가 있기 때문에 autowired만으로는 error
//	@Qualifier("apple") 	//appleSpeaker 지정
	private Speaker speaker; //autowired를 통하여 sony speaker로 대체할 예정
	
	public LgTV() { //IoC 언제 객체가 생성이 되는지 알아본다
		System.out.println("===> LgTV 객체 생성");
	}
	
	public void powerOn() {
		System.out.println("LgTV--전원 켠다.");
	}

	public void powerOff() {
		System.out.println("LgTV--전원 끈다.");
	}

	public void volumeUp() {
		speaker.volumeUp();
//		System.out.println("LgTV--소리 올린다.");
	}

	public void volumeDown() {
		speaker.volumeDown();
//		System.out.println("LgTV--소리 내린다.");
	}
	
	
	
}
	
	
	

