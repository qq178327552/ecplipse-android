package _7_SQLite数据库;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * SQLite数据库的增删改查
 * 		SQLite创建出来默认是私有只能被当前应用访问,所以没必要关闭数据库
 * 		创建获取操作数据库实例时,不管创建几次都只有一个实例(单例)
 * 		只有一种情况多例:第一次getWritableDatabase()创建(读取并写入实例),第二次用getReadableDatabase()创建(先读写创建,异常时只读创建实例)时写入数据库发生错误,程序默认创建只读取数据库的实例
 * 
 * 	1.获取操作数据库实例
 * 		1.1).getWritableDatabase()方法获取操作数据库实例,为对数据库读写操作
 * 		1.2).getReadableDatabase()方法获取操作数据库实例,先进行创建对数据库读写操作实例,出现异常时再创建对数据库只读操作
 * 	2.对数据库操作
 * 		2.1).用传Object数组填充占位符方法,对数据库增删改操作
 * 		2.2).对数据库查询操作,查询结果返回游标值(操作游标获取数据,初始游标位于第一条数据的前面位置)
 * @author zheng
 *
 */
public class _2_增删改查service {
	private _1_创建数据库 dbOpenHelper;
	public _2_增删改查service(Context context){
		this.dbOpenHelper = new _1_创建数据库(context);
	}
	/**数据添加方法*/
	public void save(_1_bean数据 person) {
		/*1.获取操作数据库实例,对数据更改调用getWritableDatabase()方法得到操作数据库实例,打开数据库方式为读写*/
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		/*2.对数据库保存操作*/
		writableDatabase.execSQL("insert into person(name) values(?)",new Object[]{person.getName()});
	}
	/**数据修改方法*/
	public void update(_1_bean数据 person) {
		/*多次调用惭怍数据库实例对象也不会创建,内部代码中有缓存该对象,调用读取时当写入对象出错则读取操作数据库对象会被创建*/
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		writableDatabase.execSQL("update person set name=? where personid=?",new Object[]{person.getName(),person.getId()});
	}
	/**数据删除方法*/
	public void delete(Intent id) {
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		writableDatabase.execSQL("delete from person where personid=?",new Object[]{id.toString()});
	}
	/**单条数据查询方法*/
	public _1_bean数据 find(Intent id) {
		/*获取操作数据库实例,只对数据读取调用getReadableDatabase()方法,该方法并非以只读方式打开数据库*/
		SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
		/*查询语句返回游标所指向的第一条数据前面位置*/
		Cursor cursor = readableDatabase.rawQuery("select * from person where personid=?", new String[]{id.toString()});
		if(cursor.moveToFirst()){
			/*游标只能根据索引获取值,所以先获取索引*/
			int index = cursor.getColumnIndex("personid");
			int personid = cursor.getInt(index);
			index = cursor.getColumnIndex("name");
			String name = cursor.getString(index);
			_1_bean数据 person = new _1_bean数据(personid,name);
			return person;
		}
		return null;
	}
	/**多条数据查询方法*/
	public List<_1_bean数据> getScrollData(Intent offset,Intent maxResult) {
		SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
		Cursor cursor = readableDatabase.rawQuery("select * from person limit ?,?", new String[]{offset.toString(),maxResult.toString()});
		/*迭代获取数据*/
		while(cursor.moveToNext()){
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			_1_bean数据 person = new _1_bean数据(personid,name);
		}
		return null;
	}
	/**单个数据返回方法*/
	public long getCount(){
		SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
		Cursor cursor = readableDatabase.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		return cursor.getLong(0);
	}
}
