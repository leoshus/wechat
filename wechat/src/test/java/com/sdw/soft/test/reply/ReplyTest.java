package com.sdw.soft.test.reply;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sdw.soft.common.dao.ICommonDao;
import com.sdw.soft.common.reply.service.IReplyService;

/**
 * @author Sonicery_D
 * @date 2014年10月29日
 * @version 1.0.0
 * @description
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/context/spring-*.xml"})
@ActiveProfiles("test")
public class ReplyTest {

	@Autowired
	private IReplyService ReplyService;

	@Test
	public void test01(){
		String requestXml =  "<xml><ToUserName><![CDATA[gh_4f3143843422]]></ToUserName><FromUserName><![CDATA[of2Yrt8f2LvQ8bhBttLDg4A045NI]]></FromUserName><CreateTime>1414512271</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[世界新技术]]></Content><MsgId>6075283943937494502</MsgId></xml>";
		String requestXml_local =  "<xml><ToUserName><![CDATA[gh_4f3143843422]]></ToUserName><FromUserName><![CDATA[123]]></FromUserName><CreateTime>1414512271</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[1]]></Content><MsgId>6075283943937494502</MsgId></xml>";
		String requestXml_subscribe = "<xml><ToUserName><![CDATA[gh_4f3143843422]]></ToUserName><FromUserName><![CDATA[of2Yrt0GWQxhrqgQnzBHI7u-I4tI]]></FromUserName><CreateTime>1414553972</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey></xml>";
		String requestXml_location = "<xml><ToUserName><![CDATA[gh_4f3143843422]]></ToUserName><FromUserName><![CDATA[of2Yrt-lLwp-mYq4PVpB2KNCX3Y0]]></FromUserName><CreateTime>1414553088</CreateTime><MsgType><![CDATA[location]]></MsgType><Location_X>40.099438</Location_X><Location_Y>116.273460</Location_Y><Scale>16</Scale><Label><![CDATA[昌平区生命园东环路]]></Label><MsgId>6075459251617619568</MsgId></xml>";
//		ReplyService.dealReceiveMessage(requestXml_local);
	}
}
