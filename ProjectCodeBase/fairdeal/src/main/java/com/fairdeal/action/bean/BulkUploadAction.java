package com.fairdeal.action.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fairdeal.service.BulkUploadService;
import com.fairdeal.util.LoggerUtil;
import com.fairdeal.util.ObjectRepository;

@Component(value = "bulkuploadaction")
@Scope(value = "session")
public class BulkUploadAction {

	private UploadedFile uploadedFile;

	public void doBulkUploadClassifieds() {
		int result =-1;
		try {
			LoggerUtil.debug("We are uploading the Bulk File");
			result = ObjectRepository.getBean(BulkUploadService.class).bulkUpload(uploadedFile.getInputstream());
		} catch (BeansException b) {
			LoggerUtil.error("Exception is caused while trying to get the bean",b);
		} catch (IOException e) {
			LoggerUtil.error("Exception is caused while get the Uploaded File",e);
		}
		if(result==0){
			FacesContext.getCurrentInstance().addMessage("messages", new FacesMessage("File is uploaded successfully"));
		}
	}

	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	public void handleFileUpload(FileUploadEvent event) {
		LoggerUtil.debug("Uploading the image to mail file");
		setUploadedFile(event.getFile());
	}
}
