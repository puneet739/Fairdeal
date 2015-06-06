package com.fairdeal.test.servicetest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fairdeal.service.BulkUploadService;
import com.fairdeal.test.BaseTest;
import com.fairdeal.util.LoggerUtil;

public class BulkUploadServiceTest extends BaseTest{

	@Autowired
	BulkUploadService service;
	
	@Test
	public void doBulkUploadTest() throws FileNotFoundException{
		File file  = new File("ClassifiedBulk.csv");
		LoggerUtil.debug("File is uploaded to Bulk Upload: "+file.exists());
		service.bulkUpload(new FileInputStream(file));
	}
}
