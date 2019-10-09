package _7_SQLite数据库;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
/**
 * 事物操作
 * 
 * 	1.开启事物
 * 	2.执行事务语句
 * 	3.设置事务标识为成功标识,当结束事务时才会提交
 * 	4.结束事务(当未设置事务成功则回滚,设置了事务成功则执行)
 * 
 * @author zheng
 *
 */
public class _5_事物操作 {
	private _1_创建数据库 dbOpenHelper;
	public _5_事物操作(Context context){
		this.dbOpenHelper = new _1_创建数据库(context);
	}
	
	public void payment(){
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		/*1.开启事物*/
		writableDatabase.beginTransaction();
		try {
			/* 2.执行事务语句 */
			writableDatabase.execSQL("update person set name=name where personid=?", new Object[] { 1 });
			writableDatabase.execSQL("update person set name=name where personid=?", new Object[] { 2 });
			/* 3.设置事务标识为成功标识,当结束事务时才会提交 */
			writableDatabase.setTransactionSuccessful();
		} finally {
			/* 4.结束事务(当未设置事务成功则回滚,设置了事务成功则执行) */
			writableDatabase.endTransaction();
		}
	}
}
