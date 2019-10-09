package _7_SQLite数据库;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite数据库
 * 		有五种数据类型:null,integer,real(浮点),text(字符串),blob(二进制),也支持varchar,char,decimal等但最终转换为五种存储
 * 		可以把任何类型数据保存到任何字段中,不需关心字段声明类型(除非字段为整型主键)
 * 		查看数据库:先把数据库文件导出->用SQLite Developer查看数据库文件->注册数据库->选择数据库路径并取别名
 * 
 * 	1.创建继承SQLiteOpenHelper的抽象类,并实例化抽象方法和构造器(父类没有默认构造器)
 * 	2.创建数据库,调用父类的构造器,所以完成构造器创建数据库
 * 	3.初始化数据库,,数据库第一次调用getWritableDatabase()方法时执行初始化onCreate()方法
 * 		3.1).生成数据库表
 * 	4.修改表结构,只有改变数据库版本时调用onUpgrade()方法
 * 
 * @author zheng
 *
 */
/*1.创建继承SQLiteOpenHelper的抽象类,并实例化抽象方法和构造器(父类没有默认构造器)*/
public class _1_创建数据库 extends SQLiteOpenHelper {
	private static final String databasename = "数据库.db";
	private static final int dataversion = 1;
	/*2.创建数据库,调用父类的构造器,所以完成构造器创建数据库,(上下文对象,数据库名称,游标工厂(通过游标访问结果集中的数据),数据库版本)*/
	public _1_创建数据库(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	public _1_创建数据库(Context context) {
		/*多态创建默认数据库,在包目录下databases文件夹下*/
		this(context, databasename, null, dataversion);
	}
	
	/*3.初始化数据库,数据库第一次调用getWritableDatabase()方法时执行初始化方法*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		/*生成数据库表*/
		db.execSQL("create table person (personid integer primary key autoincrement,name varchar(20))");//创建有更改的SQL语句
	}
	/*4.修改表结构,只有改变数据库版本时调用该方法*/
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
