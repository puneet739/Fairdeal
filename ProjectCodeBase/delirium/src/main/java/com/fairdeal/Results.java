package com.fairdeal;

public class Results {

	public static final int SUCCESS=0;
	
	public static class AGENT{
		public static final int EMAIL_ALREADY_REGISTERED=200;
	}
	
	public static class User{
		public static final int USER_ALREADY_REGISTERED=300;
		public static final int USER_DOES_NOT_EXIST=301;
		public static final int USER_CLASSIFIED_QUOTA_OVER=302;
	}
}
