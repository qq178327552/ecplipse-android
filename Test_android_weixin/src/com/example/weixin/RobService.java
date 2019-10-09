package com.example.weixin;

import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

public class RobService extends AccessibilityService {
	/** 微信几个页面的包名+地址。用于判断在哪个页面 LAUCHER-微信聊天界面，LUCKEY_MONEY_RECEIVER-点击红包弹出的界面 */
	private String LAUCHER = "com.tencent.mm.ui.LauncherUI";
	private String LUCKEY_MONEY_DETAIL = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI";
	private String LUCKEY_MONEY_RECEIVER = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyReceiveUI";
	/** 用于判断是否点击过红包了 */
	private boolean isOpenRP;

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {
		int eventType = event.getEventType();
		switch (eventType) {
		/* 通知栏来信息，判断是否含有微信红包字样，是的话跳转 */
		case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
			List<CharSequence> texts = event.getText();
			for (CharSequence text : texts) {
				String content = text.toString();
				if (!TextUtils.isEmpty(content)) {
					if (content.contains("[微信红包]")) {// 判断是否含有[微信红包]字样
						openWeChatPage(event);// 如果有则打开微信红包页面
						isOpenRP = false;
					}
				}
			}
			break;
		/* 界面跳转的监听 */
		case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
			String className = event.getClassName().toString();
			if (LAUCHER.equals(className)) {// 判断是否是微信聊天界面
				AccessibilityNodeInfo rootNode = getRootInActiveWindow();// 获取当前聊天页面的根布局
				try   
				{   
					int i = (int)(1+Math.random()*(10-1+1));
					Thread.currentThread();
					Thread.sleep(i*547);//毫秒   
				}   
				catch(Exception e){} 
				findRedPacket(rootNode);// 开始找红包
			}
			if (LUCKEY_MONEY_RECEIVER.equals(className)) { // 判断是否是显示‘开’的那个红包界面
				AccessibilityNodeInfo rootNode = getRootInActiveWindow();
				try   
				{   
					int i = (int)(1+Math.random()*(10-1+1));
					Thread.currentThread();
					Thread.sleep(i*661);//毫秒   
				}   
				catch(Exception e){} 
				findRedPacket(rootNode);// 开始找红包
				openRedPacket(rootNode);// 开始抢红包
			}

			if (LUCKEY_MONEY_DETAIL.equals(className)) {// 判断是否是红包领取后的详情界面
				try   
				{   
					Thread.currentThread();
					Thread.sleep(1000);//毫秒   
				}   
				catch(Exception e){} 
				back2Home(); // 返回桌面
			}
			break;
		}
	}

	/** 开始打开红包 */
	private void openRedPacket(AccessibilityNodeInfo rootNode) {
		for (int i = 0; i < rootNode.getChildCount(); i++) {
			AccessibilityNodeInfo node = rootNode.getChild(i);
			if ("android.widget.Button".equals(node.getClassName())) {
				node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
			}
			openRedPacket(node);
		}
	}

	/** 遍历查找红包 */
	private void findRedPacket(AccessibilityNodeInfo rootNode) {
		if (rootNode != null) {
			/* 从最后一行开始找起 */
			for (int i = rootNode.getChildCount() - 1; i >= 0; i--) {
				AccessibilityNodeInfo node = rootNode.getChild(i);
				if (node == null) {// 如果node为空则跳过该节点
					continue;
				}
				CharSequence text = node.getText();
				if (text != null && text.toString().equals("领取红包")) {
					AccessibilityNodeInfo parent = node.getParent();
					while (parent != null) {// while循环,遍历"领取红包"的各个父布局，直至找到可点击的为止
						if (parent.isClickable()) {
							parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);// 模拟点击
							isOpenRP = true;// isOpenRP用于判断该红包是否点击过
							break;
						}
						parent = parent.getParent();
					}
				}
				if (isOpenRP) {// 判断是否已经打开过那个最新的红包了，是的话就跳出for循环，不是的话继续遍历
					break;
				} else {
					findRedPacket(node);
				}

			}
		}
	}

	/** 开启红包所在的聊天页面 */
	private void openWeChatPage(AccessibilityEvent event) {
		// A instanceof B 用来判断内存中实际对象A是不是B类型，常用于强制转换前的判断
		if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
			Notification notification = (Notification) event.getParcelableData();
			// 打开对应的聊天界面
			PendingIntent pendingIntent = notification.contentIntent;
			try {
				pendingIntent.send();
			} catch (PendingIntent.CanceledException e) {
				e.printStackTrace();
			}
		}
	}

	/** 返回桌面 */
	private void back2Home() {
		Intent home = new Intent(Intent.ACTION_MAIN);
		home.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		home.addCategory(Intent.CATEGORY_HOME);
		startActivity(home);
	}

	@Override
	public void onInterrupt() {
		Toast.makeText(this, "我快被终结了啊-----", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onServiceConnected() {
		/*
		 * AccessibilityServiceInfo serviceInfo = new
		 * AccessibilityServiceInfo(); serviceInfo.eventTypes =
		 * AccessibilityEvent.TYPES_ALL_MASK; serviceInfo.feedbackType =
		 * AccessibilityServiceInfo.FEEDBACK_GENERIC; serviceInfo.packageNames =
		 * new String[] { "com.tencent.mm" }; serviceInfo.notificationTimeout =
		 * 100; setServiceInfo(serviceInfo);
		 */
		Toast.makeText(this, "抢红包服务开启", Toast.LENGTH_SHORT).show();
		super.onServiceConnected();
	}

	/**
	 * 服务已断开
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "抢红包服务已被关闭", Toast.LENGTH_SHORT).show();
		return super.onUnbind(intent);
	}
}
