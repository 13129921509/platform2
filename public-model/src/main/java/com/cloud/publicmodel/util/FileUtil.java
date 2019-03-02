package com.cloud.publicmodel.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

public class FileUtil {
    public static File[] getFilesNameByPath(String path){
        File file = new File(path);
        File[] fileLists = file.listFiles();
        return fileLists;
    }
}
