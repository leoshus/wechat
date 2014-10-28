package com.sdw.soft.test.reply;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sdw.soft.common.reply.service.IReplyService;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/context/spring-*.xml"})
public class ReplyTest {

	@Autowired
	private IReplyService ReplyService;
	
	@Test
	public void test01(){
		String tmp = ReplyService.dealReceiveMessage(null);
		System.out.println("tmp:"+tmp);
	}
}
