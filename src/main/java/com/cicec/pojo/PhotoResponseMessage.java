package com.cicec.pojo;

import java.util.List;

public class PhotoResponseMessage extends ResponseMessage {
	private List<Photo> photoList;

	public PhotoResponseMessage(int status, String message, List<Photo> photoList) {
		super(status, message);
		this.setPhotoList(photoList);
	}

	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}

}

