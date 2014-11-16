package com.sdw.soft.common.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sdw.soft.core.utils.DateUtils;
import com.sdw.soft.core.utils.RandomUtils;

/**
 * @author Sonicery_D
 * @date 2014年11月16日
 * @version 1.0.0
 * @description 文件上传
 **/
@Controller
@RequestMapping("/fileService")
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String fileUpload(@RequestParam(value="fileList",required=false) MultipartFile[] fileselect,HttpServletRequest request){
		OutputStream os = null;
		for(MultipartFile mpFile : fileselect){
			if(!mpFile.isEmpty()){
				String originalName = mpFile.getOriginalFilename();
				String sufix = originalName.substring(originalName.indexOf('.'), originalName.length());
				String fileRealName = DateUtils.getCurrentDateString()+RandomUtils.getRandom() + sufix;
				try {
					String realPath = request.getSession().getServletContext().getRealPath("/upload/");
					logger.info("realPath:{}",realPath);
					File file = new File(realPath);
					if(file.exists()){
						file.mkdirs();
					}
					os = new BufferedOutputStream(new FileOutputStream(file+"/"+fileRealName));
					os.write(mpFile.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
					logger.info(e.getMessage(),e);
				}finally{
					try {
						os.flush();
						if(os != null){
							os.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						logger.info(e.getMessage(),e);
					}
				}
				logger.info(originalName);
			}
		}
		return "/common/success";
	}
}
