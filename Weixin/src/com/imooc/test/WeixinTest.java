package com.imooc.test;

import java.util.HashMap;
import java.util.Map;

import com.imooc.menu.Button;
import com.imooc.menu.Menu;
import com.imooc.menu.NewButton;
import com.imooc.menu.SubButton;
import com.imooc.po.AccessToken;
import com.imooc.util.MessageUtil;
import com.imooc.util.WeixinUtil;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据" + token.getToken());
			System.out.println("有效时间" + token.getExpiresIn());

			// String path = "D:/imooc.jpg";
			// String mediaId = WeixinUtil.upload(path, token.getToken(),
			// "thumb");
			// System.out.println(mediaId);

			// String result = WeixinUtil.translate("my name is laobi");
			// String result = WeixinUtil.translateFull("");
			// System.out.println(result);
			System.out.println(WeixinUtil.queryMenu(token.getToken()));
			// System.out.println("###########"+WeixinUtil.deleteMenu(token.getToken()));
			Menu m = WeixinUtil.initMenu();
//		String menus="{\\"button\\": [{\\"type\\": \\"click\\", \\"name\\": \\"今日歌曲\\", \\"key\\": \\"111\\"}]}";	
		   JsonConfig jsonConfig = new JsonConfig();
	        jsonConfig.setExcludes( new String[]{"userId"});
	        NewButton button=new NewButton();
	        SubButton sb=new SubButton();
	        sb.setName("111");
	        sb.setType("click");
	        sb.setUrl("www.baidu.com");
	        button.setName("test");
	        button.setType("click");
	        button.setKey("111");
	        button.setSub_button(sb);
	        Map map=new HashMap();
	        map.put("button", button);
	        JSON json = JSONSerializer.toJSON(map, jsonConfig);
//	 	int error = WeixinUtil.createMenu(token.getToken(), MessageUtil.objectToXml(m));
	        System.out.println(json.toString());
	        int error =WeixinUtil.createMenu(token.getToken(),json.toString());
			System.out.println(error);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
// https://mp.weixin.qq.com/debug/cgi-bin/apiinfo?t=index&type=%E6%B6%88%E6%81%AF%E6%8E%A5%E5%8F%A3%E8%B0%83%E8%AF%95&form=%E9%93%BE%E6%8E%A5%E6%B6%88%E6%81%AF

// http://weixinsuxiaoguang123.nat123.net/jeewx/wechatController.do

/*
 * URL : http://weixinsuxiaoguang123.nat123.net/jeewx/wechatController.do
 * 开发者填写URL，调试时将把消息推送到该URL上 校验通过
 * 
 * ToUserName : gh_f994f72346f0 开发者微信号 校验通过
 * 
 * FromUserName : oFDG_s_InxSygP3yPuW3f7rb6418 发送方帐号（一个OpenID） 校验通过
 * 
 * CreateTime : 201603125 消息创建时间 （整型） 校验通过
 * 
 * MsgType : link 消息类型（地理消息为 link ）
 * 
 * Title : 这是一个测试标题 消息标题 校验通过
 * 
 * Description : 这是一个测试描述 消息描述 校验通过
 * 
 * Url : www.baidu.com 消息链接 校验通过
 * 
 * MsgId : 1 消息id，64位整型
 */