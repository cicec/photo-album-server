package com.cicec.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.tools.Tool;

import com.cicec.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cicec.service.AlbumService;
import com.cicec.service.PhotoService;
import com.cicec.tool.PathConfig;
import com.cicec.tool.Tools;

@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/getalbumlist", method = RequestMethod.GET)
    public @ResponseBody
    AlbumResponseMessage getAlbumList(HttpSession session) {
        AlbumResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            List<Album> albumList = albumService.getAlbumList(user);
            for (Album album : albumList) {
                album.setCover(PathConfig.onlineImagePath + "/" + album.getCover());
            }
            rm = new AlbumResponseMessage(1, "相册列表获取成功", albumList);
        } else {
            rm = new AlbumResponseMessage(0, "用户未登录", null);
        }
        return rm;
    }

    @RequestMapping(value = "/addalbum", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage addAlbum(@RequestBody Album album, HttpSession session) {
        ResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            if (album.getTitle() == null || album.getDescription() == null) {
                rm = new ResponseMessage(0, "填写信息不完整");
            } else {
                album.setUserId(user.getId());
                if (album.getCover() == null) {
                    album.setCover("album-cover-default.jpg");
                } else {
                    String imageName = Tools.saveBase64ToImage(album.getCover());
                    album.setCover(imageName);
                }
                albumService.addAlbum(album);
                rm = new ResponseMessage(1, "相册添加成功");
            }
        } else {
            rm = new ResponseMessage(0, "用户未登录");
        }
        return rm;
    }

    @RequestMapping(value = "/removealbum", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage removeAlbum(@RequestBody Album album, HttpSession session) {
        ResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            List<Photo> photoList = photoService.getPhotoList(user.getId(), album.getId());
            for (Photo photo : photoList) {
                Tools.deleteLocalImage(photo.getPhoto());
            }
            photoService.removePhotosForAlbum(user.getId(), album.getId());
            albumService.removeAlbum(user.getId(), album.getId());
            rm = new ResponseMessage(1, "相册删除成功");
        } else {
            rm = new ResponseMessage(0, "用户未登录");
        }
        return rm;
    }
}