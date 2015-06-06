package com.fairdeal.test.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.Constants;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.Config;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.Util;

public class UtilTest extends BaseTest{
	
	@Autowired
	Config config;

	@Test
	public void testSaveInputStreamToFile(){
		String filePath = Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH);
		File file = new File(filePath+"puneet.properties");
		file.exists();
		LoggerUtil.debug("File absolute Path is "+file.getAbsolutePath());
	}
	
	@Test
	public void testReadingconfiguration(){
		String configOutPut = Config.getString("puneet.test");
		assertEquals("TestRun", configOutPut);
	}
	
	@Test
	public void testDisplayDateFormat(){
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 06, 22);
		Date date = cal.getTime();
		String dateString = Util.getStringFromDate(date);
		assertEquals("22-July-2014", dateString);
		
		cal.set(2014, 10, 22);
		date = cal.getTime();
		String dateString2 = Util.getStringFromDate(date);
		assertEquals("22-November-2014", dateString2);
		LoggerUtil.debug("dateString"+dateString);
		
	}
	
	@Test
	public void testLoadAllPropertyFilesinFolder(){
		String configOutPut = Config.getString("puneet.test");
		assertEquals("TestRun", configOutPut);
		assertEquals("loaded", Config.getString("included.properties"));
		assertEquals(null, Config.getString("application2.propesr"));
	}
	
	@Test
	public void testTagsGenerated(){
		String desc = "This is a test description from noida. Faridabad is a good place. ";
		List<String> obj = Util.getTags(desc);
		assertTrue(obj.contains("NOIDA"));
		assertTrue(obj.contains("FARIDABAD"));
		assertFalse(obj.contains("DELHI"));
	}
	
	@Test
	public void testreSizeFile() throws FileNotFoundException{
		InputStream file = new FileInputStream("D:/PuneetWork/MYProject/GIT/trunk/Images/66_agentImage.jpg");
		assertTrue(file!=null);
		String outputFileName = Util.saveResizedImageFile(file, Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), "testOutput.jpg");
		assertNotNull(outputFileName);
		File file1 = new File(Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH)+"testOutput.jpg");
		LoggerUtil.debug("The file Path is "+file1.getAbsolutePath());
		assertTrue(file1.exists());
		file1.delete();
	}
	
	@Test
	public void testreSizeFileMaintainingRatio() throws FileNotFoundException{
		InputStream file = new FileInputStream("D:/PuneetWork/Puneet Entertainment/images/puneet.jpg");
		assertTrue(file!=null);
		String outputFileName = Util.saveResizedImageFile(file, Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), "testOutput.png", true);
		assertNotNull(outputFileName);
		File file1 = new File(Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH)+outputFileName);
		LoggerUtil.debug("The file Path is "+file1.getAbsolutePath());
		assertTrue(file1.exists());
		file1.delete();
	}
	
	@Test
	public void testreSizeFileTextFile() throws FileNotFoundException{
		InputStream file = new FileInputStream("D:/PuneetWork/MYProject/GIT/trunk/Images/ContactAgentForm.txt");
		assertTrue(file!=null);
		String outputFileName = Util.saveResizedImageFile(file, Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), "testOutput.jpg");
		assertNull(outputFileName);
		File file1 = new File(Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH)+"testOutput.jpg");
		LoggerUtil.debug("The file Path is "+file1.getAbsolutePath());
		assertFalse(file1.exists());
	}
	
	@Test
	public void testresizeFileFileNotExist() throws FileNotFoundException{
		
		String outputFileName = Util.saveResizedImageFile(null, Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), "testOutput.jpg");
		assertNull(outputFileName);
		File file1 = new File(Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH)+"testOutput.jpg");
		LoggerUtil.debug("The file Path is "+file1.getAbsolutePath());
		assertFalse(file1.exists());
	}
	
	public void testSaveInputStreamToFileInputstreamNull(){
	}
	
	public void testSaveInputStreamToFilePathFromConfig(){
	}
	
	@Test
	public void testGetDateFromFormat(){
		String currentDate ="01/11/2014";
		Date date = Util.getDateFromString(currentDate);
		assertNotNull(date);
	}
	
	@Test
	public void testGetDateFromFormatException(){
		String currentDate ="55/13/2014";
		Date date = Util.getDateFromString(currentDate);
		assertNotNull(date);
	}
	
}
