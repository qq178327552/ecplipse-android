package _2_打电话功能;

import android._1_base.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * 打电话后台处理功能
 * 
 * 	1.在R文件中寻找到button按钮和文本框资源
 * 	2.为按钮添加点击事件(调用Button类中的点击监听事件,需要传递一个接口实现类)
 * 	3.添加button点击监听事件的内部类,用于传递到设置点击事件(继承实现OnClickListener接口类)
 * 	4.获取页面中文本框的值
 * 	5.拨打电话号码(向系统发送一个意图,实现电话拨打号码的Activity组件)
 * 		5.1).创建一个意图,传递意图的三要素:行为(action),类别(category),数据(data);其中数据须以URI格式,类别在调用传递方法后自动添加进意图
 * 		5.2).传递意图给操作系统,内部会自动添加意图的类别信息
 * 	6.意图传递后,系统会根据意图三要素匹配注册表中的activity,若有多个activity匹配,则会跳出选择框选择
 * 	7.打电话需要应用权限才能拨打电话,需要在AndroidManifest.xml中申请权限
 * 
 * @author zheng
 *
 */
public class _1_打电话功能 extends Activity {
	/*1.在R文件中寻找到button按钮和文本框资源*/
	private Button btn = (Button)this.findViewById(R.id._1_button);
	private EditText text = (EditText)this.findViewById(R.id._1_mobile);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a2_call_mobile);
		
		/*2.为按钮添加点击事件(调用Button类中的点击监听事件,需要传递一个接口实现类)*/
		btn.setOnClickListener(new ButtonListener());
	}
	/*3.添加button点击监听事件的内部类,用于传递到设置点击事件(继承实现OnClickListener接口类)*/
	private final class ButtonListener implements View.OnClickListener{
		/*实现OnClickListener类中的方法(点击的方法)*/
		@Override
		public void onClick(View v) {
			/*4.获取页面中文本框的值*/
			String mobile = text.getText().toString();
			/*5.拨打电话号码(向系统发送一个意图,实现电话拨打号码的Activity组件)*/
			/*5.1).创建一个意图,传递意图的三要素:行为(action),类别(category),数据(data);其中数据须以URI格式,类别在调用传递方法后自动添加进意图*/
			Intent intent = new Intent("android.intent.action.CALL",Uri.parse("tel:"+mobile));
			/*5.2).传递意图给操作系统,内部会自动添加意图的类别信息*/
			startActivity(intent);
			/*6.意图传递后,系统会根据意图三要素匹配注册表中的activity,若有多个activity匹配,则会跳出选择框选择*/
			/*7.打电话需要应用权限才能拨打电话,需要在AndroidManifest.xml中申请权限*/
		}
	}
}
