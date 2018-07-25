package com.cicec.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cicec.tool.PathConfig;

@Controller
public class ImageController {
	@RequestMapping("/image/{imageName}")
	public @ResponseBody void getImage(@PathVariable("imageName") String imageName, HttpServletResponse response) {
	     String imagePath = PathConfig.localImagePath + "/" + imageName + ".jpg";
	     try (FileInputStream fileOutImage = new FileInputStream(imagePath)) {
				int i = fileOutImage.available();
				byte data[] = new byte[i];
				fileOutImage.read(data);
				fileOutImage.close();
				response.setContentType("image/*");
			     OutputStream toClient = response.getOutputStream();
			     toClient.write(data);
			     toClient.close();
			} catch (FileNotFoundException e) {
				System.out.println("Image not found" + e);
			} catch (IOException e) {
				System.out.println("Exception while reading the Image " + e);
			}
	}
}
