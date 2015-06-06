package com.fairdeal.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.JSONValue;

import com.fairdeal.Constants;
import com.fairdeal.entity.Classified;
import com.fairdeal.entity.User;

public class Util {

	private static final String imagePath = "../../../../../../Images";
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	private static final SimpleDateFormat display_date_format = new SimpleDateFormat("dd-MMMMMMMM-yyyy");

	public static String getJSON(List object) {
		return JSONValue.toJSONString(object);
	}

	public static String[] getAdminUsers(){
		String[] admin = {"puneet739@gmail.com"};
		return admin;
	}
	
	/**
	 * This function will return the current Date
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static Date getClassifiedExpiryDate() {
		Calendar cal = Calendar.getInstance();
		int daystoExpire = Config.getInt(Constants.Config.CLASSIFIED_EXPIRY_DAYS);
		cal.add(Calendar.DATE, daystoExpire);
		return cal.getTime();
	}

	public static Date uploadImage() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static String saveFile(InputStream inputStream, String filename) {
		return saveFile(inputStream, Config.getString(Constants.Config.AGENTS_IMAGE_STORE_PATH), filename);
	}

	//Used only for AOP catch and doing validation. 
	public void classifiedDetailsFetch(Classified classified, User loggedInUser){
		LoggerUtil.debug("This function is used to send email via AOP");	
	}
	
	//Used only for AOP catch and doing validation.
	public void sendUserQuery(Classified classified, User loggedInUser){
		LoggerUtil.debug("This function is used to send email via AOP");	
	}
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);
		byte[] b = new byte[2048];
		int length;
		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}
		is.close();
		os.close();
	}
	
	public static String saveFile(InputStream inputStream, String outputPath, String filename) {
		String finalFilePath = null;
		if (inputStream != null)
			try {
				File file = new File(outputPath + filename);
				LoggerUtil.debug("Saving the file at :" + file.getAbsolutePath());
				OutputStream outputStream = new FileOutputStream(file);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				finalFilePath = filename;
			} catch (IOException e) {
				LoggerUtil.error("Exception caused while saving the image");
			} catch (NullPointerException ne) {
				LoggerUtil.error(" Null pointer Exceptoin occured, user have not provided the file");
			}

		return finalFilePath;
	}

	public static String saveResizedImageFile(InputStream inputStream, String outputPath, String filename) {
		return saveResizedImageFile(inputStream,outputPath,filename,false);
	}
	
	public static String saveResizedImageFile(InputStream inputStream, String outputPath, String filename, boolean maintainRatio) {
		String finalFilePath = null;
		if (inputStream != null)
			try {
				BufferedImage originalImage = ImageIO.read(inputStream);
				if (originalImage == null)
					return null;
				int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
				File file = new File(outputPath + filename);
				BufferedImage resizedImage = saveResizedImage(originalImage, type, maintainRatio);
				ImageIO.write(resizedImage, "png", file);
				finalFilePath = filename;
			} catch (IOException e) {
				e.printStackTrace();
			}
		return finalFilePath;
	}

	private static BufferedImage saveResizedImage(BufferedImage originalImage, int type , boolean maintainRatio) {
		int height = Config.getInt(Constants.Config.IMAGE_HEIGHT);
		int width = Config.getInt(Constants.Config.IMAGE_WIDTH);
		if (maintainRatio==true){
			Dimension originalDimensions=new Dimension(originalImage.getWidth(), originalImage.getHeight());
			Dimension newDimension=new Dimension(height,width);
			Dimension finalDimension=getScaledDimension(originalDimensions, newDimension);
			width=(int)finalDimension.getWidth();
			height=(int)finalDimension.getHeight();
		}
		
		String waterMarkString = Config.getString(Constants.Config.IMAGE_WATERMARK_TEXT);
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, width, height, null);
		g.setFont(new Font("baskerville", Font.BOLD, 30));
		g.drawString(waterMarkString, width/2-100, height-20);
		g.dispose();
		return resizedImage;
	}

	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
	    int original_width = imgSize.width;
	    int original_height = imgSize.height;
	    int bound_width = boundary.width;
	    int bound_height = boundary.height;
	    int new_width = original_width;
	    int new_height = original_height;
	    // first check if we need to scale width
	    if (original_width > bound_width) {
	        //scale width to fit
	        new_width = bound_width;
	        //scale height to maintain aspect ratio
	        new_height = (new_width * original_height) / original_width;
	    }
	    // then check if we need to scale even with the new height
	    if (new_height > bound_height) {
	        //scale height to fit instead
	        new_height = bound_height;
	        //scale width to maintain aspect ratio
	        new_width = (new_height * original_width) / original_height;
	    }
	    return new Dimension(new_width, new_height);
	}
	public static List<String> getTags(String text) {
		List<String> currenttags = new LinkedList<String>();
		List<Object> allTags = Config.getList("dlm.tags.alltags");
		for (Object tag : allTags) {
			String currentTag = ((String) tag).toUpperCase();
			if (text.toUpperCase().contains(currentTag)) {
				currenttags.add(currentTag);
			}
		}
		return currenttags;
	}

	public static BigDecimal getbigDecimanPrice(String price) {
		try {
			return new BigDecimal(price);
		} catch (Exception e) {
			LoggerUtil.error("Exception caused while parsing price to big decimal" + price);
		}
		return BigDecimal.ZERO;
	}

	public static Date getDateFromString(String dateString) {
		Date date = null;
		if (dateString==null || dateString.trim().isEmpty()) return null;
		try {
			date = formatter.parse(dateString);
		} catch (Exception e) {
			LoggerUtil.error("Exception caused while fetching date" + dateString, e);
		}
		return date;
	}
	
	public static String getStringFromDate(Date date) {
		String dateString = null;
		try {
			dateString = display_date_format.format(date);
		} catch (Exception e) {
			LoggerUtil.error("Exception caused while fetching date" + dateString, e);
		}
		return dateString;
	}

}
