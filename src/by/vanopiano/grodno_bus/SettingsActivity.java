package by.vanopiano.grodno_bus;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SettingsActivity extends Activity {

		// GUI controls
		SeekBar seek_back;
		TextView seek_1;
		TextView seek_2;
		SeekBar seek_future;
		Button DeleteProgBTN, StartSB, StopSB;//,SaveClose;
		SharedPreferences set;
		Editor sete;
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings_layout);
	    seek_1 = (TextView) findViewById(R.id.seek_1);
	    seek_2 = (TextView) findViewById(R.id.seek_2);
	    //SaveClose = (Button) findViewById(R.id.button1);
	    DeleteProgBTN = (Button) findViewById(R.id.button1);
		StartSB = (Button) findViewById(R.id.startbtn);
		StopSB = (Button) findViewById(R.id.stopbtn);
	    seek_future = (SeekBar) findViewById(R.id.seek_future);
	    seek_future.setMax(901);//максимум 15 часов
	    seek_back = (SeekBar) findViewById(R.id.seek_back);
	    seek_back.setMax(60);
	    seek_back.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
		
	    

			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				seek_1.setText("¬зг€д в прошлое: "+ Integer.toString(arg1)+ " мин.");
				//Van.ToastBox(getApplicationContext(), Integer.toString(arg0.getId()));
				// TODO Auto-generated method stub
				
			}
			public void onStartTrackingTouch(SeekBar arg0) {
			}
			public void onStopTrackingTouch(SeekBar arg0) {
				SaveS();
			}
	    });

DeleteProgBTN.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		Uri packageURI = Uri.parse("package:by.vanopiano.grodno_bus"); 
        Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
        startActivity(uninstallIntent);
	}
});

StartSB.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		startService(new Intent(this, SheduleService.class));
	}
});
StopSB.setOnClickListener(new OnClickListener() {
	
	public void onClick(View arg0) {
		stopService(new Intent(this, SheduleService.class));
	}
});


	    seek_future.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
				if (arg1<60)
				seek_2.setText("¬зг€д в будущее: "+ Integer.toString(arg1)+ " мин.");
				else if (arg1<90) seek_2.setText("¬зг€д в будущее: 1 час");
				else if (arg1<120) seek_2.setText("¬зг€д в будущее: 1 час 30 мин.");
				else if (arg1<180) seek_2.setText("¬зг€д в будущее: 2 часа");
				else if (arg1<240) seek_2.setText("¬зг€д в будущее: 3 часа");
				else if (arg1<300) seek_2.setText("¬зг€д в будущее: 4 часа");
				else if (arg1<360) seek_2.setText("¬зг€д в будущее: 5 часов");
				else if (arg1<420) seek_2.setText("¬зг€д в будущее: 6 часов");
				else if (arg1<480) seek_2.setText("¬зг€д в будущее: 7 часов");
				else if (arg1<540) seek_2.setText("¬зг€д в будущее: 8 часов");
				else if (arg1<600) seek_2.setText("¬зг€д в будущее: 9 часов");
				else if (arg1<660) seek_2.setText("¬зг€д в будущее: 10 часов");
				else if (arg1<720) seek_2.setText("¬зг€д в будущее: 11 часов");
				else if (arg1<780) seek_2.setText("¬зг€д в будущее: 12 часов");
				else if (arg1<840) seek_2.setText("¬зг€д в будущее: 13 часов");
				else if (arg1<900) seek_2.setText("¬зг€д в будущее: 14 часов");
				else seek_2.setText("¬зг€д в будущее: 15 часов");
			}
			public void onStartTrackingTouch(SeekBar arg0) {
			}
			public void onStopTrackingTouch(SeekBar arg0) {
				SaveS();
			}
	    });
	    LoadS();
	    
}// onCreate
	
	public void SaveS() {
		set = getSharedPreferences("app_set",MODE_PRIVATE);
	    sete = set.edit();
	    sete.putInt("seek_back", seek_back.getProgress());
	    sete.putInt("seek_future", seek_future.getProgress());
	    sete.commit();
	}
	public void LoadS() {
		set=getSharedPreferences("app_set",MODE_PRIVATE);
	    seek_back.setProgress(set.getInt("seek_back", 0));//Work.getTimetoback());
	    seek_future.setProgress(set.getInt("seek_future", 0));//Work.getTimetofuture());
}
	@Override
	protected void onPause()
	{
		super.onPause();
		SaveS();
		//Van.ToastBox(getApplication(), "Ќастройки сохранены");
		//сохранить в файл
	}
	@Override
	protected void onResume()
	{
		super.onResume();
	//	Van.ToastBox(getApplication(), "продолжаетс€ HELP",4);
	}
	@Override
	protected void onDestroy()
	{
		
		
		super.onDestroy();
	//	Van.ToastBox(getApplication(), "закрыт HELP",4);
	}
}
