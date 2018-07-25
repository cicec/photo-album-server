package com.cicec.pojo;

public class Album {
	private int id;
	private int user_id;
	private String title;
	private String description;
	private String cover;
	private int pics_number;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public int getPicsNumber() {
		return pics_number;
	}

	public void setPicsNumber(int pics_number) {
		this.pics_number = pics_number;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + ", user_id=" + user_id + ", title=" + title + ", description=" + description
				+ ", cover=" + cover + ", pics_number=" + pics_number + "]";
	}
}
