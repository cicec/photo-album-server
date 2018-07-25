package com.cicec.tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class Tools {
    public static String saveBase64ToImage(String base64Str) {
        String base64Image = base64Str.replaceAll("data:image/\\w+;base64,", "");
        String imageName = new Date().getTime() + ".jpg";
        String imagePath = PathConfig.localImagePath + "/" + imageName;
        try (FileOutputStream imageOutFile = new FileOutputStream(imagePath)) {
            byte[] imageByteArray = Base64.getDecoder().decode(base64Image);
            imageOutFile.write(imageByteArray);
            imageOutFile.flush();
            imageOutFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException e) {
            System.out.println("Exception while reading the Image " + e);
        }
        return imageName;
    }

    public static void deleteLocalImage(String imageName) {
        try {
            File file = new File(PathConfig.localImagePath + "/" + imageName);
            if (file.delete()) {
                System.out.println(file.getName() + " 文件已被删除！");
            } else {
                System.out.println("文件删除失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
