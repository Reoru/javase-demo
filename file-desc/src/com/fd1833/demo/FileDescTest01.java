package com.fd1833.demo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 需求：
 * 获取一个文件路径
 * 将该路径的所有文件夹放入一个集合中，方便迭代
 * 取出集合中的元素进行迭代，利用fileList方法将所有文件夹中的文件拿到，在迭代该数组
 * 拿到所有的文件，再根据文件的修改日期比较大小，从小到大排序，存入新的集合中
 * 在根据新的集合内容，统一重命名，放入一个文件夹中
 */
public class FileDescTest01 {
    public static void main(String[] args) {
        //需要将所有已重命名的文件移动到的文件夹
        String resultPath = "D:\\design\\pictures01\\";
        //初始迭代文件夹，内部需要的是【文件夹，不可有文件】，然后文件夹内部是【文件，不可有文件夹】
        String dirPath = "D:\\design\\design";
        long startTime = System.currentTimeMillis();
        File file = new File(dirPath);
        File[] files = file.listFiles();
        // 排序文件夹，按照修改日期排序，从小到大
        if(files.length>1){
            files = descDirs(files);
        }
        // 迭代文件夹集合，将单个文件夹内的所有文件按照修改日期进行排序，从小到大
        List<File> filesList = new ArrayList<>();
        for (File f : files) {
            filesList = descFiles(f, filesList);
        }
        // 拿到所有已经排序好的文件进行重命名
        for (File f : filesList) {
            String fileName = f.getName();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            String newFileName = System.currentTimeMillis() + "." + suffix;
            System.out.println(newFileName);
            try {
                Thread.sleep(1);
                f.renameTo(new File(resultPath + newFileName));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime));
    }


    /**
     * 根据文件修改日期从小到大排序
     *
     * @param file      文件
     * @param filesList 将文件夹内的所有文件整合起来
     * @return
     */
    public static List<File> descFiles(File file, List<File> filesList) {
        File[] files = file.listFiles();
        File tempFile = null;
        for (int i = 0; i < files.length; i++) {
            for (int x = 0; x < files.length - 1 - i; x++) {
                if (files[x].lastModified() > files[x + 1].lastModified()) {
                    tempFile = files[x];
                    files[x] = files[x + 1];
                    files[x + 1] = tempFile;
                }
            }
        }

        for (File f : files) {
            filesList.add(f);
        }
        return filesList;
    }

    /**
     * 根据文件夹修改日期从小到大排序
     *
     * @param files 文件夹
     * @return
     */
    public static File[] descDirs(File[] files) {
        File tempFile = null;
        for (int i = 0; i < files.length; i++) {
            for (int j = 0; j < files.length - 1 - i; j++) {
                if (files[j].lastModified() > files[j + 1].lastModified()) {
                    tempFile = files[j];
                    files[j] = files[j + 1];
                    files[j + 1] = tempFile;
                }
            }
            System.out.println();
        }

        return files;
    }
}
