package _7_SQLite数据库;

import java.util.List;

import android.app.DownloadManager.Query;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
/**
 * 调用类增删改查
 * 		性能比直接调用差,因为内部需要从新构造SQL语句
 * 	
 * 	1.保存操作
 * 		1.1).创建添加各个字段的值的ContentValues容器
 * 		1.2).调用insert()方法对数据库保存操作(数据的表名,空值字段(当第三个参数为空时,该参数表示插入的值为null,以保持至少插入一条数据),各个字段的值)
 * 	2.更新操作
 * 		2.1).创建添加各个字段的值的ContentValues容器
 * 		2.2).调用update()方法对数据库更新操作(表名,更新字段的名,条件,指定占位符字符串数组)
 * 	3.调用delete()方法对数据库删除(表名,条件,指定占位符字符串数组)
 * 	4.调用query()方法对数据库查询(表名,查询的字段名称(null表示*),条件,指定占位符字符串数组,是否存在分组,筛选,是否排序)
 * 	5.调用delete()方法对数据库查询(表名,查询的字段名称(null表示*),条件,指定占位符字符串数组,是否存在分组,筛选,是否排序,分页页数)
 * 	6.调用delete()方法对数据库查询(表名,查询的字段名称(null表示*),条件,指定占位符字符串数组,是否存在分组,筛选,是否排序,分页页数)
 * @author zheng
 *
 */
public class _3_调用类增删改查 {
	private _1_创建数据库 dbOpenHelper;
	public _3_调用类增删改查(Context context){
		this.dbOpenHelper = new _1_创建数据库(context);
	}
	/**数据添加方法*/
	public void save(_1_bean数据 person) {
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		/*1.1).创建添加各个字段的值的ContentValues容器*/
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", person.getName());
		/*1.2).调用insert()方法对数据库保存操作(数据的表名,空值字段(当第三个参数为空时,该参数表示插入的值为null,以保持至少插入一条数据),各个字段的值)*/
		writableDatabase.insert("person", "name", contentValues);
	}
	/**数据修改方法*/
	public void update(_1_bean数据 person) {
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		
		ContentValues contentValues = new ContentValues();
		contentValues.put("name", person.getName());
		/*2.2).调用update()方法对数据库更新操作(表名,更新字段的名,条件,指定占位符字符串数组)*/
		writableDatabase.update("person", contentValues, "person=?", new String[]{person.getId().toString()});
	}
	/**数据删除方法*/
	public void delete(Intent id) {
		SQLiteDatabase writableDatabase = dbOpenHelper.getWritableDatabase();
		/*3.调用delete()方法对数据库删除(表名,条件,指定占位符字符串数组)*/
		writableDatabase.delete("person", "personid=?", new String[]{id.toString()});
	}
	/**单条数据查询方法*/
	public _1_bean数据 find(Intent id) {
		SQLiteDatabase readableDatabase = dbOpenHelper.getReadableDatabase();
		/*4.调用query()方法对数据库查询(表名,查询的字段名称(null表示*),条件,指定占位符字符串数组,是否存在分组,筛选,是否排序)*/
		Cursor cursor = readableDatabase.query("person",new String[]{"personid","name"}, "person=?",new String[]{id.toString()},null,null,null);
		if(cursor.moveToFirst()){
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
		/*5.调用delete()方法对数据库查询(表名,查询的字段名称(null表示*),条件,指定占位符字符串数组,是否存在分组,筛选,是否排序,分页页数)*/
		Cursor cursor = readableDatabase.query("person",null, null,null,null,null,null,offset+","+maxResult);
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
		/*6.调用delete()方法对数据库查询(表名,查询的字段名称(null表示*),条件,指定占位符字符串数组,是否存在分组,筛选,是否排序,分页页数)*/
		Cursor cursor = readableDatabase.query("person",new String[]{"count(*)"}, null,null,null,null,null);
		cursor.moveToFirst();
		return cursor.getLong(0);
	}
}
