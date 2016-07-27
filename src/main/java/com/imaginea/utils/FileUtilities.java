package com.imaginea.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class FileUtilities {

	public boolean isLogFolderExists=false;
	public boolean isReportFolderExists= false;
	
	public void deleteExisitngFolder(String fPath){
		
		File file = new File(fPath);
		if(file.isDirectory()){
			try {
				FileUtils.cleanDirectory(file);
				FileUtils.forceDelete(file); 
		        FileUtils.forceMkdir(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
           
		}
	}
}
