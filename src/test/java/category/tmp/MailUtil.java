package category.tmp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

//

/**
 * Created by Lenovo on 2015/6/3.
 */
//@TestPropertySource
public class MailUtil {

    private String host;
    private String port;
    private String user;
    private String pwd;
    private String from;
    private String to;
    private String auth;
    private String timeout;


    public String getHost() {
        return host;
    }

    @Value("#{configProperties['mail.host']}")
    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    @Value("#{configProperties['mail.port']}")
    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    @Value("#{configProperties['mail.user']}")
    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    @Value("#{configProperties['mail.pwd']}")
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFrom() {
        return from;
    }

    @Value("#{configProperties['mail.from']}")
    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    @Value("#{configProperties['mail.to']}")
    public void setTo(String to) {
        this.to = to;
    }

    public String getAuth() {
        return auth;
    }

    @Value("#{configProperties['mail.auth']}")
    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getTimeout() {
        return timeout;
    }

    @Value("#{configProperties['mail.timeout']}")
    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    /**
     * 发送简单文本邮件
     *
     * @param subject
     * @param content
     */
    public void sendTextMail(String subject, String content) {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

        // 建立邮件消息
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        // 设置收件人，寄件人 用数组发送多个邮件
        String[] array = getTo().split(";");
        if (array.length > 0) {
            mailMessage.setTo(array);
        } else {
            mailMessage.setTo(getTo());
        }

//        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        senderImpl.setUsername(getUser());  //  根据自己的情况,设置username
        senderImpl.setPassword(getPwd());  //  根据自己的情况, 设置password'

        Properties prop = new Properties();
        prop.put("mail.smtp.host", getHost());
        prop.put("mail.smtp.port", getPort());
        prop.put("mail.smtp.from", getFrom());
        prop.put("mail.smtp.auth", getAuth());
        prop.put("mail.smtp.timeout", getTimeout());

        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        System.out.println(" Text邮件发送成功.. ");
    }


    /**
     * 发送html邮件
     *
     * @param subject
     * @param htmlContent
     * @throws Exception
     */
    public void sendHtmlMail(String subject, String htmlContent) throws Exception {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();

        // 建立邮件消息
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
        // 设置收件人，寄件人 用数组发送多个邮件
        String[] array = getTo().split(";");
        if (array.length > 0) {
            messageHelper.setTo(array);
        } else {
            messageHelper.setTo(getTo());
        }
        messageHelper.setSubject(subject);
        messageHelper.setText(htmlContent);
        senderImpl.setUsername(getUser());  //  根据自己的情况,设置username
        senderImpl.setPassword(getPwd());  //  根据自己的情况, 设置password'

        Properties prop = new Properties();
        prop.put("mail.smtp.host", getHost());
        prop.put("mail.smtp.port", getPort());
        prop.put("mail.smtp.from", getFrom());
        prop.put("mail.smtp.auth", getAuth());
        prop.put("mail.smtp.timeout", getTimeout());

        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
        System.out.println(" HTML邮件发送成功.. ");
    }

    public static void main(String[] args) throws Exception {
        MailUtil util = new MailUtil();
        util.setTo("641995773@qq.com");
        util.sendTextMail("测试text", "system.out.print()");
        util.sendHtmlMail("测试html", "<html><body><input type='text' value='hello'></body></html>");
    }
}