package _6_数据存储;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
import android.os.Environment;

/**
 * SD卡存储
 * 		android手机SD卡位置:
 * 			安卓2.2版本以下:/sdcard
 * 			安卓2.2版本以上:/mnt/sdcard,但在.sdcard也有镜像路径
 * 
 * 	1.存储文件时需要调用Activity父类(Context对象中)中的方法,业务层对象在创建时传入context对象
 * 	2.保存文件
 * 		2.1).判断是否有SD卡,并且打开写保护开关
 * 		2.2).存储文件到手机SD卡中
 * 		2.3).根据文件输出流(相对于应用来说数据输出),写入文件中
 * 	3.读取文件
 * 		3.1).读取SD卡中对应的文件
 * 		3.2).根据文件输入流(相对于应用来说数据输出),读取文件
 * 	4.添加SD卡创建与删除权限、往SD卡写数据权限
 * 
 * @author zheng
 *
 */
public class _2_SD卡读写service {
	/* 1.存储文件时需要调用Activity父类(Context对象中)中的方法,业务层对象在创建时传入context对象 */
	private Context context;

	public _2_SD卡读写service(Context context) {
		super();
		this.context = context;
	}
	/*2.保存文件*/
	public void save(String filename, String content) throws Exception {
		/*2.1).判断是否有SD卡,并且打开写保护开关*/
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) return;
		/* 2.2).存储文件到手机SD卡中 */
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		FileOutputStream openFileOutput = new FileOutputStream(file);
		/* 2.3).根据文件输出流(相对于应用来说数据输出),写入文件中 */
		openFileOutput.write(content.getBytes());
		openFileOutput.close();
	}

	/* 3.读取文件 */
	public String readFile(String filename) throws Exception {
		/* 3.1).读取SD卡中对应的文件 */
		File file = new File(Environment.getExternalStorageDirectory(),filename);
		FileInputStream openFileInput = new FileInputStream(file);
		/* 3.2).根据文件输入流(相对于应用来说数据输出),读取文件 */
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		while((len = openFileInput.read(buffer)) != -1){
			outStream.write(buffer,0,len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		openFileInput.close();
		return new String(data);
	}
	/*4.添加SD卡创建与删除权限、往SD卡写数据权限*/
}
