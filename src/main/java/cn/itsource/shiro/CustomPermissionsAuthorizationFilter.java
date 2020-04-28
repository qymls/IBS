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
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() == null) {
            //没有登录成功后的操作
            this.saveRequestAndRedirectToLogin(request, response);
        } else {
            //登录成功后没有权限的操作
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            //判断是什么请求
            String xRequestedWith = httpRequest.getHeader("X-Requested-With");
            if (xRequestedWith != null && "XMLHttpRequest".equals(xRequestedWith)) {/*ajax请求*/
                //表示ajax请求 {"success":false,"message":"没有权限"}
                httpResponse.setContentType("text/json; charset=UTF-8");
                httpResponse.getWriter().print("{\"success\":false,\"msg\":\"抱歉，您无相应的权限,请联系管理员\"}");
                httpResponse.getWriter().flush();
                httpResponse.getWriter().close();
            } else {
                String unauthorizedUrl = this.getUnauthorizedUrl();
                if (StringUtils.hasText(unauthorizedUrl)) {
                    WebUtils.issueRedirect(request, response, unauthorizedUrl);
                } else {
                    WebUtils.toHttp(response).sendError(401);
                }
            }
        }
        return false;
    }

}
