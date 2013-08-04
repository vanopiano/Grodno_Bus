
package by.vanopiano.grodno_bus;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Environment;

public class Work extends Activity{

	
	@SuppressLint("UseSparseArrays")
	private static HashMap <Integer, BusTime> db = new HashMap <Integer, BusTime> ();
	private static Integer InternalId=0;
	
	//private static String FILEAVT = Environment.getExternalStorageDirectory().getPath() + "/Android/data/grodno_bus/Bus_avt.txt";
	//private static String FILEKOR = Environment.getExternalStorageDirectory().getPath() + "/Android/data/grodno_bus/Bus_kor.txt";
	public static String DIRAVT = Environment.getExternalStorageDirectory().getPath() + "/Android/data/grodno_bus";
	//private static String DIRKOR = Environment.getExternalStorageDirectory().getPath() + "/Android/data/grodno_bus";
	private static int h;
	private static int m;
	private static int w;
	private static Calendar c;
	private static int timetoback=20;
	private static int timetofuture=500;
	public static int getTimetofuture() {
		return timetofuture;
	}

	public static void setTimetofuture(int timetofuture) {
		Work.timetofuture = timetofuture;
	}

	public static int getTimetoback() {
		return timetoback;
	}

	public static void CheckTime() {
		c = Calendar.getInstance();
		h = c.get(Calendar.HOUR_OF_DAY);
    	m = c.get(Calendar.MINUTE);
    	w = c.get(Calendar.DAY_OF_WEEK);
		return;
	}
	public static void setTimetoback(int timetoback) {
		Work.timetoback = timetoback;
	}

	//методы для работы с элементыми базы данных
	public static void AddEntry(Integer hour,Integer minutes, Integer dayofweek) {
		InternalId++;
		BusTime temp = new BusTime();
		temp.hour=hour;
		temp.min=minutes;
		temp.week=dayofweek;
		temp.id=InternalId;
		db.put(InternalId, temp);
	}
	
	public static List<String> Out0() throws IOException {
		
		List <String> temp = new ArrayList <String>();
		BusTime bus = new BusTime();
		MTime now= new MTime(h,m);
		MTime curr;
		String minutes, hmin;
		ReadFile("avt");
		
		//temp.add("Следующий автобус через: ");
		//temp.add("Сейчас: "+ Integer.toString(h)+ ":"+Integer.toString(m));
		for (int i=1; i<=(InternalId); i++) {
			bus = db.get(i);
			//проверка на неделю
			if (bus.week==1)    {
				//week="будние";
				if (w==6 && w==7)
					//week="не покажет, только будние";
				continue;		
				}
			else if (bus.week==2)    {
				//week="сб,вс";
				if (w!=6 && w!=7)
				//week="не покажет, только сб,вс";
				continue;
					}
					//else if //тут надо, чтобы все дни кроме воскресенья и такие, чтобы только по субботам
			//else week="все дни";
			//проверка на неделю
			///////////////////
			
			curr = new MTime(bus.hour, bus.min);
			
			
			//проверка на время
			if (curr.getAllMin()<now.getAllMin())
				{
				if (curr.getAllMin()>=(now.getAllMin()-timetoback))
				{//если автобус прошел n-минут назад
					//curr = MTime.Otn(now,curr);
					
					hmin=Integer.toString(now.getAllMin()-curr.getAllMin()); //подсчитанные минуты
					if (hmin.length()==1) hmin=Integer.toString(now.getAllMin()-curr.getAllMin()); //подсчитанные минуты
					
					minutes=Integer.toString(bus.min);//нормальные минуты
					if (minutes.length()==1) minutes="0"+Integer.toString(bus.min); //нормальные минуты
					//Добавляем в список
					temp.add(hmin +" мин. назад   ("+Integer.toString(bus.hour)+":"+minutes+")");
					
				//continue;
				}
				}
			else if (curr.getAllMin()<(now.getAllMin()+timetofuture))
			{
				curr = MTime.Otn(curr,now);
				minutes=Integer.toString(bus.min);//нормальные минуты
				if (minutes.length()==1) minutes="0"+Integer.toString(bus.min); //нормальные минуты
				if ((curr.getMin()==0) & (curr.getHour()==0))
				{
					hmin="Сейчас"; //подсчитанные минуты
					
					//Добавляем в список
					temp.add(hmin +"   ("+Integer.toString(bus.hour)+":"+minutes+")");
				
				}
				else {
					hmin=Integer.toString(Math.abs(curr.getMin()));
					if (hmin.length()==1) hmin="0"+Integer.toString(Math.abs(curr.getMin())); //подсчитанные минуты
					//Добавляем в список
					temp.add(Integer.toString(curr.getHour())+":"+hmin +"   ("+Integer.toString(bus.hour)+":"+minutes+")");
				}
				
				
				
				//continue;
			}
		
			
		}
		InternalId=0;
		
		return temp;
		
	}
	
	public static List<String> Out1() throws IOException {
		List <String> temp = new ArrayList <String>();
		BusTime bus = new BusTime();
		MTime now= new MTime(h,m);
		MTime curr;
		
		String minutes, hmin;
		ReadFile("kor");
		
		//temp.add("Следующий автобус через: ");
		//temp.add("Сейчас: "+ Integer.toString(h)+ ":"+Integer.toString(m));
		for (int i=1; i<=(InternalId); i++) {
			bus = db.get(i);
			//проверка на неделю
			if (bus.week==1)    {
				//week="будние";
				if (w==6 && w==7)
					//week="не покажет, только будние";
				continue;		
				}
			else if (bus.week==2)    {
				//week="сб,вс";
				if (w!=6 && w!=7)
				//week="не покажет, только сб,вс";
				continue;
					}
					//else if //тут надо, чтобы все дни кроме воскресенья и такие, чтобы только по субботам
			//else week="все дни";
			//проверка на неделю
			///////////////////
			
			curr = new MTime(bus.hour, bus.min);
			
			
			//проверка на время
			if (curr.getAllMin()<now.getAllMin())
				{
				if (curr.getAllMin()>=(now.getAllMin()-timetoback))
				{//если автобус прошел n-минут назад
					//curr = MTime.Otn(now,curr);
					
					hmin=Integer.toString(now.getAllMin()-curr.getAllMin()); //подсчитанные минуты
					if (hmin.length()==1) hmin=Integer.toString(now.getAllMin()-curr.getAllMin()); //подсчитанные минуты
					
					minutes=Integer.toString(bus.min);//нормальные минуты
					if (minutes.length()==1) minutes="0"+Integer.toString(bus.min); //нормальные минуты
					//Добавляем в список
					temp.add(hmin +" мин. назад   ("+Integer.toString(bus.hour)+":"+minutes+")");
					
				//continue;
				}
				}
			else if (curr.getAllMin()<(now.getAllMin()+timetofuture))
			{
				curr = MTime.Otn(curr,now);
				
				hmin=Integer.toString(Math.abs(curr.getMin())); //подсчитанные минуты
				if (hmin.length()==1) hmin="0"+Integer.toString(Math.abs(curr.getMin())); //подсчитанные минуты
				
				minutes=Integer.toString(bus.min);//нормальные минуты
				if (minutes.length()==1) minutes="0"+Integer.toString(bus.min); //нормальные минуты
				//Добавляем в список
				temp.add(Integer.toString(curr.getHour())+":"+hmin +"   ("+Integer.toString(bus.hour)+":"+minutes+")");
				
				//continue;
			}
		
			
		}
		InternalId=0;
		
		return temp;
	}
	
	
	public static void ReadFile(String name) throws IOException {
		
		db.clear();
		if (name.equals("avt")) {
		AddEntry(6,00,1);
		AddEntry(7,00,0);
		AddEntry(7,30,0);
		AddEntry(8,00,0);
		AddEntry(9,00,0);
		AddEntry(9,35,0);
		AddEntry(10,00,0);
		AddEntry(10,35,2);
		AddEntry(11,35,0);
		AddEntry(12,00,0);
		AddEntry(12,35,2);
		AddEntry(13,30,0);
		AddEntry(14,00,0);
		AddEntry(14,25,2);
		AddEntry(12,30,2);
		AddEntry(15,40,0);
		AddEntry(16,00,0);
		AddEntry(16,25,0);
		AddEntry(17,00,1);
		AddEntry(17,45,2);
		AddEntry(18,00,0);
		AddEntry(18,40,2);
		AddEntry(19,0,1);
		AddEntry(20,00,0);
		}
		else {
				AddEntry(6,50,1);
				AddEntry(7,15,0);
				AddEntry(8,30,0);
				AddEntry(9,30,0);
				AddEntry(9,30,0);
				AddEntry(10,10,0);
				AddEntry(10,50,0);
				AddEntry(11,10,2);
				AddEntry(12,10,0);
				AddEntry(12,50,0);
				AddEntry(13,10,2);
				AddEntry(14,05,0);
				AddEntry(14,50,0);
				AddEntry(15,05,2);
				AddEntry(16,15,2);
				AddEntry(16,25,0);
				AddEntry(16,50,0);
				AddEntry(17,00,0);
				AddEntry(17,50,1);
				AddEntry(18,50,2);
				AddEntry(19,00,0);
				AddEntry(19,15,2);
				AddEntry(19,30,1);
				AddEntry(20,30,0);
		}
		/*
		//Это берет из файла. Рабочий код.
		int hh,mm,ww;
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(new File(FILEAVT)));
		String str = "";
		// читаем содержимое
		while ((str = br.readLine()) != null)//(!str.equals("#END"+name)) 
		{
				//if (str.contains("#E")) break;
				if (str.contains(":") && str.contains(","))
				{
					hh=Integer.decode(str.substring(0, str.indexOf(":")));
					mm=Integer.decode(str.substring(str.indexOf(":")+1,str.indexOf(",")));
					ww=Integer.decode(str.substring(str.indexOf(",")+1));
					AddEntry(hh, mm, ww);
				}
		}

	}
	*/
	}
	
	
}//End of WOKR Class
	
	/*
	public static void CreateFiles()
	{
		
		File t = new File(DIRAVT);
		if (!t.exists())
		{
			Van.createDir(DIRAVT); 
		}
		
			//заполнить файл (это будет один раз за жизгь проги)
		
		
    }
    */



 
