package com.qiku.usermgr.api.controller;

import com.google.code.kaptcha.Producer;
import com.qiku.usermgr.api.utils.SHA1Util;
import com.qiku.usermgr.api.service.UserService;
import com.qiku.usermgr.api.vo.LoginBean;
import com.qiku.usermgr.common.utils.IOUtils;
import com.qiku.usermgr.core.http.BaseResponse;
import com.qiku.usermgr.store.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.code.kaptcha.Constants;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {

    @Autowired
    private Producer producer;

    @Autowired
    UserService userService;

    @ExceptionHandler({UnauthorizedException.class})
    public BaseResponse processUnauthenticatedException(NativeWebRequest request,
                                                        UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("refuse");
        return BaseResponse.Ok().putData("没有权限");
    }

    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);
        // 保存到验证码到 session
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
    /**
     * 登录接口
     */
    @PostMapping(value = "/login")
    public BaseResponse login(@RequestBody LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getAccount();
        String pwd = loginBean.getPassword();
        String captcha = loginBean.getCaptcha();
        // 从session中获取之前保存的验证码跟前台传来的验证码进行匹配
//        Object kaptcha = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//        if(kaptcha == null){
//            return BaseResponse.Fail("验证码已失效");
//        }
//        if(!captcha.equals(kaptcha)){
//            return BaseResponse.Fail("验证码不正确");
//        }

        // 用户信息
        User user = userService.findByName(username);
        // 账号不存在、密码错误
        if (user == null) {
            return BaseResponse.Fail("账号不存在");
        }
        String salt = "1y3u4r";
        String cpwd = SHA1Util.shaEncrypt(pwd+salt);
        if (!cpwd.equals(user.getPwd())) {
            return BaseResponse.Fail("密码不正确");
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd, false);
        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
            return BaseResponse.Ok();
        } catch (Exception e) {
            //log.error("登录失败，用户名[{}]", username, e);
            token.clear();
            return BaseResponse.Fail(e.getMessage());
        }
    }
    /**
     * 登录接口
     */
    @GetMapping(value = "/logout")
    public BaseResponse logout(HttpServletRequest request)  {

        return  BaseResponse.Ok();
    }
    /**
     * 刷新token
     */
    @RequiresPermissions("sys:user:view")
    @PostMapping(value = "/test")
    public BaseResponse test(@RequestBody LoginBean loginBean, HttpServletRequest request)  {

        return  BaseResponse.Ok().putData("HSJFAHFJSHFJ");
    }
    /**
     * 未登录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unauth")
    @ResponseBody
    public BaseResponse unauth(HttpServletRequest request) throws Exception {
        return  BaseResponse.Ok().putData("请重新登录");
    }
}
