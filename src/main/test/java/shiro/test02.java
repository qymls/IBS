package shiro;

import cn.itsource.shiro.JpaRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class test02 {
    @Test
    public void test() throws Exception {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();/*创建securityManager*/
        JpaRealm realm = new JpaRealm();/*自定义的realm*/
        securityManager.setRealm(realm);/*放入securityManager*/
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文*/
        Subject subject = SecurityUtils.getSubject();/*获取当前用户*/
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {/*如果没有登录*/
            try {
                AuthenticationToken token = new UsernamePasswordToken("admin", "123");
                subject.login(token);
            } catch (UnknownAccountException e) {
                System.out.println("用户名不存在");
                e.printStackTrace();
            } catch (IncorrectCredentialsException e) {
                System.out.println("密码错误");
                e.printStackTrace();
            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("系统繁忙");
            }

        }
    }

    @Test
    public void test03() throws Exception {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();/*创建securityManager*/
        JpaRealm realm = new JpaRealm();/*自定义的realm*/
        securityManager.setRealm(realm);/*放入securityManager*/
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文*/
        Subject subject = SecurityUtils.getSubject();/*获取当前用户*/
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {/*如果没有登录*/
            try {
                AuthenticationToken token = new UsernamePasswordToken("admin", "123");
                subject.login(token);
            } catch (UnknownAccountException e) {
                System.out.println("用户名不存在");
                e.printStackTrace();
            } catch (IncorrectCredentialsException e) {
                System.out.println("密码错误");
                e.printStackTrace();
            } catch (AuthenticationException e) {
                e.printStackTrace();
                System.out.println("系统繁忙");
            }

        }
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        /*判断当前用户是否属于admin角色*/
        //System.out.println("当前用户是否是admin"+subject.hasRole("admin"));
        /*判断当前角色有什么权限*/
        System.out.println("当前用户是否有对应的权限employee:save   "+subject.isPermitted("employee:save"));
    }
}
