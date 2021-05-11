package com.yaof.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yaof
 * @Date 2021-04-30
 */
public class FileUtils {

    public static void main(String[] args){
        JSONObject jo = new JSONObject();
        jo.put("name", "yaofang");
        jo.put("age", "161");
        jo.put("address", "a上海市");
        try {
            writeFile(JSON.toJSONString(jo), "d://test.js", false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 往文件中写入内容
     * @param content 写入内容
     * @param filePath 写入文件地址
     * @param append 是否追加写入
     * @throws Exception
     */
    public static void writeFile(String content, String filePath, boolean append) throws Exception{
        File file = new File(filePath);
        if(!file.exists()){
            //如果文件不存在，就创建该文件
            file.createNewFile();
        }
        //如果文件已存在，那么就在文件末尾追加写入
        //这里构造方法多了一个参数,true表示在文件末尾追加写入,false表示重新写入
        FileOutputStream fos = new FileOutputStream(file, append);

        //指定以UTF-8格式写入文件
        OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
        osw.write(content);

        //写入完成关闭流
        osw.close();
        fos.close();
    }
}
