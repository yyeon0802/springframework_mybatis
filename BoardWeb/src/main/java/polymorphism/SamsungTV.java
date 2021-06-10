package polymorphism;

public class SamsungTV implements TV{ // implements ~ 인터페이스를 상속 

	private Speaker speaker; 
	private int price; //변수 생성

	/*
	public SamsungTV(Speaker speaker, int price) { //alt+shit+s 생성자 자동생성 Speaker 인터페이스 이전, SonySpeaker로 받았으나 교체함
		System.out.println("===> SamsungTV(3) 객체 생성");
		this.speaker = speaker;
		this.price = price;
	}
	*/
	
	

	/*
	public void initMethod() {
		System.out.println("객체 초기화 작업처리...");
	}
	
	public void destroyMethod() {
		System.out.println("객체 삭제 전에 처리할 로직 처리...");
	}
	*/
	
	public SamsungTV() { //이 클래스의 기본 생성자 / IoC 언제 객체가 생성이 되는지 알아본다
		System.out.println("===> SamsungTV(1) 객체 생성");
	}
	
	
	//Setter Injection	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> setSpeaker() 호출");
		this.speaker = speaker;
	}
	
	public void setPrice(int price) {
		System.out.println("===> setPrice() 호출");
		this.price = price;
	}

	
	
	public SamsungTV(Speaker speaker) { // DI 사용 
		System.out.println("===> SamsungTV(2) 객체 생성");
		this.speaker = speaker;
	}
	
	public void powerOn() {
		System.out.println("SamsungTV--전원 켠다.(가격 : " + price +")");
	}

	public void powerOff() {
		System.out.println("SamsungTV--전원 끈다.");
	}

	public void volumeUp() {
//		System.out.println("SamsungTV--소리 올린다.");
//		speaker = new SonySpeaker();	//SonySpeaker 객체를 두번이나 생성중, DI를 이용해 수정이 필요!
		speaker.volumeUp();
		
	}

	public void volumeDown() {
//		System.out.println("SamsungTV--소리 내린다.");
//		speaker = new SonySpeaker(); 	//SonySpeaker 객체를 두번이나 생성중,  DI를 이용해 수정이 필요!
		speaker.volumeDown();
	}
	
}