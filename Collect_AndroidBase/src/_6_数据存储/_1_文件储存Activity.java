package _6_数据存储;

import android._1_base.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/**
 * 文件存储
 * 		遵循MVC框架,该类是模型层,控制前台与后台服务交互
 * 		后台需要Activity类中其父类上下文对象的实例,所以实例化服务层时传入this上下文对象
 * @author zheng
 *
 */
public class _1_文件储存Activity extends Activity {
	private static final String TAG = "_1_文件储存Activity";
	private _1_文件存储service fileService;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a6_1_savefile);
		/*初始化业务层,存储文件需要Activity父类Context中的子方法,所以初始化时需传入this上下文对象*/
		fileService = new _1_文件存储service(this);
		
		Button btn = (Button)this.findViewById(R.id._6_button);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText filenameText = (EditText)findViewById(R.id._6_filename);
				EditText contentText = (EditText)findViewById(R.id._6_filecontent);
				String filename = filenameText.getText().toString();
				String content = contentText.getText().toString();
				try{
					fileService.save(filename, content);
					Toast.makeText(_1_文件储存Activity.this, R.string._6_success, 1).show();
				}catch(Exception e){
					Log.e(TAG, e.toString());
					Toast.makeText(_1_文件储存Activity.this, R.string._6_error, 1).show();
				}
			}
		});
		
	}
}
