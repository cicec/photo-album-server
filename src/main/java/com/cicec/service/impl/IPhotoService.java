package com.cicec.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cicec.dao.PhotoMapper;
import com.cicec.pojo.Photo;
import com.cicec.service.PhotoService;

@Service
public class IPhotoService implements PhotoService {
	
	@Autowired
	private PhotoMapper photoMapper;

	@Override

	public List<Photo> getPhotoList(@Param("userId") int userId, @Param("albumId") int albumId) {
		// TODO Auto-generated method stub
		return photoMapper.getPhotoList(userId, albumId);
	}

	@Override
	public List<Photo> getPhotoForId(int id) {
		return photoMapper.getPhotoForId(id);
	}

	@Override
	public int addPhoto(Photo photo) {
		// TODO Auto-generated method stub
		return photoMapper.addPhoto(photo);
	}

	@Override
	public int removePhotosForAlbum(int userId, int albumId) {
		// TODO Auto-generated method stub
		return photoMapper.removePhotosForAlbum(userId, albumId);
	}
	
	@Override
	public int removePhotoForId(int photoId, int userId) {
		// TODO Auto-generated method stub
		return photoMapper.removePhotoForId(photoId, userId);
	}
}
