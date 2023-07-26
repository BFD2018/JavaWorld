package com.xjt.javase.apache_commons.commons_io;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * @author jtxiong
 * @version 1.0
 * @description: TODO
 * @date 2023/7/26 20:26
 */
public class MyFileAlterationListener extends FileAlterationListenerAdaptor {
    /**
     * File created Event.
     *
     * @param file The file created (ignored)
     */
    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
        System.out.println(file.getAbsolutePath() + "---onFileCreate");
    }

    /**
     * File changed Event.
     *
     * @param file The file changed (ignored)
     */
    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        System.out.println(file.getAbsolutePath() + "---onFileChange");
    }

    /**
     * File deleted Event.
     *
     * @param file The file deleted (ignored)
     */
    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
        System.out.println(file.getAbsolutePath() + "---onFileDelete");
    }
}
