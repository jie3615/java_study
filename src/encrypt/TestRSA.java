package encrypt;

import com.sun.xml.internal.ws.api.ResourceLoader;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author: wyj
 * @date: 2020/5/9
 * @description:
 */
public class TestRSA {
    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 256;

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param privateKey 私钥字符串
     * @return
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey 公钥字符串
     * @return
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodedKey = Base64.decodeBase64(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * RSA加密
     *
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return
     */
    public static String encrypt(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE , publicKey);
        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data.getBytes(), offset, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64String(encryptedData));
    }

    /**
     * RSA解密
     *
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return
     */
    public static String decrypt(String data, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] dataBytes = Base64.decodeBase64(data);
        int inputLen = dataBytes.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offset > 0) {
            if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        // 解密后的内容
        return new String(decryptedData, "UTF-8");
    }

    /**
     * 签名
     *
     * @param data 待签名数据
     * @param privateKey 私钥
     * @return 签名
     */
    public static String sign(String data, PrivateKey privateKey) throws Exception {
        byte[] keyBytes = privateKey.getEncoded();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey key = keyFactory.generatePrivate(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(key);
        signature.update(data.getBytes());
        return new String(Base64.encodeBase64(signature.sign()));
    }

    /**
     * 验签
     *
     * @param srcData 原始字符串
     * @param publicKey 公钥
     * @param sign 签名
     * @return 是否验签通过
     */
    public static boolean verify(String srcData, PublicKey publicKey, String sign) throws Exception {
        byte[] keyBytes = publicKey.getEncoded();
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey key = keyFactory.generatePublic(keySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(key);
        signature.update(srcData.getBytes());
        return signature.verify(Base64.decodeBase64(sign.getBytes()));
    }
    public static String getRsaKey(String filePath) throws IOException {
        //读取pem证书
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String s = br.readLine();
        String str = "";
        s = br.readLine();
        while (s.charAt(0) != '-') {
            str += s;
            s = br.readLine();
        }
        System.out.println(str);
        return str;
    }
    public static void main(String[] args) {
        try {
            // 生成密钥对
            KeyPair keyPair = getKeyPair();
//            String privateKey = new String(Base64.encodeBase64(keyPair.getPrivate().getEncoded()));
//            String publicKey = new String(Base64.encodeBase64(keyPair.getPublic().getEncoded()));
            String classPath = TestRSA.class.getResource("").getPath();
            System.out.println(classPath);
            ClassLoader classLoader = TestRSA.class.getClassLoader();
            System.out.println(classLoader);
            String privateKey = getRsaKey(classPath+"conf/cert/boc/pri1043301599808060.pem");
            String publicKey = getRsaKey(classPath + "conf/cert/boc/pub1043301599808060.pem");
          /*  String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCw9MWihoOH81zw\n" +
                    "2ePD7sqg2cgiJhtiT5zWg8eGwsGecf3FtZnuAuIyfobFuBQLOXq3VV3FfhB6OhZ9\n" +
                    "G8QCV8zzu87eZT6D/sI0PlgSpRFV2I6hS4gXAHG9Dj1KLCsKNJHQ28U8by+/C8NK\n" +
                    "SkjRAkHboXPnM2bg33lIl7ZiW60GsVExMA/1qZYlwz4N8dNPzFoBgqjhJ1xSujVw\n" +
                    "WfwY+cDScta/vZkO6dXj4YXQAAYtSS8rlW+KxkCoN8x7fI7tSX+JzDjx1U9lN/6i\n" +
                    "A2OIU3UsB+SqEifQ2DNOfPFRh1YL3Uet8xh0uRw83rnLmYq+jte/qPuvoc6X2WAc\n" +
                    "z9g4kKPVAgMBAAECggEAXk5k0aQcjySswJNu5mnpxWWrWj+y0mcZb92VRYWfyIB5\n" +
                    "vZd17LnOsRhrXQ4oaH7+npw5OFpiD7HLcGuOv6zpRnAt5KpSceggudUL/QvJvFWI\n" +
                    "Vf+6DIApHZdsJI57dZnkqEmnyPh8mrV7I6mJOxKX5KQb7/id+HRA3+YyxiKTiP+u\n" +
                    "Juyg21tLkyNa5Dgk0PNZH7iE3Yp+uFjXP3IQAIHIr4zgLuRVQWXKYHddgNJo/var\n" +
                    "5iBJl5W10np2yB9qxc/dQvoGwJ/ftVIzcW7nvQNJhi2vQO3R8rRiPS4RpvSxMXTB\n" +
                    "zMcD3mp98pWuJNCmm5QjPMy7/beW9Px8/iIzJyusdQKBgQDV6niRodF3r0/otAgr\n" +
                    "Sg6JTnUzKhizeoWNZ2bwDWTj4UdrIkiOYb01WQfYl3QdBuaesKF9W3ZQeEljM8Hw\n" +
                    "py/00sfyn9IWBbzK+FBqkemml//zgjXzf7aEl5A2Qy2G2ekAlC74WHkgNvsjCZND\n" +
                    "Q2C70wW8i1LZmD0/OmjFUDJzFwKBgQDTxOHf6d9EDYr4l10VCrPPUEoI0bR3nOuE\n" +
                    "feP1onKabHptg9+KEQC1wJXVlz7IcVFTg65aktLbKEURH4R5uDWP8SrcC0rTA5kw\n" +
                    "VoNjPfFCxyN7vwC037B4ZfXDD00XaY6E9zVidFlIko+YDxbTiHDxBHsiKEqc7A8D\n" +
                    "KI107GDj8wKBgF0qbG6I+pDU0sm3tyMbcu9MM3EgMXfhsc7fLQ751moFULSRTuZs\n" +
                    "HRrh9q/XbqPBwKl2pi7RqdrsOJIzFZF5T0KilqDOCJK81vfTZDq15/w9kST+Hf86\n" +
                    "LUTcOdvYMDjeKUOuRjK1XbOxHnmpDvEppDTJeGMgjunkCzDBgKYHAqHrAoGAMqt8\n" +
                    "JDhwXHclFmAZPIyoA2XBoSSUGr6oN7Zrkf2BkGN+U6/nD8ELu3FOKKIcupcqj2IF\n" +
                    "mNRisV+MTxGMgBdMcZmR2L6285B72e4JV/idPo4pdlV4VM7lCZnT2Q1TM9Pk7vm7\n" +
                    "g0g1iMDQrOCHZKvT4kdhMwYS/EMbQHU2i5B3inMCgYBPkT7Y3SVFckcnPS4mNuEE\n" +
                    "s3kSR5YN6L//77OMZLcwZLmH2f7Ajb2j3nWMX4jp6TDH7N9ik+tXvWMIKlhkcmE9\n" +
                    "KQBio4pJjK2AlwEU5fX+bFp8tUsEhFaYInAn3ttlWARkC8vPyerYvu9764SaQr/y\n" +
                    "UluZ2CnDuoNs+THivksVjA==";
            String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsPTFooaDh/Nc8Nnjw+7K\n" +
                    "oNnIIiYbYk+c1oPHhsLBnnH9xbWZ7gLiMn6GxbgUCzl6t1VdxX4QejoWfRvEAlfM\n" +
                    "87vO3mU+g/7CND5YEqURVdiOoUuIFwBxvQ49SiwrCjSR0NvFPG8vvwvDSkpI0QJB\n" +
                    "26Fz5zNm4N95SJe2YlutBrFRMTAP9amWJcM+DfHTT8xaAYKo4SdcUro1cFn8GPnA\n" +
                    "0nLWv72ZDunV4+GF0AAGLUkvK5VvisZAqDfMe3yO7Ul/icw48dVPZTf+ogNjiFN1\n" +
                    "LAfkqhIn0NgzTnzxUYdWC91HrfMYdLkcPN65y5mKvo7Xv6j7r6HOl9lgHM/YOJCj\n" +
                    "1QIDAQAB";*/
            System.out.println("私钥:" + privateKey);
            System.out.println("公钥:" + publicKey);
            // RSA加密
            String data = "待加密的文字内容";
            String encryptData = encrypt(data, getPublicKey(publicKey));
            System.out.println("加密后内容:" + encryptData);
            // RSA解密
            String decryptData = decrypt(encryptData, getPrivateKey(privateKey));
            System.out.println("解密后内容:" + decryptData);

            // RSA签名
            String sign = sign(data, getPrivateKey(privateKey));
            // RSA验签
            boolean result = verify(data, getPublicKey(publicKey), sign);
            System.out.print("验签结果:" + result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}