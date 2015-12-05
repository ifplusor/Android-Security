package com.JY.utils;

import android.content.Context;
import android.widget.Toast;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpDownloader {
    private URL url = null;

    /**
     * 根据URL下载文件，前提是这个文件当中的内容是文本，函数的返回值就是文件当中的内容
     * 1.创建一个URL对象
     * 2.通过URL对象，创建一个HttpURLConnection对象
     * 3.得到InputStram
     * 4.从InputStream当中读取数据
     *
     * @param urlStr
     * @return
     */
    public String download(String urlStr) {
        StringBuffer sb = new StringBuffer();
        String line = null;
        BufferedReader buffer = null;
        try {
            // 创建一个URL对象
            url = new URL(urlStr);
            // 创建一个Http连接
            HttpURLConnection urlConn = (HttpURLConnection) url
                    .openConnection();
            // 使用IO流读取数据
            buffer = new BufferedReader(new InputStreamReader(urlConn
                    .getInputStream()));
            while ((line = buffer.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                buffer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /*
* 该函数返回整型: -1:代表出错 , 0:代表成功 , 1:代表已经存在
*
* @description 下载的通用方法
* @param urlStr
* @param path
* @param fileName
* @return
*/

    public int downFile(Context context, String urlStr, String path,

                        String fileName) {

        InputStream inputStream = null;

        FileUtils fileUtils = new FileUtils();

        if (fileUtils.isFileExist(path + fileName)) {

            Toast.makeText(context, "文件已经存在", Toast.LENGTH_LONG).show();

            return 1;

        } else {

            try {

                inputStream = getInputSreamFromUrl(urlStr);

                File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);

                if (resultFile == null) {
                    Toast.makeText(context, "下载失败", Toast.LENGTH_LONG).show();
                    return -1;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return -1;
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return -1;
                }
            }
        }
        return 3;
    }


    /**
     * 得到输入流
     *
     * @param urlstr
     * @return
     * @throws java.io.IOException
     */

    public InputStream getInputSreamFromUrl(String urlstr) throws IOException {

        url = new URL(urlstr);

        HttpURLConnection urlconn = (HttpURLConnection) url.openConnection();

        InputStream is = urlconn.getInputStream();

        return is;

    }

    /**
     * 该函数返回整形 -1：代表下载文件出错 0：代表下载文件成功 1：代表文件已经存在
     */
    public int downFile (String urlStr, String path, String fileName){
        InputStream inputStream = null;
        try {
            FileUtils fileUtils = new FileUtils();

            if (fileUtils.isFileExist(path + fileName)) {
                return 1;
            } else {
                inputStream = getInputStreamFromUrl(urlStr);
                File resultFile = fileUtils.write2SDFromInput(path, fileName, inputStream);
                if (resultFile == null) {
                    return -1;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * 根据URL得到输入流
     *
     * @param urlStr
     * @return
     * @throws java.net.MalformedURLException
     * @throws java.io.IOException
     */
    public InputStream getInputStreamFromUrl (String urlStr)
            throws MalformedURLException, IOException {
        url = new URL(urlStr);
        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
        InputStream inputStream = urlConn.getInputStream();
        return inputStream;
    }

    /**
     * 从网上下载
     *
     * @param urlPsth        下载路径
     * @param outputFile 创建本地保存流的文件
     * @return 下载失败返回1(比如没有网络等情况)下载成功返回0
     */

    public static int downloadFile (String urlPsth, File outputFile){

        int result = 0;

        try {

            URL url = new URL(urlPsth);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoInput(true);

            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)

            {

                InputStream is = conn.getInputStream();

                FileOutputStream fos = new FileOutputStream(outputFile);

                byte[] bt = new byte[1024];

                int i = 0;

                while ((i = is.read(bt)) > 0) {

                    fos.write(bt, 0, i);

                }

                fos.flush();

                fos.close();

                is.close();

            } else {

                result = 1;

            }

        } catch (FileNotFoundException e) {

            result = 1;

        } catch (IOException e) {

            result = 1;

        }

        return result;

    }
}
