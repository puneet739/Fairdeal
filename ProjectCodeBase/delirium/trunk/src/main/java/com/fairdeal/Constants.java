package com.fairdeal;

public class Constants {

	public static final String IMAGE_FORMAT="png";
	
	public static class User{
		public static final int AGENT_DELETED=1;
		public static final int AGENT_NOT_DELETED=0;
		
		public static final int MAILER_DISABLED=1;
		public static final int MAILER_ENABLED=0;
		
		public static final String ROLE_ADMIN="ROLE_ADMIN";
		public static final String ROLE_USER="ROLE_USER";
		public static final String ROLE_BROKER="ROLE_BROKER";

		
		public static final int PLATINUM_CATEGORY=1;
		public static final int GOLD_CATEGORY=2;
		public static final int SILVER_CATEGORY=3;
		
		public static final int GENERAL_USER=9;
	}
	
	public static class Classified{
		public static final int CLASIFIED_DELETED=1;
		public static final int CLASIFIED_NOT_DELETED=0;
		public static final String CLASIFIED_IMAGE1="image1";
		public static final String CLASIFIED_IMAGE2="image2";
		public static final String CLASIFIED_IMAGE3="image3";
		public static final String CLASIFIED_IMAGE4="image4";
		
		public static final int TYPE_SELL=1;
		public static final int TYPE_RENT=2;
		
		public static final int PLATINUM_CATEGORY=1;
		public static final int GOLD_CATEGORY=2;
		public static final int SILVER_CATEGORY=3;
		
		public static final int GENERAL_CATEGORY=9;
	}
	
	public static class Agent{
		public static final int AGENT_DELETED=1;
		public static final int AGENT_NOT_DELETED=0;
		
		public static final String AGENT_MAIN_IMAGE="agentImage";
		public static final String AGENT_IMAGE1="image1";
		public static final String AGENT_IMAGE2="image2";
		public static final String AGENT_IMAGE3="image3";
		public static final String AGENT_IMAGE4="image4";
	}

	public static class UserQuery{
		public static final int QUERY_ANSWERED=1;
		public static final int QUERY_UNANSWERED=2;
	}
	
	public static class EmailTemplates{
		public static final String REGISTER_TEMPLATE="registerTemplate.html";
		public static final String REGISTER_SUBJECT="Welcome to Property Player";
		
		public static final String VIEWCLASSIFIED_TEMPLATE="viewclassified.html";
		public static final String USER_QUERY_TEMPLATE="userquery.html";
		
	}
	
	public static class Config{
		public static final String AGENTS_IMAGE_STORE_PATH="dlm.agents.image.location";
		public static final String ALL_TAGS="dlm.tags.alltags";
		public static final String CLASSIFIED_EXPIRY_DAYS="dlm.classified.expire";
		public static final String CLASSIFIED_IMAGE_STORE_PATH="dlm.classified.image.location";
		public static final String CLASSIFIED_PAGINATION_SIZE="dlm.classified.pagination.size";
		public static final String AGENT_SUPERAGENT_ID="dlm.agent.superagent.agentid";
		public static final String IMAGE_HEIGHT="dlm.image.resize.height";
		public static final String IMAGE_WIDTH="dlm.image.resize.width";
		public static final String IMAGE_WATERMARK_TEXT="dlm.image.watermark.text";
		public static final String APPLICATION_NAME = "dlm.application.name";
		public static final String SUGGESTIONS_MAIN = "dlm.suggestion.main";
		public static final String SUGGESTIONS_STATE = "dlm.suggestion.state";
		public static final String SUGGESTIONS_CITY = "dlm.suggestion.city";
		public static final String CLASSIFIED_QUOTA_ORIGINAL = "dlm.user.classifid.default.quota";
		public static final String EMAIL_ADMIN = "dlm.user.classifid.default.quota";
	}
	
	public static class AppConfig{
		public static final String RE_CAPTCHA_KEY="6Lf3EAcTAAAAAI9POA1dFQkLEUKLp1rq6MNg_2CD";
		public static final String RE_CAPTCHA_RESPONSE="g-recaptcha-response";
		public static final String RE_CAPTCHA_URL="https://www.google.com/recaptcha/api/siteverify";
	}
}
