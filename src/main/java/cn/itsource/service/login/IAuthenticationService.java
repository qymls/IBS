package cn.itsource.service.login;

import javax.servlet.http.HttpSession;

public interface IAuthenticationService {
    public String getUserInfo(String code, HttpSession session);
}
