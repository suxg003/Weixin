package com.imooc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.imooc.util.CheckUtil;
import com.imooc.util.MessageUtil;
import com.imooc.util.WeixinUtil;

public class WeixinServlet extends HttpServlet {
	/**
	 * 接入验证
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");

		PrintWriter out = resp.getWriter();
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
	}

	/**
	 * 消息的接收与响应
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try {
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("11".equals(content)){
//					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
//					message = MessageUtil.initText(toUserName, fromUserName, "这是测试title",MessageUtil.MESSAGE_LINK,"这是测试描述","www.qq.com",5839907284805129867L);
//					回复图文消息
					StringBuffer sb1=new StringBuffer();
					sb1.append("<xml>");
					sb1.append("<ToUserName><![CDATA[oFDG_s_InxSygP3yPuW3f7rb6418]]></ToUserName>");
					sb1.append("<FromUserName><![CDATA[gh_f994f72346f0]]></FromUserName>");
					sb1.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
					sb1.append("<MsgType><![CDATA[news]]></MsgType>");
					sb1.append("<ArticleCount>1</ArticleCount>");
					sb1.append("<Articles>");
					sb1.append("<item>");
					sb1.append("<Title><![CDATA[title1]]></Title> ");
					sb1.append("<Description><![CDATA[description1]]></Description>");
					sb1.append("<PicUrl><![CDATA[http://img1.gtimg.com/15/1552/155209/15520945_1200x1000_0.jpg]]></PicUrl>");
					sb1.append("<Url><![CDATA[http://news.qq.com/a/20160322/036262.htm#p=1]]></Url>");
					sb1.append("</item>");
					sb1.append("</Articles>");
					sb1.append("</xml>");
					message=sb1.toString();
				}
				else if("1".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
				}else if("2".equals(content)){
					message = MessageUtil.initNewsMessage(toUserName, fromUserName);
				}else if("3".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
				}else if("?".equals(content) || "？".equals(content)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(content.startsWith("翻译")){
					String word = content.replaceAll("^翻译", "").trim();
					if("".equals(word)){
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.threeMenu());
					}else{
						message = MessageUtil.initText(toUserName, fromUserName, WeixinUtil.translate(word));
					}
				}
			}else if(MessageUtil.MESSAGE_EVNET.equals(msgType)){
				String eventType = map.get("Event");
				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_CLICK.equals(eventType)){
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
					String url = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, url);
				}else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
					String key = map.get("EventKey");
					message = MessageUtil.initText(toUserName, fromUserName, key);
				}
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}else if(MessageUtil.MESSAGE_LINK.equals(msgType)){
				/* 
				String title= map.get("Title");
				String description =map.get("Description");
				String url=map.get("Url");
				String MsgId=map.get("MsgId");
				System.out.println("参数title="+title);
				System.out.println("参数description="+description);
				System.out.println("参数url="+url);
				System.out.println("参数MsgId="+MsgId);
				message = MessageUtil.initText(toUserName, fromUserName, title,msgType,description,url,Long.valueOf(MsgId));
				System.out.println(message);*/
				
			/*	StringBuffer sb=new StringBuffer();
			sb.append("<xml><ToUserName><![CDATA[oFDG_s_InxSygP3yPuW3f7rb6418]]></ToUserName>");
			sb.append("<FromUserName><![CDATA[gh_f994f72346f0]]></FromUserName>");
			sb.append("<CreateTime>"+new Date().getTime()+"</CreateTime>");
			sb.append("<MsgType><![CDATA[link]]></MsgType>");
			sb.append("<Title><![CDATA[公众平台官网链接]]></Title>");
			sb.append("<Description><![CDATA[公众平台官网链接]]></Description>");
			sb.append("<Url><![CDATA[http://www.qq.com]]></Url>");
			sb.append("	<MsgId>1234567890123456</MsgId>");
			sb.append("	</xml> ");
			message=sb.toString();*/
			

			}
			
			
			System.out.println(message);
			
			out.print(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
