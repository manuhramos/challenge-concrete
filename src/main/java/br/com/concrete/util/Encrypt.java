package br.com.concrete.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Emanuella Cavalcante on 13/06/2018.
 */
public class Encrypt {

    private static final String _HmacSHA1 = "HmacSHA1";
    private static String _secret = "c0ncR373";

    public static String encryptHmac(String value){
        try {
            Mac sha1_HMAC = Mac.getInstance(_HmacSHA1);
            SecretKeySpec secret_key = new SecretKeySpec(_secret.getBytes("ASCII"), _HmacSHA1);
            sha1_HMAC.init(secret_key);
            return Hex.encodeHexString(sha1_HMAC.doFinal(value.getBytes("ASCII")));
        } catch (Exception e){
            e.printStackTrace();
            return value;
        }
    }
}
