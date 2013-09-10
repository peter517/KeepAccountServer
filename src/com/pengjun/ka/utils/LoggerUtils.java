package com.pengjun.ka.utils;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.pengjun.ka.net.KaServer;

public class LoggerUtils {

	public static final Logger serviceLogger = Logger.getLogger(KaServer.class.getSimpleName());

	static {
		System.out.println(new File("").getAbsolutePath());
		PropertyConfigurator.configure("log4j.properties");
	}

}
