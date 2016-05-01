package com.ks.ssm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.ks.ssm.constant.CommonConstants;
import com.ks.ssm.domain.User;

public class SSMUtils {

	private static String[] imgs = { "jpg", "jpeg", "bmp", "gif", "png" };
	private static List<String> imgsList = Arrays.asList(imgs);
	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!CommonUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!CommonUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	/**
     * 把信息存放近session
     * 权限
     * ID
     * Email
     * 姓名
     * 公司
     * 手机
     * @param session
     * @param userInfo
     */
    public static  void storeSession(HttpSession session, User userInfo) {
    	session.setAttribute(CommonConstants.SESSION_ISLOGIN, CommonConstants.SESSION_LOGIN);
        session.setAttribute(CommonConstants.SESSION_AUTH,CommonConstants.USER_AUTH_USER );
        session.setAttribute(CommonConstants.SESSION_USERID, userInfo.getId());
        session.setAttribute(CommonConstants.SESSION_USEREMAIL, userInfo.getEmail());
        session.setAttribute(CommonConstants.SESSION_USER_NAME, userInfo.getUsername());
    }

    
    public static boolean isLogin(HttpSession session)
    {
        Object userId = session.getAttribute(CommonConstants.SESSION_USERID);
        Object auth = session.getAttribute(CommonConstants.SESSION_AUTH);
        Object isLogin=session.getAttribute(CommonConstants.SESSION_ISLOGIN);
        if (userId != null &&  auth != null && isLogin!=null && (boolean)isLogin) 
            return true;
        
        return false;

    }
    
    public static int  getAuth(HttpSession session)
    {
    	Object auth=session.getAttribute(CommonConstants.SESSION_AUTH);
    	return auth==null? CommonConstants.USER_AUTH_ERROR:(int)auth;
    }
    
    /**
	   * 退出逻辑
	   * 
	   * @param session
	   */
	  public static  void logOut(HttpSession session,HttpServletRequest request)
	  {   
	      //  清除运原来的session
	      session.invalidate();
	      //返回新的session
	      session=request.getSession(true);
	  }
	  
	  public static File getFile(MultipartFile imgFile, String userID, String articleImg) {
			String fileName = imgFile.getOriginalFilename();
			// 获取上传文件类型的扩展名,先得到.的位置，再截取从.的下一个位置到文件的最后，最后得到扩展名
			String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			// 对扩展名进行小写转换
			ext = ext.toLowerCase();
			File file = null;
			if (imgsList.contains(ext)) { // 如果扩展名属于允许上传的类型，则创建文件
				fileName = df.format(new Date()) + "." + ext;
				file = creatFolder(userID, articleImg, fileName);
				try {
					imgFile.transferTo(file); // 保存上传的文件
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return file;
		}

		public static File creatFolder(String userID, String articleImg, String fileName) {
			File file = null;

			File firstFolder = new File("C:/" + userID); // 一级文件夹
			if (firstFolder.exists()) { // 如果一级文件夹存在，则检测二级文件夹
				File secondFolder = new File(firstFolder, articleImg);
				if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件
					file = new File(secondFolder, fileName);
				} else { // 如果二级文件夹不存在，则创建二级文件夹
					secondFolder.mkdir();
					file = new File(secondFolder, fileName); // 创建完二级文件夹后，再合建文件
				}
			} else { // 如果一级不存在，则创建一级文件夹
				firstFolder.mkdir();
				File secondFolder = new File(firstFolder, articleImg);
				if (secondFolder.exists()) { // 如果二级文件夹也存在，则创建文件
					file = new File(secondFolder, fileName);
				} else { // 如果二级文件夹不存在，则创建二级文件夹
					secondFolder.mkdir();
					file = new File(secondFolder, fileName);
				}
			}
			return file;
		}

}
