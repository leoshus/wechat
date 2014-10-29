package com.sdw.soft.core.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;


/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description freemarker 工具类
 */
public class FreemarkerUtils {
	private static final Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

	/**
	 * 数据与模板集合
	 * @param resultMap
	 * @param templateName
	 * @return
	 */
	public static String data2Template(Map<String,String> resultMap,String templateName){
		StringWriter writer = null;
		String content = "";
		Template template = null;
		try {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			File file = new File(FreemarkerUtils.class.getResource("/template").getPath());
			configuration.setDirectoryForTemplateLoading(file);
			template = configuration.getTemplate(templateName);
			writer = new StringWriter();
			template.process(resultMap, writer);
			content = writer.toString();
			writer.close();
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return content;
	}
	
	public static void main(String[] args){
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("username", "斯蒂芬");
		String tmp = data2Template(resultMap, "welcome.ftl");
		System.out.println("tmp:"+tmp);
	}
}
