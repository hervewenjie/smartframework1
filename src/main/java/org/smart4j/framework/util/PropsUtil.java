package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chengwenjie on 2017/2/6.
 * 属性文件工具类
 */
public class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载属性文件
     */
    public static Properties loadProps(String fileName){
        Properties props = null;
        InputStream in   = null;
        try {
            in = ClassUtil.getClassLoader().getResourceAsStream(fileName);
            if (in==null){
                throw new FileNotFoundException(fileName + "File not Found");
            }
            props = new Properties();
            props.load(in);
        } catch (IOException e){
            LOGGER.error("Load properties file failure", e);
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e){
                    LOGGER.error("Close input stream failure", e);
                }
            }
        }
        return props;
    }

    /**
     * 获取String类型的属性值 (默认值空字符串)
     * @return
     */
    public static String getString(Properties props, String key){
        return getString(props, key, "");
    }

    /**
     * 获取String类型的属性值 (默认值空字符串)
     * @return
     */
    public static String getString(Properties props, String key, String def){
        String val = def;
        if (props.containsKey(key)){
            val = props.getProperty(key);
        }
        return val;
    }

    /**
     * 获取 int 类型的属性值（默认值为 0）
     */
    public static int getInt(Properties props, String key) {
        return getInt(props, key, 0);
    }

    /**
     * 获取 int 类型的属性值（可指定默认值）
     */
    public static int getInt(Properties props, String key, int defaultValue) {
        int value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castInt(props.getProperty(key));
        }
        return value;
    }

    /**
     * 获取 boolean 类型属性（默认值为 false）
     */
    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props, key, false);
    }

    /**
     * 获取 boolean 类型属性（可指定默认值）
     */
    public static boolean getBoolean(Properties props, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (props.containsKey(key)) {
            value = CastUtil.castBoolean(props.getProperty(key));
        }
        return value;
    }



}
