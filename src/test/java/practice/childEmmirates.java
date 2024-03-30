package practice;

public class childEmmirates extends parentAirCraft{
	public static void main(String[] args)
	{
		childEmmirates c = new childEmmirates();
		c.BodyColor();
		c.engine();
		c.safetyGuidelines();
	}

	@Override
	public void BodyColor() {
		// TODO Auto-generated method stub
		System.out.println("Red color");
	}

}
