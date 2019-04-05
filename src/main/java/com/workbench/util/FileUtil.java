package com.workbench.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by niu_ben on 2016/11/30.
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void main(String[] args) {
        FileUtil fileUtils = new FileUtil();

        /**
         String xmlStr = "<dept-root DeptId=\"beijing\" DeptName=\"北京市\">"
         + "<dept-1 DeptId=\"201010000008\" DeptName=\"北京市委\">"
         + "<dept-2 DeptId=\"201010000010\" DeptName=\"市委办公厅\">"
         + "<dept-3 DeptId=\"201010000070\" DeptName=\"机要局\">"
         + "<dept-4 DeptId=\"201010003147\" DeptName=\"信息化运维2\">"
         + "<member CertNo=\"020110010003108002\" LoginName=\"201010003108\" Order=\"2\" Roles=\"33\" UserId=\"201010003108\" UserName=\"测试用人员1\">测试用人员1</member>"
         + "</dept-4></dept-3></dept-2></dept-1>"
         + "</dept-root>";
         fileUtils.writeFile(xmlStr);
         */


//        String s = fileUtils.readFile("C:\\syndata", "20161222152216.xml");
//        System.out.println(s);


//        String path = "ftp://172.18.116.36:21/File";
//        File file = new File(path);
//        System.out.println(file.getPath());


    }


    /**
     * read content from a text
     *
     * @param directory 路径
     * @param fileName  文件名
     */
    public String readFile(String directory, String fileName) {
        String res = "";
        File file = new File(directory, fileName);
        if (file.exists()) {
            FileInputStream fis = null;
            InputStreamReader isr = null;
            BufferedReader bufferedReader = null;
            try {
                // 读取文件内容 (输入流)
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis, "UTF-8");

                bufferedReader = new BufferedReader(isr);

                String str = "";

                while ((str = bufferedReader.readLine()) != null) {
                    res += str;
                    System.out.print(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    bufferedReader.close();
                    isr.close();
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return res;
    }

    /**
     * @param directory 路径
     * @param fileName  文件名
     * @param content   要写入的内容
     */
    public void writeFile(String directory, String fileName, String content) {
        if ("".equals(directory) || directory == null) {
            String separator = File.separator;
            directory = "C:" + separator + "syndata";
        }
        if ("".equals(fileName) || fileName == null) {
            fileName = DateUtils.getCurrDate("yyyyMMddHHmmss") + ".xml";
        }

        File file = new File(directory, fileName);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getName());
            System.out.println(file.length());
        } else {
            try {
                file.createNewFile(); //
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        byte[] bytes = new byte[1024];
        bytes = content.getBytes();
        try {
            FileOutputStream in = new FileOutputStream(file);
            try {
                in.write(bytes, 0, bytes.length);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // 读取文件内容 (输入流)
            FileInputStream out = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(out);
            int ch = 0;
            while ((ch = isr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param file
     */
    public static String getFileMd5(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}
