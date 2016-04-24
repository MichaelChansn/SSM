package com.ks.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
  @Resource
  private IUserService userService;
  
  @RequestMapping("/showUser")
  public String toIndex(HttpServletRequest request,Model model){
	  System.err.println("reauest is in");
	  //String id=request.getParameter("id");
	  String email=request.getParameter("email");
	  if( email!=null)
	  {
	    //int userId = Integer.parseInt(id);
	    //User user = this.userService.getUserById(userId);
	    List<User> users = this.userService.selectByEmail(email);
	    //User user=this.userService.selectByUserName("ks");
	   // model.addAttribute("user", user);
	    model.addAttribute("user", users);
	    return "showUser";
	  }
	  else
	  {
		  return "error";
	  }
  }
}
