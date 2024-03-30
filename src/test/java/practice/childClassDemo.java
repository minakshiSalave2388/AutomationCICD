package practice;

public class childClassDemo extends parentClassDemo {

	public void engine()
	{
		System.out.println("new generation engine");
	}
	
	public void color()
	{
		System.out.println(color);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		childClassDemo cd = new childClassDemo();
		cd.Break();
		cd.color();
		cd.engine();
		cd.Gear();
	}

}
