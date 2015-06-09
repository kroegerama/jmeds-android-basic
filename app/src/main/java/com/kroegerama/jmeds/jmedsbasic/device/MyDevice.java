package com.kroegerama.jmeds.jmedsbasic.device;

import org.ws4d.java.communication.DPWSCommunicationManager;
import org.ws4d.java.service.DefaultDevice;
import org.ws4d.java.types.QName;
import org.ws4d.java.types.QNameSet;
import org.ws4d.java.types.ScopeSet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDevice extends DefaultDevice {

	String[]	scopes;

	ScopeSet	scopeList		= null;

	QNameSet	qnsDeviceTypes	= null;

	public MyDevice() {
		super(DPWSCommunicationManager.COMMUNICATION_MANAGER_ID);
		scopeList = new ScopeSet(new String[] { "urn:AndroidDevice" });

		qnsDeviceTypes = new QNameSet(new QName("MyDevice", "http://www.materna.de/bui/"));

		addFriendlyName("en-GB", "Test Device");
		addFriendlyName("DE-de", "Test Gerät");

		addManufacturer("de-DE", "Materna GmbH");
		addManufacturer("en-GB", "Materna Ltd.");
		addManufacturer("en-US", "Materna Inc.");

		addModelName("de-DE", "Android");
		addModelName("en-GB", "Android");

		setFirmwareVersion("v1.0");
		setManufacturerUrl("http://www.manu-url.de");
		setModelNumber("1288-4859-125648-25");
		setModelUrl("https://model-homepage.de");
		setSerialNumber("3-44888490-3482-dfjae-11");
		setPresentationUrl("http://www.presentation.de/MyDevice");

		setScopes(scopeList);
		setPortTypes(qnsDeviceTypes);
	}

	/**
	 * Returns the current time in format HH:MM:SS.mmm.
	 * 
	 * @return The current time in format HH:MM:SS.mmm.
	 */
	static String getCurrentTime(String format) throws IllegalArgumentException {
		if (format == null || format.length() == 0) format = "HH:mm:ss:SSS";
		SimpleDateFormat dateformat = new SimpleDateFormat(format);

		return dateformat.format(new Date());
	};
}
