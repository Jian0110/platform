package cn.com.ssj.utils;/*
 * Created with IntelliJ IDEA.
 * User: ycc
 * Date: 2018/5/16
 * Time: 13:17
 * To change this template use File | Settings | File and Code Templates.
 * Description:
 */

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class DownloadFileUtils {
    public static void downloadFile(File file, HttpServletResponse response, boolean isDelete) {
        try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"), "ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            if (isDelete) {
                file.delete();        //是否将生成的服务器端文件删除
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
