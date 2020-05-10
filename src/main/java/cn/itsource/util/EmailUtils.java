package cn.itsource.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailUtils {

    @Autowired
    MailSender mailSender;

    public void send(String to, String subject, String context) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            // 发送人:和配置一致
            msg.setFrom("admin@163.com");
            // 收件人
            msg.setTo(to);
            // 主题
            msg.setSubject(subject);
            // 内容
            msg.setText(context);
            // 设置固定回邮地址
            msg.setReplyTo("admin@163.com");
            // 发送
            System.out.println("我要发送了"+context);
            mailSender.send(msg);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}
