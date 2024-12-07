package com.jianan.demomodule.util;

import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECParameterSpec;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.X509EncodedKeySpec;

public class SM2Util {

    public static String encrypt(String encodeStr, String publicKeyStr) {
        byte[] data = encodeStr.getBytes(StandardCharsets.UTF_8);
        PublicKey publicKey = createPublicKey(publicKeyStr);
        ECPublicKeyParameters localECPublicKeyParameters = null;

        if (publicKey instanceof BCECPublicKey) {
            BCECPublicKey localECPublicKey = (BCECPublicKey) publicKey;
            ECParameterSpec localECParameterSpec = localECPublicKey.getParameters();
            ECDomainParameters localECDomainParameters = new ECDomainParameters(localECParameterSpec.getCurve(),
                    localECParameterSpec.getG(), localECParameterSpec.getN());
            localECPublicKeyParameters = new ECPublicKeyParameters(localECPublicKey.getQ(), localECDomainParameters);
        }
        SM2Engine localSM2Engine = new SM2Engine();
        localSM2Engine.init(true, new ParametersWithRandom(localECPublicKeyParameters, new SecureRandom()));
        byte[] arrayOfByte2;
        try {
            arrayOfByte2 = localSM2Engine.processBlock(data, 0, data.length);
            return Util.byteToHex(arrayOfByte2);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static PublicKey createPublicKey(String publicKey) {
        PublicKey publickey = null;
        try {
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Util.hexToByte(publicKey));
            KeyFactory keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
            publickey = keyFactory.generatePublic(publicKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publickey;
    }

    public static void main(String[] args) {
    }
}