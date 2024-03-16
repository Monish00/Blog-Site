package com.helper;

import com.models.User;

public class CurrentUser {
	private static User user;



	public static void setUser(User user) {
		System.out.println("User set");
		CurrentUser.user = user;
	}



	public static User getUser() {
		return user;
	}

}
