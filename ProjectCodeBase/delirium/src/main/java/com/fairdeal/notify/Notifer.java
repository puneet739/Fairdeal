package com.fairdeal.notify;

/**
 * All the classes which needs to be notified should impliment this class
 * 
 * @author pbehl
 */
public interface Notifer {
	
	public void doNotify(Object... allObjects);

	
}
