package com.fairdeal.batch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.fairdeal.entity.Classified;
import com.fairdeal.util.LoggerUtil;

@Component
public class ClassifiedBatch extends PropertyPlayerBase {
	
	public List<Classified> getclassified(InputStream inputStream) {
		List<Classified> classifiedList = new LinkedList<Classified>();
		try {
			reader = new CsvBeanReader(new InputStreamReader(inputStream), CsvPreference.STANDARD_PREFERENCE);
			reader.getHeader(true);
			Classified classified;
			while ((classified = reader.read(Classified.class, classifiedHeader, classifiedProcessor)) != null) {
				classifiedList.add(classified);
			}
		} catch (IOException e) {
			LoggerUtil.error("Exception is caused while reading the Input Stream", e);
		}
		LoggerUtil.debug("Final Classified List uploaded is " + classifiedList);
		return classifiedList;
	}

	public List<Classified> getClassified(File file) throws IOException {
		InputStream io = new FileInputStream(file);
		return getclassified(io);
	}

}
