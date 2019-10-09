package _1_base;

import android._1_base.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
/**
 * 安卓基础
 * 		1.AndroidManifest.xml:用于写前端界面注册信息,添加activity和其意图过滤器
 * 		2.R文件(自动生成):用于存储所有资源的总集合
 * 		3.界面(res->layout):用于写安卓显示界面的xml文件
 * 		4.后台代码(src),继承Activity类,用于处理安卓程序,对于前端调用都是通过访问R文件调用
 * 		5.文字常量(res->values):用于存放文字常量的包,设置完后在R文件调用
 * 		
 * 		6.更改安卓版本:项目Properties->Android->选择对应的版本
 * 					打开AndroidManifest.xml,修改<<uses-sdk>标签中最低版本
 * 		6.部署安卓到虚拟机:打开虚拟机(AndroidVirtualDeviceManager)->右击项目->RunAs->AndroidApplication->选择模拟器
 * 		7.真机测试:安装真实手机对应的驱动->USB线连接手机->打开Devices视图窗口,插入后该视图显示手机信息->同部署安装到虚拟机操作一样,最后选择真机信息
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*1.显示在R文件资源下的界面(在界面的layout下写的界面,会自动添加到资源R文件下的一个引用)*/
		setContentView(R.layout.activity_main);
	}
}
