package com.fd1833.demo;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author RRReoru
 * @version 1.0
 * @date 2020/6/25 0025 上午 11:33
 */
public class Apps {
    // 切换器的files目录
    private static final File MAIN_PATH_FILE = new File("E:\\猛汉王\\商店文件切换器 1.0\\file\\");
    private static final File MOD_PATH_FILE = new File("E:\\猛汉王\\好看的服装mod合集\\商店mod\\");
    private static int count = 1;
    private static final String ABSULTE_PATH = MAIN_PATH_FILE.getAbsolutePath() + "\\";

    public static void main(String[] args) {
        //清空mainPath目录下的所有文件
        if (clear(MAIN_PATH_FILE)) {
            changeFileName(MOD_PATH_FILE);
        } else {
            throw new RuntimeException("不是一个文件夹!");
        }
    }

    private static boolean clear(File file) {
        if (Objects.nonNull(file)) {
            if (file.isDirectory() && file.listFiles().length == 0) {
                return true;
            } else {
                File[] files = file.listFiles();
                Stream.of(files).forEach((f) -> f.delete());
                return true;
            }
        }
        return false;
    }

    private static void changeFileName(File file) {
        File[] files = file.listFiles();
        for (File f : files) {
            if (f.isDirectory()) {
                changeFileName(f);
            } else if (f.getName().endsWith(".slt")) {
                System.out.println(f.getName());
                File f1 = new File(ABSULTE_PATH + count + ".slt");
                System.out.println(f1);
                boolean b = f.renameTo(f1);
                System.out.println(b);
                count++;
            }
        }
    }
}
