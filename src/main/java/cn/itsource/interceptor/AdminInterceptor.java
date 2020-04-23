package cn.itsource.interceptor;

import cn.itsource.util.Constant;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminInterceptor implements HandlerInterceptor {
    /**
     * 执行目标方法之前的方法
     *
     * @param req
     * @param res
     * @param o
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws IOException {
        Object user = req.getSession().getAttribute(Constant.LOGIN_SESSION);
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/Admin/login");
            return false;
        } else {
            return true;
        }

    }

    /**
     * 执行目标方法之后的方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
    }

    /**
     * 执行完页面之后的方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
    }

}
