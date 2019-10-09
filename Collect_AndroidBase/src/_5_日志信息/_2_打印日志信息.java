package _5_日志信息;

import android.util.Log;
/**
 * 打印日志
 * 		日志共分为四个级别,分别用不同颜色表示
 * 
 * 	1.向控制台输出info级别信息(消息标签,消息内容)
 * 	2.向控制台输出debug级别信息(消息标签,消息内容)
 * 	3.向控制台输出error级别信息(消息标签,消息内容)
 * 	4.向控制台输出warn级别信息(消息标签,消息内容)
 * 
 * @author zheng
 *
 */
public class _2_打印日志信息 {
	private static final String TAG = "_2_打印日志信息";
	public void print(){
		/*1.向控制台输出info级别信息(消息标签,消息内容)*/
		Log.i(TAG, "msg");
		/*2.向控制台输出debug级别信息(消息标签,消息内容)*/
		Log.d(TAG, "msg");
		/*3.向控制台输出error级别信息(消息标签,消息内容)*/
		Log.e(TAG, "msg");
		/*4.向控制台输出warn级别信息(消息标签,消息内容)*/
		Log.w(TAG, "msg");
	}
}
