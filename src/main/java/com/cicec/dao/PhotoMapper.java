package com.cicec.dao;

import java.util.List;

import com.cicec.pojo.User;
import org.apache.ibatis.annotations.Param;

import com.cicec.pojo.Photo;

public interface PhotoMapper {
	public List<Photo> getPhotoList(@Param("userId") int userId, @Param("albumId") int albumId);

	public List<Photo> getPhotoListForUser(User user);

	public List<Photo> getPhotoForId(int id);
	
	public int addPhoto(Photo photo);
	
	public int removePhotosForAlbum(@Param("userId") int userId, @Param("albumId") int albumId);
	
	public int removePhotoForId(@Param("photoId") int photoId, @Param("userId") int userId);
}
