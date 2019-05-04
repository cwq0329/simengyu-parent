package com.cwq.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cwq.core.pojo.Buyer;
import com.cwq.core.service.BuyerService;
import com.cwq.core.service.SessionService;
import com.cwq.core.tools.Encoding;
import com.cwq.core.tools.Encryption;
import com.cwq.core.tools.SessionTools;

/**
 * 登录控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class LoginController {

	@Autowired
	private BuyerService buyerService;

	@Autowired
	private SessionService sessionService;

	// 显示登录页面
	@RequestMapping(value = "login.aspx", method = RequestMethod.GET)
	public String showLogin() {
		return "login";
	}

	// 执行登录
	@RequestMapping(value = "login.aspx", method = RequestMethod.POST)
	public String doLogin(Model model, String returnUrl, String username, String password,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("url" + returnUrl + ",用户名==" + username + ",密码==" + password);
		// 用户名不为空时
		if (username != null) {
			// 密码不为空时
			if (password != null) {
				// 开始查数据库检测用户名和密码是否正确
				Buyer buery = buyerService.findByUsername(username);
				System.out.println(buery);
				// 当用户名存在时
				if (buery != null) {
					// 开始判断密码
					// 因为数据库中的密码已经是不可逆加密过后的密文,所以比较密码时先对用户传入的密码加密
					if (buery.getPassword().equals(Encryption.encrypt(password))) {
						// 当用户名和密码正确时,进行session操作(用户名保存到自定义session中(redis))
						sessionService.addSessionToRedis(
								SessionTools.getSessionId(request, response),
								buery.getUsername());
						// 若用户直接打开登录页面,则登录后返回首页
						if (returnUrl == null || returnUrl.trim().length() < 1) {
							returnUrl = "http://localhost:8082/";
						}
						return "redirect:" + Encoding.encodeGetRequest(returnUrl);
					} else {
						model.addAttribute("error", "密码不存在!");
					}
				} else {
					model.addAttribute("error", "用户名不存在!");
				}
			} else {
				model.addAttribute("error", "密码不能为空!");
			}
		} else {
			model.addAttribute("error", "用户名不能为空!");
		}

		return "login";
	}

	// 判断用户是否登录
	@RequestMapping(value = "isLogin.aspx")
	@ResponseBody
	public MappingJacksonValue isLogin(String callback, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("用户登录检测!");
		String sessionID = SessionTools.getSessionId(request, response);
		String username = sessionService.getUsernameFromRedis(sessionID);
		// 将要传回的页面数据,封装到callback的值为命名的js方法中
		// jQuery1567("username");
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(username);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;

	}
}
