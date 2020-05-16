package com.edsoft.framework.configs;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.edsoft.framework.base.BrowserType;

public class ConfigReader {
	  public static  void PopulateSettings() throws IOException {
	        ConfigReader reader = new ConfigReader();
	        reader.ReadProperty();
	    }

	    private void ReadProperty() throws IOException {
	        Properties p = new Properties();
//	        InputStream inputStream = new FileInputStream("src/main/java/com/edsoft/framework/configs/GlobalConfig.properties");
	        InputStream inputStream = new FileInputStream("GlobalConfig.properties");
	        p.load(inputStream);
	        
	        //Get AUTConnection String
	        Settings.AUTConnectionString = p.getProperty("AUTConnectionString");
	        //Get LogPath
	        Settings.LogPath = p.getProperty("LogPath");
	        //Get DriverType
	        Settings.DriverType = p.getProperty("DriverType");
	        //GEt ExcelSheetPath
	        Settings.ExcelSheetPath = p.getProperty("ExcelSheetPath");
	        //Get AUT
	        Settings.AUT = p.getProperty("AUT");
	        //Browser Type
	        Settings.BrowserType = BrowserType.valueOf(p.getProperty("BrowserType"));
	        //hub selenium
	        Settings.SeleniumGridHub = p.getProperty("SeleniumGrid");
	        
	        Settings.ExcelPoi = p.getProperty("ExcelPoi");
	    }
}
