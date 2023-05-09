package com.nguyenquockhanh.restaurant.controller;

import java.io.IOException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nguyenquockhanh.restaurant.entity.User;
import com.nguyenquockhanh.restaurant.service.UserService;

@Controller
public class LoginController extends BaseController {

	@Autowired
    private UserService userService;
	
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    
    @PostMapping("/login")
    public String processLoginForm(
    		@RequestParam("username")String username, @RequestParam("password") String password,
            HttpSession session, 
            HttpServletRequest request, RedirectAttributes redirectAttributes,ModelMap model) {
    	

            String accessToken = userService.findByUserNameAndPss(username,password ).getAcessToken();
            if(accessToken.isEmpty()) {
            	model.addAttribute("err", "Sai tài khoản hoặc mật khẩu!");
            	 return "login";
            }
            session.setAttribute("accesstoken", accessToken);

            // Redirect to requested page
            String requestedPage = (String) session.getAttribute("requestedpage");
            
        return "/index";
    }
    
    @GetMapping("/checkSession")
    @ResponseBody
    public String checkSession( HttpServletRequest request) {
      // Kiểm tra session của người dùng
      String accesstoken = (String) request.getSession().getAttribute("accesstoken");
      if (accesstoken != null) {
        return "Người dùng đã đăng nhập với accesstoken: " + accesstoken;
      } else {
        return "Người dùng chưa đăng nhập";
      }
    }
  
    @GetMapping("/header")
    public String header(Model model) {
        model.addAttribute("user", new User());
        return "header";
    }

}
