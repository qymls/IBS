package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class test01 {
    /**
     * 登录，注销测试
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        /*读取ini文件，获得工厂对象*/
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();/*创建shiro的核心对象*/
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文，在任意地方使用，相当于session*/
        Subject subject = SecurityUtils.getSubject();/*获取当前用户*/
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {/*如果没有登录*/
            AuthenticationToken token = new UsernamePasswordToken("root", "123");
            subject.login(token);
        }
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        if (subject.isAuthenticated()) {/*登录了*/
            subject.logout();
        }
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
    }

    /**
     * 登录出错
     * UnknownAccountException 用户名不存在
     * IncorrectCredentialsException 密码错误
     * AuthenticationException 其他未知错误
     *
     * @throws Exception
     */
    @Test
    public void test02() throws Exception {
        /*读取ini文件，获得工厂对象*/
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();/*创建shiro的核心对象*/
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文，在任意地方使用，相当于session*/
        Subject subject = SecurityUtils.getSubject();/*获取当前用户*/
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {/*如果没有登录*/
            try {
                AuthenticationToken token = new UsernamePasswordToken("root", "1235");
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

    /**
     * 角色和权限
     * 在未登录状态下，全是false
     * @throws Exception
     */
    @Test
    public void test03() throws Exception {
        /*读取ini文件，获得工厂对象*/
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();/*创建shiro的核心对象*/
        SecurityUtils.setSecurityManager(securityManager);/*设置上下文，在任意地方使用，相当于session*/
        Subject subject = SecurityUtils.getSubject();/*获取当前用户*/
        System.out.println("当前用户是否登录" + subject.isAuthenticated());
        if (!subject.isAuthenticated()) {/*如果没有登录*/
            try {
                AuthenticationToken token = new UsernamePasswordToken("root", "123");
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
        System.out.println("当前用户是否是admin"+subject.hasRole("admin"));
        /*判断当前角色有什么权限*/
        System.out.println("当前用户是否有对应的权限employee:save   "+subject.isPermitted("employee:save"));
    }

}
