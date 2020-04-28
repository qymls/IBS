package cn.itsource.shiro;

import cn.itsource.domain.Employee;
import cn.itsource.service.IEmployeeService;
import cn.itsource.service.IPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

/**
 * 继承AuthorizingRealm类，就是间接实现了Realm，就可以做登录 权限认证了
 */
public class JpaRealm extends AuthorizingRealm {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;

    /**
     * 身份验证（当条用subject.login就执行），并且获取到UsernamePasswordToken对象
     * 该方法返回AuthenticationInfo对象，当其为null时，用户名不存在，抛出UnknownAccountException
     * 不为null，对比密码，正确就通过
     * 错误就抛出IncorrectCredentialsException
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;/*强转*/
        String username = token.getUsername();
        Employee employee = employeeService.findByUsername(username);/*查询数据库*/
        if (employee != null) {
            /**
             * (Object principal, 主题对象，一旦登陆成功shiro会自动保存在session中
             * Object credentials, 密码，令牌，查询到的
             * ByteSource credentialsSalt,加密的盐值
             * String realmName Realm名称
             */
            ByteSource salt = ByteSource.Util.bytes("abidingly");
            return new SimpleAuthenticationInfo(employee, employee.getPassword(), salt, "JpaRealm");/*返回其实现类*/
        }
        return null;
    }


    /**
     * 权限认证
     * 登录成功后会执行此方法，对用户授权
     * 用户访问需要授权的资源时，回去查找是否有对应的权限，有就放行，没有就拦截
     * 我们只需要给用户授权即可
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*获取当前登录对象*/
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        /*Employee employee = (Employee)principalCollection.getPrimaryPrincipal();*/
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = permissionService.findPermissionsByID(employee.getId());
        if (permissions.size() == 1) {
            for (String permission : permissions) {
                if (permission == null) {
                    permissions.clear();/*当用户没有权限是，会返回一个null，那么就清空*/
                }
            }
        }
        System.out.println("根据对应的登录用户查询拥有的权限了");
        authenticationInfo.setStringPermissions(permissions);/*授权*/
        return authenticationInfo;
    }
}
