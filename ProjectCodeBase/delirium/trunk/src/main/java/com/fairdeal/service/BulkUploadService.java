package com.fairdeal.service;

import java.io.InputStream;

import javax.transaction.Transactional;

@Transactional
public interface BulkUploadService {

	public Integer bulkUpload(InputStream inputStream);
}
