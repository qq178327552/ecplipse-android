package _8_views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android._1_base.R;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
/**
 * ListView页面操作
 * 		数据绑定:将一个layout页面通过适配器并添加数据,绑定到一个ListView中作为其子页面显示项
 * 
 * 	1.准备待绑定的数据(需要List<Map<String,Object>>类型)
 * 	2.使用SimpleAdapter适配器数据绑定listView
 * 		2.1).找到listView
 * 		2.2).为listView提供一个适配器对象，用于绑定listView(上下文,所绑定的数据,指定item所使用的界面,指定数据绑定源,数据绑定目标)
 * 		2.3).通过适配器设置数据绑定
 * 	3.获取listView条目点击的事件
 * 		3.1).重写点击事件:(当前的listView对象,点击的条目页面对象,所绑定的数据的位置,条目在listView的位置)
 * 		3.2).获取listView中的数据条目(就是适配器data中的Map内容)
 * 
 * 	2_2.数据绑定:当数据源是游标数据时,使用SimpleCursorAdapter适配器绑定数据(其中游标中的数据必须包含"_id"字段,所以指向查询时返回数据其中一项取别名为_id)
 * 		2_2.1).创建SimpleCursorAdapter对象绑定游标数据(上下文,指定item所使用的界面,游标数据,指定数据绑定源,数据绑定目标)
 * 		2_2.2).通过适配器设置数据绑定
 * 		3_2.2).获取listView中的数据条目(就是SimpleCursorAdapter适配器data中的Cursor内容)
 * @author zheng
 *
 */
public class _4_ListView extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*1.准备待绑定的数据(需要List<Map<String,Object>>类型)*/
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("id", 1);
		map.put("amount", "10");
		data.add(map);
		/*2.使用SimpleAdapter适配器数据绑定listView*/
		/*2.1).找到listView*/
		ListView listView = (ListView)this.findViewById(R.id._8_4_listvView);
		/*2.2).为listView提供一个适配器对象，用于绑定listView(上下文,所绑定的数据,指定item所使用的界面,指定数据绑定源,数据绑定目标)*/
		SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.a8_4_listview_item, new String[]{"id","amount"}, new int[]{R.id._8_4_id,R.id._8_4_amount});
		/*2.3).通过适配器设置数据绑定*/
		listView.setAdapter(adapter);
		
		/*3.获取listView条目点击的事件*/
		listView.setOnItemClickListener(new OnItemClickListener() {
			/*3.1).重写点击事件:(当前的listView对象,点击的条目页面对象,所绑定的数据的位置,条目在listView的位置)*/
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				/*3.2).获取listView中的数据条目(就是SimpleAdapter适配器data中的Map内容)*/
				ListView listView =(ListView)parent;
				HashMap<String,Object> item = (HashMap<String,Object>)listView.getItemAtPosition(position);
				
				/*3_2.2).获取listView中的数据条目(就是SimpleCursorAdapter适配器data中的Cursor内容)*/
				Cursor cursorItem = (Cursor)listView.getItemAtPosition(position);
			}
		});
		
		/*2_2.数据绑定:当数据源是游标数据时,使用SimpleCursorAdapter适配器绑定数据(其中游标中的数据必须包含"_id"字段,所以指向查询时返回数据其中一项取别名为_id)*/
		Cursor cursor = null;
		/*2_2.1).创建SimpleCursorAdapter对象绑定游标数据(上下文,指定item所使用的界面,游标数据,指定数据绑定源,数据绑定目标)*/
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.a8_4_listview_item, cursor, new String[]{"_id","amount"}, new int[]{R.id._8_4_id,R.id._8_4_amount});
		/*2_2.2).通过适配器设置数据绑定*/
		listView.setAdapter(cursorAdapter);
	}
}
