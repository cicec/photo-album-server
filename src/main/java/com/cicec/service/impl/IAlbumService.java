package com.cicec.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cicec.dao.AlbumMapper;
import com.cicec.pojo.Album;
import com.cicec.pojo.User;
import com.cicec.service.AlbumService;

@Service
public class IAlbumService implements AlbumService {
	
	@Autowired
	private AlbumMapper albumMapper;
	
	@Override
	public List<Album> getAlbumList(User user) {
		// TODO Auto-generated method stub
		return albumMapper.getAlbumList(user);
	}
	
	@Override
	public int addAlbum(Album album) {
		// TODO Auto-generated method stub
		return albumMapper.addAlbum(album);
	}

	@Override
	public int removeAlbum(int userId, int albumId) {
		// TODO Auto-generated method stub
		return albumMapper.removeAlbum(userId, albumId);
	}
}
