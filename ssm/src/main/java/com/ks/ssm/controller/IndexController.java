package com.ks.ssm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author KS
 *
 */
@Controller
public class IndexController {
	
	  @RequestMapping({"/","/index"})
	  public String toIndex(HttpServletRequest request,Model model){
		  System.err.println("reauest is in");
		 return "index";
	  }

}
