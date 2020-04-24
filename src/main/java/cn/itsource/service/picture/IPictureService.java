package cn.itsource.service.picture;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface IPictureService {

    String saveImage(MultipartFile picture);

    String saveImage(MultipartFile picture,String headImage, HttpServletRequest req);

    String saveImage(MultipartFile picture, String path);

    String saveImage(String url, String pictureFormat);

    String saveImage(String url, String path, String pictureFormat);

    boolean deleteImage(String pictureUrl);

    boolean deleteImage(String pictureUrl, HttpServletRequest req);
}
