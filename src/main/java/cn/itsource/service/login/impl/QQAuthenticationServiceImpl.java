package cn.itsource.service.login.impl;

import cn.itsource.util.ConstantApi;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QQAuthenticationServiceImpl  {
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    private RestTemplate restTemplate = new RestTemplate();

    private static final String QQ_ACCESSS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token";

    private static final String QQ_OPENID_URL = "https://graph.qq.com/oauth2.0/me";

    private static final String QQ_USER_URL = "https://graph.qq.com/user/get_user_info";

    public String getUserInfo(String code) {

        String appid = ConstantApi.QQlOGIN_APP_ID;
        String appkey = ConstantApi.QQlOGIN_APP_KEY;

        /* 获取access_token */
        String tokenUrl = QQ_ACCESSS_TOKEN_URL + "?grant_type=authorization_code&client_id=" + appid +
                "&client_secret=" + appkey + "&code=" + code + "&redirect_uri=";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(tokenUrl, String.class);
        String message = responseEntity.getBody().trim();
        String access_token = message.split("&")[0].split("=")[1];

        /* 获取openid */
        String openidUrl = QQ_OPENID_URL + "?access_token=" + access_token;
        responseEntity = restTemplate.getForEntity(openidUrl, String.class);
        message = responseEntity.getBody().trim();
        message = message.split("\\(")[1].split("\\)")[0];

        return null;
    }

    private String insertUser(JSONObject qqUserInfo, String openid) throws JSONException {


        // 异步将网络资源下载到本地，并且更新数据库
        threadPoolTaskExecutor.execute(() -> {

        });
        return "";
    }

}
