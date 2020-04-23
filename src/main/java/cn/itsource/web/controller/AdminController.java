package cn.itsource.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Admin")
public class AdminController {

    /**
     * 到后台管理主页面
     *
     * @return
     */
    @RequestMapping("/admin")
    public String forwardAdminIndex() {
        return "WEB-INF/admin/admin";
    }

    /**
     * iframe对应的页面
     *
     * @param url
     * @return
     */
    @RequestMapping("/jframeUrl")
    public String jframeUrl(String url) {/*控制iframe的跳转*/
        return "WEB-INF/admin/" + url;
    }

}
