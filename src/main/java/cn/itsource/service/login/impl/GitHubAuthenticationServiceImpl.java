package cn.itsource.service.login.impl;

import cn.itsource.service.login.IAuthenticationService;
import cn.itsource.util.Constant;
import cn.itsource.util.ConstantApi;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Service
public class GitHubAuthenticationServiceImpl implements IAuthenticationService {
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    private RestTemplate restTemplate = new RestTemplate();

    private static final String GITHUB_ACCESSS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private static final String GITHUB_USER_URL = "https://api.github.com/user";

    public String getUserInfo(String code, HttpSession session) {
        Object loginSession = session.getAttribute(Constant.LOGIN_SESSION);
        if (loginSession != null) {/*如果用户已经登录了,在点击guthub登录，那么直接结束方法，不再请求了*/
            return "";
        }
        MultiValueMap<String, String> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("client_id", ConstantApi.GITHUB_Client_ID);
        requestEntity.add("client_secret", ConstantApi.GITHUB_Client_Secret);
        requestEntity.add("code", code);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(GITHUB_ACCESSS_TOKEN_URL, requestEntity, String.class);
        String message = responseEntity.getBody().trim();

        String access_token = message.split("&")[0].split("=")[1];
        System.out.println(access_token);
        String url = GITHUB_USER_URL + "?access_token=" + access_token;
        responseEntity = restTemplate.getForEntity(url, String.class);/*https://api.github.com/user?access_token=7aba1a5443473c1d0900883219ae258f36735296*/
        JSONObject githubUserInfo = JSON.parseObject(responseEntity.getBody().trim());
        String loginName = githubUserInfo.getString("login");
        System.out.println(githubUserInfo);
        session.setAttribute(Constant.LOGIN_SESSION, loginName);
        /*System.out.println(login);*/
        return null;
    }

    private String insertUser(JSONObject githubToken) throws JSONException {


        // 异步将网络资源下载到本地，并且更新数据库
        threadPoolTaskExecutor.execute(() -> {

        });
        return "";
    }

}
