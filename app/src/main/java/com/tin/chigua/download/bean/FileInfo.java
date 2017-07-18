package com.tin.chigua.download.bean;

/**
 * Created by hasee on 7/17/2017.
 */

public class FileInfo {

    private String fileName; //文件名
    private String url;  //下载地址
    private int length;  //文件大小
    private int finished; //下载已完成进度
    private boolean isStop=false; //是否暂停下载
    private boolean isDownloading=false; //是否正在下载

    public FileInfo(){
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public void setDownloading(boolean downloading) {
        isDownloading = downloading;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                ", length=" + length +
                ", finished=" + finished +
                ", isStop=" + isStop +
                ", isDownloading=" + isDownloading +
                '}';
    }
}
