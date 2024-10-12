package com.jianan.demomodule.test.mail;

/**
 * @Author:
 * @Date: 2024/3/20
 * @description
 **/
public class MailProperty {
    private String mailSmtpUsername;
    
    private String mailSmtpPassword;
    
    private String mailSmtpHost;
    
    private String mailSmtpPort;

    public String getMailSmtpUsername() {
        return mailSmtpUsername;
    }

    public void setMailSmtpUsername(String mailSmtpUsername) {
        this.mailSmtpUsername = mailSmtpUsername;
    }

    public String getMailSmtpPassword() {
        return mailSmtpPassword;
    }

    public void setMailSmtpPassword(String mailSmtpPassword) {
        this.mailSmtpPassword = mailSmtpPassword;
    }

    public String getMailSmtpHost() {
        return mailSmtpHost;
    }

    public void setMailSmtpHost(String mailSmtpHost) {
        this.mailSmtpHost = mailSmtpHost;
    }

    public String getMailSmtpPort() {
        return mailSmtpPort;
    }

    public void setMailSmtpPort(String mailSmtpPort) {
        this.mailSmtpPort = mailSmtpPort;
    }
}
