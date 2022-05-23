package com.justmax.virtualstyler.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Download {
    public static void downloadFile(String url, File dest) throws IOException {
        FileUtils.copyURLToFile(new URL(url), dest);
    }
}
