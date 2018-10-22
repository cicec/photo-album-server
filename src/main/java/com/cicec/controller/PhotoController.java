package com.cicec.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cicec.pojo.Photo;
import com.cicec.pojo.PhotoResponseMessage;
import com.cicec.pojo.ResponseMessage;
import com.cicec.pojo.User;
import com.cicec.service.PhotoService;
import com.cicec.tool.PathConfig;
import com.cicec.tool.Tools;

@Controller
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/getphotolist/{albumId}", method = RequestMethod.GET)
    public @ResponseBody
    PhotoResponseMessage getAlbumList(@PathVariable("albumId") int albumId, HttpSession session) {
        PhotoResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            List<Photo> photoList = photoService.getPhotoList(user.getId(), albumId);
            for (Photo photo : photoList) {
                photo.setPhoto(PathConfig.onlineImagePath + "/" + photo.getPhoto());
            }
            rm = new PhotoResponseMessage(1, "图片列表获取成功", photoList);
        } else {
            rm = new PhotoResponseMessage(0, "用户未登录", null);
        }
        return rm;
    }

    @RequestMapping(value = "/getphoto/{photoId}", method = RequestMethod.GET)
    public @ResponseBody
    PhotoResponseMessage getPhoto(@PathVariable("photoId") int phototId, HttpSession session) {
        PhotoResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            List<Photo> photoList = photoService.getPhotoForId(phototId);
            Photo photo = photoList.get(0);
            photo.setPhoto(PathConfig.onlineImagePath + "/" + photo.getPhoto());
            rm = new PhotoResponseMessage(1, "图片获取成功", photoList);
        } else {
            rm = new PhotoResponseMessage(0, "用户未登录", null);
        }
        return rm;
    }

    @RequestMapping(value = "/addphoto", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage addPhotos(@RequestBody Photo photo, HttpSession session) {
        ResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            String imageName = Tools.saveBase64ToImage(photo.getPhoto());
            photo.setPhoto(imageName);
            photo.setUserId(user.getId());
            photoService.addPhoto(photo);
            rm = new ResponseMessage(1, "图片上传成功");
        } else {
            rm = new ResponseMessage(0, "用户未登录");
        }
        return rm;
    }

    @RequestMapping(value = "/removephoto", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage removePhoto(@RequestBody Photo photo, HttpSession session) {
        ResponseMessage rm;
        User user = (User) session.getAttribute("currentUser");
        if (user != null) {
            Photo rPhoto = photoService.getPhotoForId(photo.getId()).get(0);
            Tools.deleteLocalImage(rPhoto.getPhoto());
            photoService.removePhotoForId(photo.getId(), user.getId());
            rm = new ResponseMessage(1, "图片删除成功");
        } else {
            rm = new ResponseMessage(0, "用户未登录");
        }
        return rm;
    }
}