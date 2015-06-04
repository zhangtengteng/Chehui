package com.chehui.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Environment;

/**
 * Created by h00223604 on 2014-09-24.
 */
public final class FilePathUtils
{
    /**
     * 建立文件�?
     * 
     * @param path
     *            文件夹目�?
     */
    public static void makeDir(String path)
    {
        File file = new File(path);
        if (!file.exists() && !file.mkdirs())
        {
            LogN.w(Utils.class, "mkdirs failed");
        }
    }

    public static void makeParentDir(String filePath)
    {
        File file = new File(filePath).getParentFile();
        if (!file.exists() && !file.mkdirs())
        {
            LogN.w(Utils.class, "mkdirs failed");
        }
    }

    public static boolean isPathExsit(String path)
    {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 删除文件
     * 
     * @param filePath
     *            文件目录
     */
    public static boolean deleteFile(String filePath)
    {
        if (StringUtils.isEmpty(filePath))
        {
            LogN.w(Utils.class, "deleteFile | filePath is empty");
            return false;
        }

        File file = new File(filePath);

        return file.delete();
    }

    /**
     * 删除路径下所有文�?
     * 
     * @param dir
     *            路径
     * @return 空间大小字节�?
     */
    public static void deleteFilesInPath(File dir)
    {
        if (dir == null)
        {
            return;
        }

        if (!dir.isDirectory())
        {
            return;
        }

        File[] files = dir.listFiles();
        for (File file : files)
        {
            if (file.isFile())
            {
                if (!file.delete())
                {
                    LogN.e(Utils.class, "deleteFilesInPath delete failed");
                }
            }
            else if (file.isDirectory())
            {
                deleteFilesInPath(file);
            }
        }
    }

    /**
     * 获取路径占用空间大小
     * 
     * @param dir
     *            路径
     * @return 空间大小字节�?
     */
    public static long getDirSizeInByte(File dir)
    {
        if (dir == null)
        {
            return 0;
        }
        if (!dir.isDirectory())
        {
            return 0;
        }

        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files)
        {
            if (file.isFile())
            {
                dirSize += file.length();
            }
            else if (file.isDirectory())
            {
                // 如果遇到目录则�?�过递归调用继续统计
                dirSize += getDirSizeInByte(file);
            }
        }
        return dirSize;
    }

    /**
     * 获取路径占用空间大小
     * 
     * @param dir
     *            路径
     * @return 空间大小兆字节数
     */
    public static String getDirSizeInMB(File dir)
    {
        double size = getDirSizeInByte(dir) / (1024.0 * 1024);

        DecimalFormat df = new DecimalFormat("0.00");
        String filesize = df.format(size);

        return filesize;
    }

    public static Uri getTakePhotoUri()
    {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM));
        pathBuilder.append('/');
        pathBuilder.append("Camera");
        pathBuilder.append('/');
        pathBuilder.append("IMG_" + DateTimeUtils.getFormatTime(new Date()) + ".jpg");
        Uri uri = Uri.parse(StringUtils.localPathToUri(pathBuilder.toString()));
        File file = new File(uri.toString());
        file.getParentFile().mkdirs();
        return uri;
    }

    /**
     * 判断是否有外部存储设备sdcard
     * 
     * @return true | false
     */
    public static boolean isSdcardExit()
    {
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

}
