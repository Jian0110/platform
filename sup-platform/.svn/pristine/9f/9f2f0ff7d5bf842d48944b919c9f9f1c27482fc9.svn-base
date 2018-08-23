package cn.com.ssj.controller;

import cn.com.ssj.dto.LoginRequest;
import cn.com.ssj.dto.ResultObject;
import cn.com.ssj.dto.UserRequest;
import cn.com.ssj.mybatis.model.User;
import cn.com.ssj.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class IndexController {

    private static final Logger logger = LogManager.getLogger(IndexController.class);

    @Autowired
    MessageSource messageSource;


    @Autowired
    UserService userService;


    @RequestMapping(value = {"/", "/index"})
    public String index(HttpServletRequest httpServletRequest, Model model) {
        Object object = SecurityUtils.getSubject().getPrincipal();
        if (object instanceof User) {
            User user = (User) object;
            model.addAttribute("photo", user.getImage());
        }
        return "index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        logger.info("indexcontroller login");
        //rememberme 记住我登录
        boolean isAuthenticated = SecurityUtils.getSubject().isAuthenticated();
        boolean isRemembered = SecurityUtils.getSubject().isRemembered();
        if (isAuthenticated || isRemembered) {
            return "redirect:/index";
        }
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject login(@Valid LoginRequest loginRequest) {
        try {

            String password = loginRequest.getPassword();
            Md5Hash md5Hash = new Md5Hash(password);
            password = md5Hash.toString();
            SecurityUtils.getSubject().login(new UsernamePasswordToken(loginRequest.getUsername(), password, loginRequest.isRememberMe()));
            Object object = SecurityUtils.getSubject().getPrincipal();
            if (object instanceof User) {
                User user = (User) object;
                UserRequest userRequest = new UserRequest();
                userRequest.setUserId(user.getUserId());
                this.userService.updateLastLoginTs(userRequest);
                logger.info("{}:{}", loginRequest.getUsername(), loginRequest.getPassword());
            }
        } catch (UnknownAccountException uae) {
            return ResultObject.NACK("用户不存在");
        } catch (IncorrectCredentialsException ice) {
            return ResultObject.NACK("密码错误");
        } catch (AuthenticationException ae) {
            return ResultObject.NACK("登录错误");
        }
        return ResultObject.ACK();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResultObject register(@RequestBody User user) {

        //shiro中提供的加密工具
		/*
		int hashIterations = 8;//加密的次数
        Object salt = null;//盐值
        Object credentials = "world";//密码
        String hashAlgorithmName = "MD5";//加密方式
        SimpleHash simpleHash = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println("加密后的值----->" + simpleHash.toString());
        */
        return ResultObject.NACK();
    }


}
