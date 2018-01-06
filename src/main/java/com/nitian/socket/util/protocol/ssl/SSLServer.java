package com.nitian.socket.util.protocol.ssl;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * ssl server
 * Created by xws on 12/24/17.
 */
public class SSLServer {

    private SSLServerSocket sslServerSocket;

    public static void main(String[] args) throws Exception {

        SSLServer server = new SSLServer();

        server.init();

        System.out.println("SSLServer initialized.");

        server.process();

    }



    //服务器端将要使用到server.keystore和ca-trust.keystore

    public void init() throws Exception {

        int port = 7788;

        String keystorePath = "/software/www.1036225283.com.jks";

        String trustKeystorePath = "/software/www.1036225283.com.jks";

        String keystorePassword = "890512";

        SSLContext context = SSLContext.getInstance("SSL");


        //客户端证书库

        KeyStore keystore = KeyStore.getInstance("jks");

        FileInputStream keystoreFis = new FileInputStream(keystorePath);

        keystore.load(keystoreFis, keystorePassword.toCharArray());

        //信任证书库

        KeyStore trustKeystore = KeyStore.getInstance("jks");

        FileInputStream trustKeystoreFis = new FileInputStream(trustKeystorePath);

        trustKeystore.load(trustKeystoreFis, keystorePassword.toCharArray());


        //密钥库
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");

        kmf.init(keystore, keystorePassword.toCharArray());


        //信任库

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");

        tmf.init(trustKeystore);



        //初始化SSL上下文

        context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        //初始化SSLSocket

        sslServerSocket = (SSLServerSocket)context.getServerSocketFactory().createServerSocket(port);

        //设置这个SSLServerSocket需要授权的客户端访问

        sslServerSocket.setNeedClientAuth(true);

    }



    public void process() throws Exception {

        String bye = "Bye!";

        byte[] buffer = new byte[5000];

        while(true) {

            Socket socket = sslServerSocket.accept();

            InputStream in = socket.getInputStream();

            in.read(buffer);

            System.out.println("Received: " + new String(buffer));

            OutputStream out = socket.getOutputStream();

            out.write(bye.getBytes());

            out.flush();

        }

    }
}

