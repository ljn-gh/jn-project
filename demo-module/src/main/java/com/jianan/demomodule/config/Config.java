package com.jianan.demomodule.config;

/**
 * @Author:
 * @Date: 2024/3/20
 * @description
 **/
public class Config {
    public static final String SM2_PUBLIC_KEY;
    
    public static final String SM2_PRIVATE_KEY;

    static {
        SM2_PUBLIC_KEY = System.getenv("Sm2PublicKey");
        SM2_PRIVATE_KEY = System.getenv("Sm2PrivateKey");
    }
}
