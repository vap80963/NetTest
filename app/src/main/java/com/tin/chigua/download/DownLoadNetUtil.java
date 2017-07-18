package com.tin.chigua.download;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hasee on 7/12/2017.
 */

public class DownLoadNetUtil {

    private static final String REQUEST_POST = "POST";
    private static final int CONNECT_TIME_OUT = 15000;
    private static final int READ_TIME_OUT = 3000;
    private static final String PROPERTY_KEY = "Connection";
    //保持连接
    private static final String PROPERTY_VALUE = "Keep-Alive";
    private static final String ENCODE_METHOD = "UTF-8";

    private volatile int start = 0;
    private volatile int end = 0;


    public void getHttpConnection(String url){

        HttpURLConnection conn = null;
        try {
            /**
             * 以下完成链接、请求和获取输入流
             * 关键操作是：conn.setRequestProperty("Range","byte=" + start + "-" + end);
             */
            URL mUrl = new URL(url);
            conn = (HttpURLConnection) mUrl.openConnection();
            //设置请求方式
            conn.setRequestMethod(REQUEST_POST);
            //设置user-agent
            conn.setRequestProperty("user-agent","netfox");
            //设置请求数据范围
            conn.setRequestProperty("Range","byte=" + start + "-" + end);
            //获得输入流
            InputStream is = conn.getInputStream();

            /**
             * 以下完成输入流的写入本地文件
             * 这里使用RandomAccessFile
             */
            RandomAccessFile accessFile = new RandomAccessFile("down.zip","rw");
            //当前请求数据的起始位置
            long nowPosition = start;
            //定位文件指针到当前请求位置
            accessFile.seek(nowPosition);
            accessFile.getFilePointer();
            byte b[] = new byte[1024];
            int nowRead;
            /**
             * 读入输入流
             * read(byte b[], int off, int len)
             * off 表示byte数组b被读取的起始位置
             * len 表示每次可读取的最大字节数，这里的byte数组指定了1024，所以传入1024作为最大读取数
             * read()方法返回本次读取的byte字节数量
             */
            while ((nowRead = accessFile.read(b,0,1024)) > 0){
                accessFile.write(b,0,nowRead);  //同read相似，此处传入的是read（）返回的字节数量
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
