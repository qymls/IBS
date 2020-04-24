package cn.itsource.service.picture.Impl;

import cn.itsource.service.picture.IPictureService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class PictureServiceImpl implements IPictureService {
    @Override
    public String saveImage(MultipartFile multipartFile) {
        return null;
    }

    @Override
    public String saveImage(MultipartFile multipartFile, String headImage, HttpServletRequest req) {
        String originalFilename = multipartFile.getOriginalFilename();/*文件名*/
        FileOutputStream outputStream = null;
        String realPath = req.getServletContext().getRealPath("/");
        if (StringUtils.isNoneBlank(headImage)) {/*上传之前判断是否已经上传过了，如果上传了就删除*/
            File file = new File(realPath + headImage);
            if (file.exists()) {
                file.delete();
            }
        }
        String extension = FilenameUtils.getExtension(originalFilename);/*获取文件后缀名称*/
        String path = "upload/" + UUID.randomUUID() + "." + extension;/*用于放在数据库*/
        File file = new File(realPath + path);
        if (!file.getParentFile().exists()) {/*父文件夹不存在就创建*/
            file.getParentFile().mkdirs();
        }
        try {
            outputStream = new FileOutputStream(file);
            IOUtils.copy(multipartFile.getInputStream(), outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return path;
    }

    @Override
    public String saveImage(MultipartFile picture, String path) {
        return null;
    }

    @Override
    public String saveImage(String url, String pictureFormat) {
        return null;
    }

    @Override
    public String saveImage(String url, String path, String pictureFormat) {
        return null;
    }

    @Override
    public boolean deleteImage(String pictureUrl) {
        return false;
    }

    @Override
    public boolean deleteImage(String pictureUrl, HttpServletRequest req) {
        String realPath = req.getServletContext().getRealPath("/");
        File file = new File(realPath + pictureUrl);
        if (file.exists()) {
            file.delete();
        }
        return true;
    }
}
