package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigReader {
   private static Properties prop;

    public static  void configreader(){
        // do not use hard coded path, use dynamic path
    //  String projectfolder=  System.getProperty("user.dir");
        if(prop==null){
            prop= new Properties();
            try{
                File file = new File("src/test/resources/config/config.properties");
                FileInputStream fis = new FileInputStream(file);
                prop.load(fis);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static String get(String key){
        configreader();
        return prop.getProperty(key);
    }

    public static String getAppUrl(){
        configreader();
        return prop.getProperty("url");
    }

    public static void main(String[] args) {
        System.out.println(getAppUrl());
    }

}
