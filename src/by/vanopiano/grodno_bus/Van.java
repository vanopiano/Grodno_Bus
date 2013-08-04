package by.vanopiano.grodno_bus;

import java.io.File;

import android.widget.Toast;
import android.content.Context;

public class Van {
	
	public static void ToastBox(Context context, String textmessage)
    {
		 Toast.makeText(context,textmessage, Toast.LENGTH_SHORT).show();
    }
	public static void ToastBoxLong(Context context, String textmessage)
    {
		 Toast.makeText(context,textmessage, Toast.LENGTH_LONG).show();
    }
    public static  void ToastBox(Context context, String textmessage, int timems)
    {
    	 Toast.makeText(context,textmessage, timems).show();
    }
    public static  void createDir(String foldername) {

        File f1 = new File(foldername); //Создаем файловую переменную
        if (!f1.exists()) { //Если папка не существует
                f1.mkdirs();  //создаем её
        }
    }
    
}
