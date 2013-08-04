package by.vanopiano.grodno_bus;

public class MTime {

	private int Hour, Min;

	public MTime(int hour, int minute)
	{
		Hour=hour;
		Min=minute;
	}
	public MTime(int allminutes)
	{
		Hour = (allminutes/60);
		Min = allminutes%60;
	}
	public MTime()
	{
	}
	public int getHour() {
		return Hour;
	}

	public void setHour(int hour) {
		Hour = hour;
	}
	public void setAllMin(int allminutes) {
		Hour = (allminutes/60);
		Min = allminutes%60;
	}

	public int getMin() {
		return Min;
	}
	public int getAllMin() {
		return (Hour*60)+Min;
	}

	public void setMin(int min) {
		Min = min;
	}
	public static MTime Otn (MTime one, MTime two)
	{
		//one-two
		return new MTime(one.getAllMin()-two.getAllMin());
		
	}
	
}
