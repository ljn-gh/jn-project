package com.jianan.demomodule.test.mail;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;


import com.jianan.demomodule.util.SecurityUtil;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;

/**
 * @Author:
 * @Date: 2024/3/19
 * @description
 **/
public class MailHy {
    @Test
    public void test(){
        //sm2加密
        KeyPair pair = SecureUtil.generateKeyPair("SM2");
        String aPrivate = Base64.encode(pair.getPrivate().getEncoded());
        String aPublic = Base64.encode(pair.getPublic().getEncoded());
        System.out.println(aPrivate);
        System.out.println(aPublic);
        SM2 sm2 = SmUtil.sm2(aPrivate,aPublic);
        String encode = sm2.encryptBase64("123", KeyType.PublicKey);
        System.out.println(encode);
        System.out.println(sm2.decryptStr(encode, KeyType.PrivateKey));
    }
    
    public static void main(String[] args) throws Exception{
        String username = "BAlBfsePPvTmMV0frjGxwjyXQnHEerEKaNi0R1kA0fWngybFjNx7N+KkFPPZVH8QFmbZYCKhwPAFoXgHjlv9xRULA1+xfHRuUxTvMXN/iSxKzJ6fnluJQ0ZQM+oLJ+5G5AdHb8vpGQRtHZTpomv7AvuEOpWTt7lfHQ==";//	邮箱发送账号
        String password = "BCHnHO3P7eM5qn9PfblxJ1RaW54aj2SNBzQmSvZrvgjl2VIElDHIF5vArM6/G69rr7grslO/aXm73yN177l0AWH9SQDQ9dRoLEUEVOcbfTL4td9NTw+/iI55KOXh5FacpnoNigdkHDBwo22+/DY54/I=";//	邮箱授权码
        String host = "BDQP5jIX7V/UFcOHU1H1damx6TYVevhzr5rfQ/2VLwjXEwRUwb0yHov/9qE+/DFD4YdzT4KZCwAMEjHgu4QVQS6bXltSgtQxKmZE6OXWN+Wrj4KPGILIfVkmWg3kGfIdb9hTDjXl9C1qHNLlmVIolv+NHA==";
        String port = "BK+c55Jym7vdrepYkdj7Wmxqjbp+5IOWuVvz1WYVwx4Tz2XalkahKEp9zYuOqBTK53udXCAOOmsr6P+Biz8iXNmZ2eQEmd/0FN93AwvgQOTHjHHqCDQKaVEgKRevrQQPvwD3";

        Properties props = new Properties();
        //	SMTP服务器
        props.put("mail.smtp.host", SecurityUtil.SM2.decryptStr(host,KeyType.PrivateKey));
        props.put("mail.smtp.port", SecurityUtil.SM2.decryptStr(port,KeyType.PrivateKey));
        props.put("mail.smtp.auth", "true");//	是否需要用户认证
        props.put("mail.smtp.starttls.enale", "true");//	启用TlS加密

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SecurityUtil.SM2.decryptStr(username,KeyType.PrivateKey)
                        ,SecurityUtil.SM2.decryptStr(password,KeyType.PrivateKey));
            }
        });

        //  控制台打印调试信息
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setSubject("主题");
        message.setText("测试邮箱发送");
        message.setFrom(new InternetAddress(SecurityUtil.SM2.decryptStr(username,KeyType.PrivateKey)));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("15148757024@163.com"));

        //	发送
        Transport.send(message);
    }
}
