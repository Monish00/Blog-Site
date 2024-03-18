package com.helper;

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

public class OldTagManager extends TimerTask {
	public static AtomicBoolean isStarted = new AtomicBoolean(false);
	
	public void run() {
		com.JDBC.TagManager tagdb = new com.JDBC.TagManager();
		tagdb.deleteOldTag();
		System.out.println("Old tags deleted");
	}
}
