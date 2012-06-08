/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package com.starit.diamond.server.utils;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * @author bsli@starit.com.cn
 * @date 2012-5-26 上午10:58:54
 */
public class ProfileUtil {
	private static String profile = "";
	
	static {
		try {
			ClassPathResource resource = new ClassPathResource("META-INF/res/profile.properties");
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			profile = properties.getProperty("spring.profiles.active");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String getValue() {
		return profile;
	}
}
