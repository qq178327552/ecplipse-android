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
 * 打电话功能改进
 * 		1.将值放入方法内
 * 		2.使用匿名内部类实现点击事件监听方法
 * 		3.使用系统自定义常量传递意图的action
 * @author zheng
 *
 */
public class _2_打电话功能_匿名内部类和常量改进 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a2_call_mobile);
		
		/*1.将值放入方法内*/
		Button btn = (Button)this.findViewById(R.id._1_button);
		final EditText text = (EditText)this.findViewById(R.id._1_mobile);
		/*2.使用匿名内部类实现点击事件监听方法*/
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String mobile = text.getText().toString();
				/*3.使用系统自定义常量传递意图的action */
				Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + mobile));
				startActivity(intent);
			}
		});
	}
}
