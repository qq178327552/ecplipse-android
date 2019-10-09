package _3_短信发送功能;

import java.util.ArrayList;

import android._1_base.R;
import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Toast通知发送短信功能
 * 		1.调用Toast通知的静态方法发起通知(上下文,通知的内容,Toast的显示时长)
 * 
 * @author zheng
 */
public class _2_Toast通知发送短信功能 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a3_1_1_sms_mobile);
		Button btn = (Button)this.findViewById(R.id._2_button);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText mobileText = (EditText)findViewById(R.id._2_moblie);
				EditText contentText = (EditText)_2_Toast通知发送短信功能.this.findViewById(R.id._2_content);
				
				String mobile = mobileText.getText().toString();
				String content = contentText.getText().toString();
				SmsManager smsManager = SmsManager.getDefault();
				ArrayList<String> texts = smsManager.divideMessage(content);
				for(String text:texts){
					smsManager.sendTextMessage(mobile, null, text, null, null);
				}
				/*1.调用Toast通知的静态方法发起通知(上下文,通知的内容,Toast的显示时长)*/
				Toast.makeText(_2_Toast通知发送短信功能.this, R.string._2_success, Toast.LENGTH_LONG).show();//传递外部类对象的上下文共享数据,显示发送成功,通知持续1秒
			}
		});
	}
}
