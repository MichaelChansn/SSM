package com.ks.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ks.ssm.domain.User;
import com.ks.ssm.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
  @Resource
  private IUserService userService;
  
  private String[] imgs={"jpg","jpeg","bmp","gif","png"};
  private List<String> imgsList=Arrays.asList(imgs);
  private SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
  
  private final String userID="1";
  private final String headImg="headerImg";
  private final String articleImg="articleImg";
  
  
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
  @RequestMapping(value="/addArticle",method =RequestMethod.POST)
  public String addArticle(HttpServletRequest request,Model model){
	 String retWeb="error";
	 File file=null;
	 MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
	 MultipartFile imgFile  =  multipartRequest.getFile("articleImg");
	 if(!(imgFile.getOriginalFilename() ==null || "".equals(imgFile.getOriginalFilename()))) { 
		 file = this.getFile(imgFile,userID,articleImg);  
	 } 
	 String textContent=request.getParameter("articleContent");
	 if((null!=textContent && !"".equals(textContent.trim())) || file!=null)
	 {
		 if(file!=null)
		 model.addAttribute("img", request.getSession().getServletContext().getContextPath()+"/user"+"/"+"img/"+userID+"/"+articleImg+"/"+file.getName());
		 model.addAttribute("text", textContent);
		 retWeb="showUser";
	 }
	 return retWeb; 
  }
  
  private File getFile(MultipartFile imgFile,String userID,String articleImg) {  
      String fileName = imgFile.getOriginalFilename();  
      //获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名  
       String ext = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());  
       //对扩展名进行小写转换  
       ext = ext.toLowerCase();  
       File file = null;  
       if(imgsList.contains(ext)) {                      //如果扩展名属于允许上传的类型，则创建文件 
    	   fileName=df.format(new Date())+"."+ext;
           file = this.creatFolder(userID, articleImg, fileName);  
           try {  
              imgFile.transferTo(file);                   //保存上传的文件  
          } catch (IllegalStateException e) {  
              e.printStackTrace();  
          } catch (IOException e) {  
              e.printStackTrace();  
          }  
       }  
       return file;  
  }  
  
  private File creatFolder(String userID,String articleImg,String fileName) {  
      File file = null;  
       
      File firstFolder = new File("D:/" + userID);         //一级文件夹  
      if(firstFolder.exists()) {                             //如果一级文件夹存在，则检测二级文件夹  
          File secondFolder = new File(firstFolder,articleImg);  
          if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
              file = new File(secondFolder,fileName);  
          }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
              secondFolder.mkdir();  
              file = new File(secondFolder,fileName);        //创建完二级文件夹后，再合建文件  
          }  
      }else {                                                //如果一级不存在，则创建一级文件夹  
          firstFolder.mkdir();  
          File secondFolder = new File(firstFolder,articleImg);  
          if(secondFolder.exists()) {                        //如果二级文件夹也存在，则创建文件  
              file = new File(secondFolder,fileName);  
          }else {                                            //如果二级文件夹不存在，则创建二级文件夹  
              secondFolder.mkdir();  
              file = new File(secondFolder,fileName);  
          }  
      }  
      return file;  
 }
  
  
  
}
