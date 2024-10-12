package com.jianan.demomodule.test.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import com.jianan.demomodule.util.SecurityUtil;

import cn.hutool.crypto.asymmetric.KeyType;

public class Mail163 {
    public static void main(String[] args) throws Exception {
        //	账号信息
        String username = "15148757024@163.com";//	邮箱发送账号
        String password = "BLXlI/PpeqykF7b0zMbD+OM3NikuRvtTiVeao+C3WvRZiqEESbKarJCqRo1UBaPOM7XZx4j52ic3AnBBzhWmBMKyqua5zPTarD0o68g+Kftrs7mDlnfBIRWADF2vNj1KFGbkvnRKpOE9oSiXV7jl1CI=";//	邮箱授权码

        //	创建一个配置文件，并保存
        Properties props = new Properties();

        //	SMTP服务器连接信息
        //  126——smtp.126.com
        //  163——smtp.163.com
        //  qqsmtp.qq.com"
        props.put("mail.smtp.host", "smtp.163.com");//	SMTP主机名

        //  126——25
        //  163——645 
        props.put("mail.smtp.port", "25");//	主机端口号
        props.put("mail.smtp.auth", "true");//	是否需要用户认证
        props.put("mail.smtp.starttls.enale", "true");//	启用TlS加密

        Session session = Session.getInstance(props,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(username, SecurityUtil.SM2.decryptStr(password, KeyType.PrivateKey));
            }
        });

        //  控制台打印调试信息
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        message.setSubject("主题");
        message.setText("测试邮箱发送");
        message.setFrom(new InternetAddress("15148757024@163.com"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("15148757024@163.com"));

        //	发送
        Transport.send(message);
    }
}
