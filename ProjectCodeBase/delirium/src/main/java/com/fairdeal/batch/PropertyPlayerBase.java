package com.fairdeal.batch;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBigDecimal;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.ICsvBeanReader;

public class PropertyPlayerBase {

	protected ICsvBeanReader reader;
	
	public final static String[] classifiedHeader = { "shorttitle", "description", "address", "city", "state", "pincode", "contactNumber", "email", "price", "userId", "type" };
	public final static CellProcessor[] classifiedProcessor = new CellProcessor[] {new NotNull(),
			new NotNull(), new Optional(), new Optional(), 
			new Optional(), new Optional(), new NotNull(), 
			new Optional(),new ParseBigDecimal(),new Optional(),
			new ParseInt()
	};
}
