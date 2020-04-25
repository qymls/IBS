package cn.itsource.web.controller;

import cn.itsource.service.login.IAuthenticationService;
import cn.itsource.util.Constant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class LoginController {
    private IAuthenticationService gitHubAuthenticationService;/*github登录*/
    private IAuthenticationService qqAuthenticationService;/*qq登录*/
    private DefaultSecurityManager securityManager;/*shiro的核心对象*/

    @Autowired
    public void setSecurityManager(DefaultSecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Autowired
    public void setGitHubAuthenticationService(IAuthenticationService gitHubAuthenticationService) {
        this.gitHubAuthenticationService = gitHubAuthenticationService;
    }

    @Autowired
    public void setQqAuthenticationService(IAuthenticationService qqAuthenticationService) {
        this.qqAuthenticationService = qqAuthenticationService;
    }

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "WEB-INF/admin/login";
    }

    /**
     * 登录方法
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(String username, String password) {
        HashMap<String, String> map = new HashMap<>();
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文*/
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {/*是否登录了*/
            try {
                AuthenticationToken token = new UsernamePasswordToken(username, password);
                subject.login(token);/*登录*/
                map.put("success", "success");/*只有登录成功了才会执行*/
            } catch (UnknownAccountException e) {
                map.put("errorMessage", "用户名不存在,错误码" + "("+System.currentTimeMillis()+")");
            } catch (IncorrectCredentialsException e) {
                map.put("errorMessage", "密码错误,错误码" + "("+System.currentTimeMillis()+")");
            } catch (AuthenticationException e) {
                map.put("errorMessage", "系统繁忙,错误码" + "("+System.currentTimeMillis()+")");
            }
        } else {
            map.put("success", "以登录");
        }
        return map;
    }

    /**
     * 注销方法
     */
    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文*/
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "WEB-INF/admin/login";/*跳回到登录页面*/
    }

    /**
     * qq登录
     */
    @RequestMapping("/qqLoginSendPost")
    @ResponseBody
    public void qqLoginSendPost() {

    }

    @RequestMapping("/qqLogin")
    @ResponseBody
    public void qqLogin(String code) {
        System.out.println(code);
    }

    @RequestMapping("/gitHubLogin")
    public String gitHubLogin(String code, HttpSession session) {
        System.out.println(code);
        gitHubAuthenticationService.getUserInfo(code, session);
        return "redirect:/Admin/admin";
    }

}
