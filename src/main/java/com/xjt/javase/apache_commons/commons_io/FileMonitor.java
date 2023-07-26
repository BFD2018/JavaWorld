package com.xjt.javase.apache_commons.commons_io;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.ArrayList;

/**
 * @author jtxiong
 * @version 1.0
 * @description: 封装一个文件监听器
 * @date 2023/7/26 20:00
 */
public class FileMonitor{
    private FileAlterationMonitor monitor;

    public FileMonitor(long interval) {
        monitor = new FileAlterationMonitor(interval);
    }

    //监听path路径下的文件
    public void monitor(String path, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    //监听path路径下指定后缀名为suffix的文件
    public void monitor(String path,String suffix, FileAlterationListener listener) {
        // Create a FileFilter
        IOFileFilter directories = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(), HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter("." + suffix));
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        FileAlterationObserver observer = new FileAlterationObserver(path,filter);
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    // 监听path路径下不少于一个文件（指定 文件名的）
    public void monitor(String[] filesPath, FileAlterationListener listener) {
        if(filesPath.length < 1 ){
            return;
        }
        File parentFile = new File(filesPath[0]).getParentFile();

        ArrayList<IOFileFilter> fileFilters = new ArrayList<>();
        for (String filePath : filesPath) {
            File file = new File(filePath);
            IOFileFilter filter = FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.nameFileFilter(file.getName()));
            fileFilters.add(filter);
        }

        IOFileFilter fileFilter = FileFilterUtils.or(fileFilters.toArray(new IOFileFilter[]{}));
        FileAlterationObserver observer = new FileAlterationObserver(parentFile.getAbsolutePath(),fileFilter);
        monitor.addObserver(observer);
        observer.addListener(listener);
    }

    public void stop() throws Exception {
        monitor.stop();
    }

    public void start() throws Exception {
        monitor.start();
    }
}