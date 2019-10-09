package _a10_内容提供者;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
/**
 * 内容提供者
 * 		程序若想把数据对外提供，需要通过内容提供者对外共享数据
 * 		使用内容提供者需要继承ContentProvider类
 * 		内容提供者提供外部访问数据，统一了数据访问方式
 * 		该类需要放在当前应用包底下(组件)
 * 		重写的方法是对提供的提供的数据操作权限
 * 		内容提供者为组件类,继承组件的类需要放在AndroidManifest.xml定义的包底下
 * 
 * 	1.在当前应用下配置内容提供者,需要在AndroidManifest.xml中申请权限
 * @author zheng
 *
 */
public class _1_内容提供者提供数据  extends ContentProvider{
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
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
	public boolean onCreate() {
		return false;
	}
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return null;
	}
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
}
