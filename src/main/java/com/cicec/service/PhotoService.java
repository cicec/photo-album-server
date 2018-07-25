package com.cicec.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cicec.pojo.Photo;

public interface PhotoService {
	public List<Photo> getPhotoList(@Param("userId") int userId, @Param("albumId") int albumId);

	public List<Photo> getPhotoForId(int id);

	public int addPhoto(Photo photo);

	public int removePhotosForAlbum(@Param("userId") int userId, @Param("albumId") int albumId);

	public int removePhotoForId(@Param("photoId") int photoId, @Param("userId") int userId);
}
