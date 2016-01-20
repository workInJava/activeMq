package com.test.mq.core;

import javax.jms.Message;
import javax.jms.MessageListener;

public abstract class AbstractReceiver implements MessageListener {

	public void onMessage(Message message) {
		messageCallBack(message);
	}
	
	public abstract void messageCallBack(Message message);
}
