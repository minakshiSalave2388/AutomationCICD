package practice;

public class AustralianTraffic implements centralTraffic {
	public static void main(String[] args)
	{
		AustralianTraffic a = new AustralianTraffic();
		a.greenGo();
		a.redStop();
		a.flashYellow();
	}

	@Override
	public void greenGo() {
		// TODO Auto-generated method stub
		System.out.println("green go implementtion");
		
	}

	@Override
	public void redStop() {
		// TODO Auto-generated method stub
		System.out.println("red stop implementtion");
	}

	@Override
	public void flashYellow() {
		// TODO Auto-generated method stub
		System.out.println("flash yellow implementtion");
		
	}

}
