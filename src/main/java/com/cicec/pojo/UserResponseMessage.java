package com.cicec.pojo;

public class UserResponseMessage extends ResponseMessage {
	private User user;

	public UserResponseMessage(int status, String message, User user) {
		super(status, message);
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
