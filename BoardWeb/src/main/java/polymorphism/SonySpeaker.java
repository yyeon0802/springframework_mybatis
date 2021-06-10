package polymorphism;

//import org.springframework.stereotype.Component;

//@Component("sony") //객체가 자동 생성되도록 주입
public class SonySpeaker implements Speaker {

	public SonySpeaker() {
		System.out.println("===> SonySpeaker 객체 생성");
	}
	
	public void volumeUp() {
		System.out.println("SonySpeaker--소리 올린다.");
	}
	
	public void volumeDown() {
		System.out.println("SonySpeaker--소리 내린다.");
	}
}