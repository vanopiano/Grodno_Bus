package by.vanopiano.grodno_bus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpActivity extends Activity {

	Button btnSMS;
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab_help);
	    
	    
	    
	    btnSMS = (Button) findViewById(R.id.button1);
	    btnSMS.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				 Intent smsIntent = new Intent(Intent.ACTION_VIEW);
				    smsIntent.setType("vnd.android-dir/mms-sms");
				    smsIntent.putExtra("address", "+375293162647"); // C версии 2.2 можно отправлять множеству получателей
				    smsIntent.putExtra("sms_body","De_Vano, спасибо за программу!!");
				    startActivity(smsIntent);
			}
		});
	    
	    
	   
	    
	}
	@Override
	protected void onPause()
	{
		super.onPause();
		//Van.ToastBox(getApplication(), "на паузе HELP");
	}
	@Override
	protected void onResume()
	{
		super.onResume();
	//	Van.ToastBox(getApplication(), "продолжается HELP",4);
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	//	Van.ToastBox(getApplication(), "закрыт HELP",4);
	}
}
