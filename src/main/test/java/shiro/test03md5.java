package shiro;

import cn.itsource.domain.Employee;
import cn.itsource.domain.Menu;
import cn.itsource.query.MenuQuery;
import cn.itsource.query.PurchasebillitemQuery;
import cn.itsource.repository.IPermissionRepository;
import cn.itsource.service.*;
import cn.itsource.service.impl.PurchasebillitemServiceImpl;
import cn.itsource.shiro.JpaRealm;
import cn.itsource.shiro.MD5Utils;
import cn.itsource.util.PageUtil;
import com.google.inject.internal.util.$SourceProvider;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class test03md5 {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IPermissionRepository permissionRepository;

    @Autowired
    private IProducttypeService producttypeService;
    @Autowired
    private IPurchasebillitemService purchasebillitemService;


    @Test
    public void test() throws Exception {
        /**
         * String algorithmName, 加密方式 填写MD5或SHA
         * Object source, 原密码
         * Object salt, 盐巴
         * int hashIterations迭代次数，加密次数
         */
        ByteSource salt = ByteSource.Util.bytes("abidingly");
        /**
         * 将原密码与盐值拼接以md5方式加密，在将加密的拼接盐值在加密，10次
         */
        SimpleHash simpleHash = new SimpleHash("MD5", "123456", salt, 9);
        System.out.println(simpleHash);
    }

    /**
     * 加密后登录
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();/*创建securityManager*/
        JpaRealm realm = new JpaRealm();/*自定义的realm*/
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("md5"); //匹配器使用MD5的算法
        matcher.setHashIterations(9);//加密算法要迭代多少次
        realm.setCredentialsMatcher(matcher);/*添加加密规则*/
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
        System.out.println("当前用户是否有对应的权限employee:save   " + subject.isPermitted("employee:save"));
    }

    @Test
    public void testupdateMd5() throws Exception {
        List<Employee> all = employeeService.findAll();
        for (Employee employee : all) {
            employee.setPassword(MD5Utils.getMD5Password(employee.getUsername()));
            employeeService.save(employee);
        }

    }

    @Test
    public void test12312313() throws Exception {
        Set<String> permissionsByID = permissionRepository.findPermissionsByID(1l);
        System.out.println(permissionsByID);
    }
    @Test
    public void tests() throws Exception {
        List<Menu> menuByEmployeeId = menuService.findMenuByEmployeeId(1l);
        System.out.println(menuByEmployeeId);
    }

    @Test
    public void testd() throws Exception {
        MenuQuery menuQuery = new MenuQuery();
        menuQuery.setCurrentPage(1);
        menuQuery.setPageSize(5);
        PageUtil<Menu> pageByQueryUseUtil = menuService.findPageByQueryUseUtil(menuQuery);
        System.out.println(pageByQueryUseUtil.getList());
    }

    @Test
    public void testss() throws Exception {
     menuService.findMenuItem();
    }

    @Test
    public void testddd() throws Exception {
        System.out.println(producttypeService.findAllParentByID(25l));
    }

    @Test
    public void test222() throws Exception {
        System.out.println(purchasebillitemService.findChartsLine(new PurchasebillitemQuery()));

    }
}
