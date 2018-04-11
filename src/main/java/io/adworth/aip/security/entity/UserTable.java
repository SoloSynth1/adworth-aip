package io.adworth.aip.security.entity;

import java.util.ArrayList;

// An in-memory ArrayList to store/access individual User.
public class UserTable {
	private static ArrayList<User> UserTable = new ArrayList<User>();

	public static User getUser(int index) {
		return UserTable.get(index);
	}
	
	public static void addUser(String username, String password, String role) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(role);
		UserTable.add(user);
	}
	
	public static void delUser(int index) {
		UserTable.remove(index);
	}
	
	public static int getSize() {
		return UserTable.size();
	}
}
