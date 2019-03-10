package com.cloud.publicmodel.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class FileUtil {
    public static File[] getFilesNameByPath(String path){
        File file = new File(path);
        File[] fileLists = file.listFiles();
        return fileLists;
    }

    public static String getUniqueCode(){
        return UUID.randomUUID().toString();
    }
}
