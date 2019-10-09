package _7_SQLite数据库;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
/**
 * 数据库版本升级更新表结构
 * 			对于名称相同的数据库，当初始化版本发生变动时，会自动调用onUpgrade()方法
 * 			会先判断数据库是否存在，然后判断手机中数据库版本和代码中版本比较，版本不一致情况下调用onUpgrade()方法
 * 
 * @author zheng
 *
 */
public class _4_升级改表结构 extends SQLiteOpenHelper {
	private static final String databasename = "数据库.db";
	private static final int dataversion = 2;

	public _4_升级改表结构(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists person");
		/*版本更新时调用onUpgrade()方法,然后更新的代码从创建表修改*/
		onCreate(db);
	}
	
}
