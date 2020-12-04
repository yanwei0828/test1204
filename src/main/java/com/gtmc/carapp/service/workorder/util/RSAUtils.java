package com.gtmc.carapp.service.workorder.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtils {

    private static final int KEY_SIZE = 2048;

    private static final int MAX_DECRYPT_BLOCK = 256;

    private static final int MAX_ENCRYPT_BLOCK = 245;

    private static final String KEY_ALGORITHM = "RSA";

    private RSAUtils() {

    }

    public static void main(String[] args) throws Exception {

        // generate public and private keys
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        System.out.println("RSA公钥：" + getBase64PublicKey(pubKey));
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println("RSA私钥：" + getBase64PrivateKey(privateKey));


        // encrypt the message
        byte[] encrypted = encrypt(privateKey, "This is a secret message");
// <<encrypted message>>
        System.out.println(Hex.encodeHex(encrypted));

        // decrypt the message
        byte[] secret = decrypt(pubKey, encrypted);
        // This is a secret message
        System.out.println(new String(secret));
    }

    /**
     * 构建RSA秘钥对
     *
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.genKeyPair();
    }

    /**
     * 私钥加密
     *
     * @param privateKey
     * @param message
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }


    /**
     * 公钥分段加密
     *
     * @param publicKey
     * @param message
     * @return
     * @throws Exception
     */
    public static byte[] blockEncrypt(PublicKey publicKey, String message) throws Exception {
        byte[] data = message.getBytes("utf-8");
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }


    /**
     * 公钥解密
     *
     * @param publicKey
     * @param encrypted
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }


    /**
     * 私钥分段解密
     *
     * @param privateKey
     * @param encrypted
     * @return
     * @throws Exception
     */
    public static byte[] blockDecrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        int inputLen = encrypted.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encrypted, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encrypted, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }


    /**
     * 获取Base64编码后明文存储的公钥
     *
     * @param publicKey
     * @return
     */
    public static String getBase64PublicKey(PublicKey publicKey) {
        return new String(Base64.getEncoder().encode(publicKey.getEncoded()));
    }


    /**
     * 获取Base64编码后明文存储的私钥
     *
     * @param privateKey
     * @return
     */
    public static String getBase64PrivateKey(PrivateKey privateKey) {
        return new String(Base64.getEncoder().encode(privateKey.getEncoded()));
    }


    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }


}

