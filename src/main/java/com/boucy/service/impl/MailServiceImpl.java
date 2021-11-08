package com.boucy.service.impl;

import com.boucy.mapper.UserMapper;
import com.boucy.service.MailService;
import com.boucy.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserMapper userMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 给前端输入的邮箱，发送验证码
     *
     * @param email
     * @param session
     * @return
     */
    public boolean sendMimeMail(String email, HttpSession session) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject("图书商城账号注册验证码~");//主题
            //生成随机数
            String code = randomCode();

            //将随机数放置到redis中
            redisUtil.set(email,code,1800);

            mailMessage.setText("欢迎来到图书商城网站，验证码：" + code + ";\n注意该验证码有时间限制，请尽快使用！" +
                    "祝您使用愉快~");//内容
            // TODO 设置验证码的有效期为30分钟

            mailMessage.setTo(email);//发给谁

            mailMessage.setFrom(from);//你自己的邮箱

            mailSender.send(mailMessage);//发送
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 随机生成4位数的验证码
     *
     * @return String code
     */
    public String randomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 校验验证码是否一致
     */
    public boolean verify(String voCode, String voEmail) {
        String code = (String) redisUtil.get(voEmail);
//        如果email数据为空，或者不一致，注册失败
        if (code==null|!voCode.equals(code)) {
            return false;
        }
        return true;
    }
}

