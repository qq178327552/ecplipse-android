package _4_单元测试;

import android.test.AndroidTestCase;
import junit.framework.Assert;

/**
 * 单元测试
 * 	1.在AndroidManifest.xml添加测试代码,将测试库引入应用程序并配置测试库入口类
 * 	2.写单元测试代码,继承AndroidTestCase类
 * 	3.右击该类中的测试方法->RunAs->AndroidJUnitTest
 * 
 * @author zheng
 *
 */
public class _1_单元测试  extends AndroidTestCase{
	/*1.在AndroidManifest.xml添加测试代码,将测试库引入应用程序并配置测试库入口类*/
	/*2.写单元测试代码,继承AndroidTestCase类测试方法运行是否正常*/
	public void testTest() throws Throwable{
		_1_待测试的类 test = new _1_待测试的类();
		int b = test.test();
		/*比较返回结果与期望值*/
		Assert.assertEquals(78, b);
	}
	/*3.右击该类中的测试方法->RunAs->AndroidJUnitTest*/
}
