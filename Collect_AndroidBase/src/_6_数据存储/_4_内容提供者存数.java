package _6_数据存储;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * 内容提供者(Content provider)
 * 		把应用中的数据对外共享
 * @author zheng
 *
 */
/*1.继承ContentProvider类,重写提供数据和存储数据的方法*/
public class _4_内容提供者存数 extends ContentProvider {
	@Override
	public boolean onCreate() {
		return false;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return null;
	}
	@Override
	public String getType(Uri uri) {
		return null;
	}
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	/*需要在AndroidManifest.xml对内容提供者配置<provider>*/
}
