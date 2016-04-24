package com.ks.ssm.controller;

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
	  String name=request.getParameter("name");
	  if( name!=null)
	  {
	    //int userId = Integer.parseInt(id);
	    //User user = this.userService.getUserById(userId);
	    User user2 = this.userService.selectByUserName(name);
	   // model.addAttribute("user", user);
	    model.addAttribute("user", user2);
	    return "showUser";
	  }
	  else
	  {
		  return "error";
	  }
  }
}
