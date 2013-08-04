package by.vanopiano.grodno_bus;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;
import java.util.List;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;


public class List_KOR extends ListActivity {
	SharedPreferences set;
	List<String> values;
	ListView listView;
	List<String> list = null;
	public void onCreate(Bundle icicle) {
		        super.onCreate(icicle);
		        listView = getListView();
				listView.setLongClickable(true);
				listView.setDividerHeight(2);
		    OnItemLongClickListener listener = new OnItemLongClickListener() {
				public boolean onItemLongClick(AdapterView<?> arg0, View v,
						int position, long id) {
					String itemname = (String) getListAdapter().getItem(position);
					Van.ToastBox(getApplication(),itemname + " долго нажат.");
					return false;
				}
			};
			listView.setOnItemLongClickListener(listener);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		        //String item = (String) getListAdapter().getItem(position);
		        Van.ToastBox(this, (String) getListAdapter().getItem(position));
	}
	@Override
	protected void onResume()
	{
		LoadSettings();
		Work.CheckTime();
		try {
			list=Work.Out1();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, list));
	    super.onResume();
	}
	public void LoadSettings() {
		try {
		set=getSharedPreferences("app_set",MODE_PRIVATE);
			Work.setTimetoback(set.getInt("seek_back", 0));
			Work.setTimetofuture(set.getInt("seek_future", 0));
			} catch (Exception e) {}
		
	}
}
