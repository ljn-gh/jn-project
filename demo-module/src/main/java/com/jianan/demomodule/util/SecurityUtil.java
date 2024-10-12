package com.jianan.demomodule.util;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.SM2;
import com.jianan.demomodule.config.Config;

/**
 * @Author:
 * @Date: 2024/3/20
 * @description
 **/
public class SecurityUtil {
    public static final SM2 SM2 = SmUtil.sm2(Config.SM2_PRIVATE_KEY,Config.SM2_PUBLIC_KEY);
}
