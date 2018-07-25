package com.cicec.pojo;

public class Photo {
	private int id;
	private int album_id;
	private int user_id;
	private String name;
	private String photo;
	private long modified;
	private int size;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlbumId() {
		return album_id;
	}

	public void setAlbumId(int album_id) {
		this.album_id = album_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public long getModified() {
		return modified;
	}

	public void setModified(long modified) {
		this.modified = modified;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", album_id=" + album_id + ", user_id=" + user_id + ", name=" + name + ", photo="
				+ photo + ", modified=" + modified + ", size=" + size + ", type=" + type + "]";
	}
}
