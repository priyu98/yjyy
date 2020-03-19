package com.example.yjyy.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.example.yjyy.entity.User;
import com.example.yjyy.entity.dto.LoginUserDto;
import com.example.yjyy.interceptor.AppToken;
import com.example.yjyy.result.PageResult;
import com.example.yjyy.result.WebRestResult;
import com.example.yjyy.result.business.PageResult.UserPageResult;
import com.example.yjyy.result.business.UserResult;
import com.example.yjyy.result.business.verificationResult;
import com.example.yjyy.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("UserController")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("getUsername")
    @AppToken
    public WebRestResult getUsername(String userid){
        return userService.getUsername(userid);
    }

    @RequestMapping("loginAdmin")
    @CrossOrigin
    public WebRestResult loginAdmin(String username,String password){
        WebRestResult result = userService.loginAdmin(username,password);
        return result;
    }

    @RequestMapping("getVerification")
    public WebRestResult getVerification(HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] verByte = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        String sessionid = null;
        verificationResult result = new verificationResult();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("verify_session_Code", createText);
            sessionid = request.getSession().getId();
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            result.setVerification(createText);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            result.setResult(WebRestResult.FAILURE);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        String base64 = encoder.encode(jpegOutputStream.toByteArray());
        String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
        result.setImage(captchaBase64);
        result.setSessionid(sessionid);
        result.setResult(WebRestResult.SUCCESS);
        return result;


		/*//定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
		verByte = jpegOutputStream.toByteArray();
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(verByte);
		responseOutputStream.flush();
		responseOutputStream.close();*/
    }

    @RequestMapping("authVerification")
    public WebRestResult authVerification(HttpServletRequest request) {
        String verification = request.getParameter("verification");
        //System.out.println(request.getSession().getId());
        String session_verification = (String)request.getSession().getAttribute("verify_session_Code");
        WebRestResult result = new WebRestResult();
        if("".equals(verification)){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("输入验证码为空");
            return result;
        }
        else if("".equals(session_verification)){
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("获取验证码为空");
            return result;
        }
        else if(verification.equals(session_verification)){
            result.setResult(WebRestResult.SUCCESS);
            result.setMessage("验证码正确");
            return result;
        }
        else{
            result.setResult(WebRestResult.FAILURE);
            result.setMessage("验证码错误");
            return result;
        }
    }

    @AppToken
    @CrossOrigin
    @PostMapping("updateUser")
    public WebRestResult updateUser(@RequestBody User user){
        WebRestResult result = userService.updateUser(user);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("deleteUser")
    public WebRestResult deleteUser(String userid){
        WebRestResult result = userService.deleteUser(userid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @PostMapping("dismissionUser")
    public WebRestResult dismissonUser(String userid){
        WebRestResult result = userService.dismissionUser(userid);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getAllUserList")
    public PageResult<UserPageResult> getAllUserList(String username,String venueid,String roleid,int page,int pagesize){
        PageResult<UserPageResult> result = userService.getAllUserList(username,venueid,roleid,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getEmployeeList")
    public PageResult<UserPageResult> getEmployeeList(String username,String venueid,String employeestatus,int page,int pagesize){
        PageResult<UserPageResult> result = userService.getEmployeeList(username,venueid,employeestatus,page,pagesize);
        return result;
    }

    @AppToken
    @CrossOrigin
    @GetMapping("getUserItem")
    public WebRestResult getUserItem(String userid){
        WebRestResult result = userService.getUserItem(userid);
        return result;
    }

    @CrossOrigin
    @PostMapping("loginUser")
    public UserResult loginUser(@RequestBody LoginUserDto loginUserDto){
        UserResult result = userService.loginUser(loginUserDto);
        return result;
    }
}
