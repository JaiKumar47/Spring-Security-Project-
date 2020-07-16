package com.coursecube.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpServerErrorException;

@Controller
@SessionAttributes("MyRoles")
public class UserController {
    
	@Autowired
	UserService userService;
	
	
	public String showIndexPage(Model model) {
		System.out.println("**showIndexPage**");
		
	}
	
	
	
	
	
	@GetMapping("/showRegister")
	public String showRegisterPage(Model model) {
		System.out.println("**showRegisterPage**");
		User myuser=new User();
		model.addAttribute("myuser",myuser);
		return "register";
	}
	
	
	
	
	
	@PostMapping("/registerUser")
	public String registerUser(@ModelAttribute("myuser")User user,BindingResult bindingResult,Model model) {
		System.out.println("**registerUser");
		userService.registerUser(user);
		
		return "index";
	}
	
	
	
	
	@GetMapping("/login")
	public String loginPage(
			@RequestParam(value="error",required=false)String error,
			 @RequestParam(value="logout",required=false)String logout,Model model) {
		      System.out.println("**LoginPage**");
		      
		      List<String>myroles=getUserRoles();
		      System.out.println(myroles);
		      model.addAttribute("MyRoles",myroles);
               if(error!=null) {
            	   model.addAttribute("error","Invalid username and password!");
               }
               if(logout!=null) {
            	   model.addAttribute("msg","You have been logged out successfully.");
               }
               getUserRoles();
               return "login";
               }
		      
		      
	@GetMapping("/logout")
	
	public String logoutPage(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("**LogoutPage**");
		List<String>myroles=getUserRoles();
		System.out.println(myroles);
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
		
		}
		return "redirect:/login?logout";
		
		
		}
	
	
	
	
	private List<String>getUserRoles(){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		List<String>myroles=new ArrayList<String>();
		if(auth!=null) {
			Collection<GrantedAuthority>col=(Collection<GrantedAuthority>)auth.getAuthorities();
			for(GrantedAuthority gauth:col)
				myroles.add(gauth.getAuthority());
		}
		return myroles;
		}
		
	}
	


