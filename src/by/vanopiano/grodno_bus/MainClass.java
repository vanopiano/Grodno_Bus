package by.vanopiano.grodno_bus;


import android.app.TabActivity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.TabHost.OnTabChangeListener;

@SuppressWarnings("deprecation")
public class MainClass extends TabActivity {
	TabHost tabHost;
	String lasttab;
	SharedPreferences set;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        // инициализация
        tabHost.setup();
        
        
    	LoadSettings();
        TabHost.TabSpec tabSpec;
        
        tabSpec = tabHost.newTabSpec("tab_avt").setIndicator(getText(R.string.tab_avt)).setContent(new Intent(this, List_AVT.class));
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("tab_kor").setIndicator(getText(R.string.tab_kor)).setContent(new Intent(this, List_KOR.class));        
        tabHost.addTab(tabSpec);
       
        tabSpec = tabHost.newTabSpec("tab_settings").setIndicator("Настройки").setContent(new Intent(this, SettingsActivity.class));        
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("tab_prefs").setIndicator("Настройки").setContent(new Intent(this, PrefsActivity.class));        
        tabHost.addTab(tabSpec);
        
        //вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag(lasttab);
       
        
        
        
     // обработчик переключения вкладок
    OnTabChangeListener tabChanged = new OnTabChangeListener() {
        public void onTabChanged(String tabId)
        		{
        	//хорошо бы сохранить настройки,
        	//допустим настройки отображения и т.д.
        	//
           // Van.ToastBox(getApplication(), "сменилось");
          		}
            };
         // добавляем его к табам
            tabHost.setOnTabChangedListener(tabChanged);
    }
    public void LoadSettings() {
		try {
		set=getSharedPreferences("app_set",MODE_PRIVATE);
			//Work.setTimetoback(set.getInt("seek_back", 0));
			//Work.setTimetofuture(set.getInt("seek_future", 0));
			lasttab=set.getString("lasttab", "tab_avt");
        	  } catch (Exception e) {}
		
	}
    public void SaveSettings() {
		
    		set=getSharedPreferences("app_set",MODE_PRIVATE);
			Editor sete = set.edit();
			sete.putString("lasttab", tabHost.getCurrentTabTag());
			sete.commit();
	}
    @Override
	protected void onDestroy()
	{
    	SaveSettings();
		stopService(new Intent(this, SheduleService.class));
		super.onDestroy();
		//Van.ToastBox(getApplication(), "приложение реально закрыто",4);
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      /*
	  getMenuInflater().inflate(R.menu.activity_bus_main, menu);
        return true;
		*/
      //menu.add(0, 1, 0, "Добавить");
      menu.add(0, 2, 0, "О программе");
      menu.add(1, 3, 0, "Настройки");
      menu.add(1, 4, 0, "Выход");
     
      
      return super.onCreateOptionsMenu(menu);
	  
    }
    
    // обновление меню
  
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
      //CheckBox chb = new CheckBox(getApplication());
    	//chb.setChecked(true);
    	//пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
    	//menu.setGroupVisible(1, chb.isChecked());
    	menu.setGroupVisible(1, true);
    	return super.onPrepareOptionsMenu(menu);
    }

    // обработка нажатий
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
     switch (item.getItemId())
     {
     case 2:
    	 //tabHost.setCurrentTabByTag("tab_help");
    	 Intent intent = new Intent(this, HelpActivity.class);
    	 startActivity(intent);
    	 ///////
    	 return true;
     //case 2:
    	 //
    	 //tabHost.setCurrentTabByTag("tab_help");
    	 //
    	// break;
     case 3:
    	 tabHost.setCurrentTabByTag("tab_settings");
    	 startActivity(new Intent(this, PrefsActivity.class));
    	 return true;
     case 4:
    	 //выход
    	 finish();
     }
    	
     /*
    	StringBuilder sb = new StringBuilder();

      // Выведем в TextView информацию о нажатом пункте меню 
      sb.append("Item Menu");
      sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
      sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
      sb.append("\r\n order: " + String.valueOf(item.getOrder()));
      sb.append("\r\n title: " + item.getTitle());
    Van.ToastBox(getApplication(), sb.toString());
      */
      return super.onOptionsItemSelected(item);
    }
    
    
    
}