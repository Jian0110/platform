package cn.com.ssj.utils;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class UploadFileUtils {
    /**
     * 获取上传文件列表
     * @param httpServletRequest httpServletRequest
     * @param savePath 文件存储地址 基于spring MultipartFile 会将文件转存为 java.io.file
     * @return 文件列表
     * @throws IOException IOException
     */
    public static List<File> getUploadFiles(HttpServletRequest httpServletRequest, String savePath) throws IOException {
        List<File> fileList = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(httpServletRequest.getSession().getServletContext());
        if (multipartResolver.isMultipart(httpServletRequest)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) httpServletRequest;
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                MultipartFile file = multiRequest.getFile(iter.next());
                String originalFilename = file.getOriginalFilename();
                String filename = originalFilename.substring(0, originalFilename.lastIndexOf(".")) + "_" + new Date().getTime() + originalFilename.substring(originalFilename.lastIndexOf("."));
                String path = savePath + filename;
                File target = new File(path);
                file.transferTo(target);
                fileList.add(target);
            }
        }
        return fileList;
    }
}
