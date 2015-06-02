package com.fairdeal.service.impl;

import java.io.InputStream;
import java.util.List;

import com.fairdeal.Results;
import com.fairdeal.batch.ClassifiedBatch;
import com.fairdeal.dao.ClassifiedDao;
import com.fairdeal.entity.Classified;
import com.fairdeal.service.BulkUploadService;
import com.fairdeal.util.ObjectRepository;

public class BulkUploadServiceImpl implements BulkUploadService {

	@Override
	public Integer bulkUpload(InputStream inputStream) {
		List<Classified> classifiedList =  ObjectRepository.getBean(ClassifiedBatch.class).getclassified(inputStream);
		for (Classified classified: classifiedList){
			ObjectRepository.getBean(ClassifiedDao.class).insertClassified(classified);
		}
		return Results.SUCCESS;
	}

}
