package _6_数据存储;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.content.Context;
/**
 * 文件存储和读取
 * 		主要通过调用Activity父类上下文对象中的openFileOutput()方法
 * 		 默认存储在/data/data/[包名]/[文件名].txt, this.getFileDir()可以快速定位/data/data/[包名]/路径下,方便读取
 * 		查看存储的文件:showView->Other->FileExplorer
 * 		导出文件:FileExplorer->Pull a file the device
 * 		文件写入模式(并用时允许+符号):MODE_PRIVATE:文件覆盖写入,,文件不存在则创建文件,并且仅自己应用可以读取
 * 				MODE_APPEND:写入内容追加写入,文件不存在则创建文件,并且仅自己应用可以读取
 * 				MODE_WORLD_READABLE:创建的文件可以被其他应用读取
 * 				MODE_WORLD_WRITEABLE:创建的文件可以被其他应用写入
 * 	1.存储文件时需要调用Activity父类的openFileOutput()方法,业务层对象在创建时传入context对象
 * 	2.保存文件
 * 		2.1).调用context对象中的openFileOutput()方法,手机里存储文件
 * 		2.2).根据文件输出流(相对于应用来说数据输出),写入文件中 
 * 	3，读取文件
 * 		3.1).调用context对象中的openFileInput()方法,读取手机文件
 * 		3.2).根据文件输入流(相对于应用来说数据输出),读取文件
 * 
 * @author zheng
 *
 */
public class _1_文件存储service {
	/* 1.存储文件时需要调用Activity父类(Context对象中)中的方法,业务层对象在创建时传入context对象 */
	private Context context;

	public _1_文件存储service(Context context) {
		super();
		this.context = context;
	}
	/*2.保存文件*/
	public void save(String filename, String content) throws Exception {
		/* 2.1).调用context对象中的openFileOutput()方法,手机里存储文件 */
		FileOutputStream openFileOutput = context.openFileOutput(filename, Context.MODE_PRIVATE);
		/* 2.2).根据文件输出流(相对于应用来说数据输出),写入文件中 */
		openFileOutput.write(content.getBytes());
		openFileOutput.close();
	}

	/* 3.读取文件 */
	public String readFile(String filename) throws Exception {
		/* 3.1).调用context对象中的openFileInput()方法,读取手机文件 */
		//this.getFileDir()可以快速获取
		FileInputStream openFileInput = context.openFileInput(filename);
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
}
















