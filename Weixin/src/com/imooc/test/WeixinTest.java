package com.imooc.test;

import com.imooc.menu.Menu;
import com.imooc.po.AccessToken;
import com.imooc.util.MessageUtil;
import com.imooc.util.WeixinUtil;

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
//			System.out.println("###########"+WeixinUtil.deleteMenu(token.getToken()));
			Menu m = WeixinUtil.initMenu();
			int error = WeixinUtil.createMenu(token.getToken(), MessageUtil.objectToXml(m));
			System.out.println(error);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
