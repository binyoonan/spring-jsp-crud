package com.kopo.project1;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	private final DB db = new DB();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		return "join";
	}

//	@RequestMapping(value = "/join_action", method = RequestMethod.POST)
//	public String joinAction(Locale locale, Model model
//			, @RequestParam("name") String name
//			, @RequestParam("id") String id
//			, @RequestParam("pwd") String pwd
//			, @RequestParam("phone") String phone
//			, @RequestParam("address") String address
//			) {
//		DB db = new DB();
//		db.insertData(new User(id, pwd, name, phone, address));
//		return "redirect:/";
//	}

	@RequestMapping(value = "/join_action", method = RequestMethod.POST)
	public String joinAction(Locale locale, Model model
			, HttpServletRequest request
			) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		
		db.insertData(new User(id, pwd, name, phone, address));
		return "redirect:/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/login_action", method = RequestMethod.POST)
	public String loginAction(Locale locale, Model model
			, HttpServletRequest request, HttpSession session
			) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		User loggedUser = db.login(new User(id, pwd));
		if (loggedUser.userType.isEmpty()) {
			session.setAttribute("is_login", false);
			session.setAttribute("user_type", "");
		} else {
			session.setAttribute("is_login", true);
			session.setAttribute("user_id", id);
			session.setAttribute("user_type", loggedUser.userType);
		}
		return "redirect:/";
	}

	@RequestMapping(value = "/user_list", method = RequestMethod.GET)
	public String userList(Locale locale, Model model, HttpSession session) {
		boolean isLogin = false;
		try {
			isLogin = (Boolean)session.getAttribute("is_login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isLogin) {
			ArrayList<User> userList = db.selectAll();
			int totalUserCnt = db.getTotalUserCount();
			int signUpToday = db.getSignUpTodayUserCount();
			model.addAttribute("totalUserCnt", totalUserCnt);
			model.addAttribute("signUpToday", signUpToday);
			model.addAttribute("userList", userList);
			return "user_list";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Locale locale, Model model, HttpSession session) {
		boolean isLogin = false;
		try {
			isLogin = (Boolean)session.getAttribute("is_login");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(isLogin) {
			String id = (String)session.getAttribute("user_id");
			User user = db.findById(id);
			model.addAttribute("user", user);
			return "mypage";
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/user_update", method = RequestMethod.GET)
	public String updateForm(Locale locale, Model model, HttpSession session) {
		boolean isLogin = false;
		try {
			isLogin = (Boolean) session.getAttribute("is_login");
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(isLogin) {
			String id = (String)session.getAttribute("user_id");
			User user = db.findById(id);
			model.addAttribute("user",user);
			return "updateForm";
		}
		return "index";
	}
	
	@RequestMapping(value = "/update_action", method = RequestMethod.POST)
	public String updateAction(@RequestBody User user, Locale locale, HttpSession session) {
		boolean isLogin = false;
		try {
			isLogin = (Boolean) session.getAttribute("is_login");
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(isLogin) {
			db.updateUser(user);
			User updatedUser = db.findById(user.getId());
			session.setAttribute("user_type", updatedUser.getUserType());
			return "updateForm";
		}
		return "index";
	}
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(Locale locale, HttpSession session) {
		session.setAttribute("is_login", false);
		session.setAttribute("user_id", "");
		session.setAttribute("user_type", "");
		return "index";
	}
} 
