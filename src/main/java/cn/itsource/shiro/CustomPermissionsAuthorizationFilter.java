package cn.itsource.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义权限过滤器
 */
public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        //登录成功后没有权限的操作
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        //判断是什么请求
        String xRequestedWith = req.getHeader("X-Requested-With");
        if (xRequestedWith != null && "XMLHttpRequest".equals(xRequestedWith)) {/*ajax请求*/
            //表示ajax请求 {"success":false,"message":"没有权限"}
            rep.setContentType("text/json; charset=UTF-8");
            rep.getWriter().print("{\"success\":false,\"msg\":\"抱歉，您无相应的权限,请联系管理员\"}");
            rep.getWriter().flush();
            rep.getWriter().close();
        } else {
            return super.onAccessDenied(request, response);/*如果是同步请求就，交给父类去处理*/
        }
        return false;
    }

}
