package com.nitian.socket.util.protocol.ssl;

import com._1036225283.util.self.encrypt.UtilBase64;
import com._1036225283.util.self.java.ByteList;
import com._1036225283.util.self.java.UtilByte;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Created by xws on 12/13/17.
 */
public class Test2 {

    public static void main(String[] args) throws Exception {
//        test0();//测试 int to byte

        test2();//测试 java DH



    }

    //测试 DH算法
    public static void test2() throws Exception {
        try {
            //初始化发送方密钥
            //KeyPairGenerator 可以产生KeyPair
            KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            senderKeyPairGenerator.initialize(512);
            //KeyPair 常用的密钥载体 成为密钥对 分为公钥 私钥
            KeyPair senderKeyPair = senderKeyPairGenerator.generateKeyPair();
            //获取发送方公钥
            byte[] senderPublicKeyEnc = senderKeyPair.getPublic().getEncoded();


            //KeyFactory 密钥工厂 生成密钥 通过某种密钥的规范来还原密钥
            KeyFactory receiverKeyFactory = KeyFactory.getInstance("DH");
            //X509EncodedKeySpec 根据ASN.1进行密钥编码
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKeyEnc);
            PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
            DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
            //
            KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance("DH");
            receiverKeyPairGenerator.initialize(dhParameterSpec);

            KeyPair receiverKeyPair = receiverKeyPairGenerator.generateKeyPair();
            PrivateKey reveiverPrivateKey = receiverKeyPair.getPrivate();

            byte[] receiverPublicKeyEnc = receiverKeyPair.getPublic().getEncoded();

            //密钥构建
            KeyAgreement receiverKeyAgreement = KeyAgreement.getInstance("DH");
            receiverKeyAgreement.init(reveiverPrivateKey);
            receiverKeyAgreement.doPhase(receiverPublicKey, true);

            SecretKey receiverDesKey = receiverKeyAgreement.generateSecret("DES");

            KeyFactory senderKeyFactory = KeyFactory.getInstance("DH");
            x509EncodedKeySpec = new X509EncodedKeySpec(receiverPublicKeyEnc);
            PublicKey senderPublicKey = senderKeyFactory.generatePublic(x509EncodedKeySpec);

            KeyAgreement senderKeyAgreement = KeyAgreement.getInstance("DH");
            senderKeyAgreement.init(senderKeyPair.getPrivate());
            senderKeyAgreement.doPhase(senderPublicKey, true);

            SecretKey senderDesKey = senderKeyAgreement.generateSecret("DES");
            if (Objects.equals(receiverDesKey, senderDesKey)) {
                System.out.println("双方密钥相同");
            }

            //加密
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, senderDesKey);
            byte[] result = cipher.doFinal("123".getBytes());
            System.out.println("jdk DH encode: " + UtilBase64.encode(result));


            //解密
            cipher.init(Cipher.DECRYPT_MODE, receiverDesKey);
            result = cipher.doFinal(result);
            System.out.println("jdk DH decode: " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test0() {

        ByteList byteList = new ByteList();

        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0);
        byteList.add((byte) 0);

        byteList.add((byte) 0x86);
        byteList.add((byte) 0xdf);
        byteList.add((byte) 0x37);
        byteList.add((byte) 0x34);

        long b = 0x86df3734;
        long d = 0x1;
        long a = 2262775604l;
        byte[] bsa = UtilByte.longToByte4(a);
        System.out.println(UtilByte.toHex(bsa));
        System.out.println(UtilByte.toHex(byteList.getByte()));


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = simpleDateFormat.format(new Date(a * 1000));
        System.out.println(strTime);

        System.out.println(new Date(a * 1000).getTime());


        d = UtilByte.byte4ToLong(bsa, 0);

        System.out.println(d);
        System.out.println(UtilByte.byte4ToLong(byteList.getByte(), 4));

    }

    public static void test1() {
        long b = 0x86df3734;
        byte[] bsa = UtilByte.longToBytes(2262775604l);
        long aaa = UtilByte.bytesToLong(bsa);


        System.out.println(UtilByte.toHex(bsa));
        System.out.println(aaa);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = simpleDateFormat.format(new Date(aaa * 1000));

        System.out.println(strTime + "-------------");


    }

}
