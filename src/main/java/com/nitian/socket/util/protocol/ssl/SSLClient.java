package com.nitian.socket.util.protocol.ssl;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;

/**
 * Created by xws on 12/24/17.
 */

public class SSLClient {

    private SSLSocket sslSocket;

    public static void main(String[] args) throws Exception {

        SSLClient client = new SSLClient();

        client.init();

        System.out.println("SSLClient initialized.");

        client.process();

    }



    //客户端将要使用到client.keystore和ca-trust.keystore

    public void init() throws Exception {

        String host = "www.1036225283.com";

        int port = 7788;

        String keystorePath = "/software/www.1036225283.com.jks";

        String trustKeystorePath = "/software/www.1036225283.com.jks";

        String keystorePassword = "890512";

        SSLContext context = SSLContext.getInstance("SSL");

        //客户端证书库

        KeyStore clientKeystore = KeyStore.getInstance("jks");

        FileInputStream keystoreFis = new FileInputStream(keystorePath);

        clientKeystore.load(keystoreFis, keystorePassword.toCharArray());

        //信任证书库

        KeyStore trustKeystore = KeyStore.getInstance("jks");

        FileInputStream trustKeystoreFis = new FileInputStream(trustKeystorePath);

        trustKeystore.load(trustKeystoreFis, keystorePassword.toCharArray());



        //密钥库

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");

        kmf.init(clientKeystore, keystorePassword.toCharArray());


        //信任库

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");

        tmf.init(trustKeystore);



        //初始化SSL上下文

        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);



        sslSocket = (SSLSocket)context.getSocketFactory().createSocket(host, port);

    }



    public void process() throws Exception {

        //往SSLSocket中写入数据

        String hello = "hello boy!";

        OutputStream out = sslSocket.getOutputStream();

        out.write(hello.getBytes(), 0, hello.getBytes().length);

        out.flush();



        //从SSLSocket中读取数据

        InputStream in = sslSocket.getInputStream();

        byte[] buffer = new byte[5000];

        in.read(buffer);

        System.out.println(new String(buffer));

    }
}