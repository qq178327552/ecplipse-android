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
/**
 * 短信发送功能
 * 
 * 		1.用短信管理对象发短信
 * 		2.将短信内容拆分成规定字数的短信(70个汉字)
 * 		3.调用发短信管理对象中的方法发送短信
 * 		4.在AndroidManifest.xml中申请发短信权限
 * 
 * @author zheng
 */
public class _1_短信发送功能  extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a3_1_1_sms_mobile);
		Button btn = (Button)this.findViewById(R.id._2_button);
		
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText mobileText = (EditText)findViewById(R.id._2_moblie);
				EditText contentText = (EditText)_1_短信发送功能.this.findViewById(R.id._2_content);
				
				String mobile = mobileText.getText().toString();
				String content = contentText.getText().toString();
				/*1.用短信管理对象发短信*/
				SmsManager smsManager = SmsManager.getDefault();
				/*2.将短信内容拆分成规定字数的短信(70个汉字)*/
				ArrayList<String> texts = smsManager.divideMessage(content);
				/*3.调用发短信管理对象中的方法发送短信(目标地址,短信中心号码,短信内容,是否发送成功响应意图,对方是否收到短信响应意图)*/
				for(String text:texts){
					smsManager.sendTextMessage(mobile, null, text, null, null);
				}
				/*4.在AndroidManifest.xml中申请发短信权限*/
			}
		});
	}
}
