package _6_数据存储;

import android._1_base.R;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
/**
 * 参数保存
 * 		保存路径:/data/data/[包名]/Shared_prefs目录下,以xml格式存储参数
 * 		操作模式:
 * 
 * 	1.保存参数
 * 		1.1).调用Activity自身类中提供的getSharedPreferences()方法创建保存参数的对象
 * 		1.2).获得编辑器保存防暑
 * 		1.3).存储数据(xml中的名称,xml中的值)
 * 		1.4).提交保存的数据
 * 	2.读取参数
 * 		2.1).调用Activity自身类中提供的getSharedPreferences()方法创建读取参数的对象,操作模式不起作用
 * 		2.2).获取参数(取到的参数,若不存在返回的值)
 * 
 * @author zheng
 *
 */
public class _3_参数存储  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Button btn = (Button)this.findViewById(R.id._6_3_button);
		btn.setOnClickListener(new OnClickListener() {
			/*1.保存参数*/
			@Override
			public void onClick(View v) {
				EditText nameText = (EditText)findViewById(R.id._6_3_name);
				EditText ageText = (EditText)findViewById(R.id._6_3_age);
				String name = nameText.toString();
				String age = ageText.toString();
				/*1.1).调用Activity自身类中提供的getSharedPreferences()方法创建保存参数的对象*/
				SharedPreferences sharedPreferences = getSharedPreferences("参数文件名称", Context.MODE_PRIVATE);//(存储的文件名称,存储操作模式)
				/*1.2).获得编辑器保存防暑*/
				Editor edit = sharedPreferences.edit();
				/*1.3).存储数据(xml中的名称,xml中的值)*/
				edit.putString("name", name);
				edit.putInt("age", new Integer(age));
				/*1.4).提交保存的数据*/
				edit.commit();
			}
		});
		
		Button resumebtn = (Button)this.findViewById(R.id._6_3_resumebutton);
		resumebtn.setOnClickListener(new OnClickListener() {
			/*2.读取参数*/
			@Override
			public void onClick(View v) {
				EditText nameText = (EditText)findViewById(R.id._6_3_name);
				EditText ageText = (EditText)findViewById(R.id._6_3_age);
				/*2.1).调用Activity自身类中提供的getSharedPreferences()方法创建读取参数的对象,操作模式不起作用*/
				SharedPreferences sharedPreferences = getSharedPreferences("参数文件名称",Context.MODE_PRIVATE);
				/*2.2).获取参数(取到的参数,若不存在返回的值)*/
				String name = sharedPreferences.getString("name", "");
				int age = sharedPreferences.getInt("age", 0);
				
				nameText.setText(name);
				ageText.setText(age);
			}
		});
	}
}
