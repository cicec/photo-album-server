package com.cicec.pojo;

import java.util.List;

public class AlbumResponseMessage extends ResponseMessage {
	private List<Album> albumList;

	public AlbumResponseMessage(int status, String message, List<Album> albumList) {
		super(status, message);
		this.setAlbumList(albumList);
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}

}
