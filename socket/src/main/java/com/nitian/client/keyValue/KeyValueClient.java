package com.nitian.client.keyValue;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * key-value系统的客户端，单线程，
 */
public class KeyValueClient extends Thread {


    private Socket socket;
    private String ip;
    private int port;

    public KeyValueClient setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public KeyValueClient setPort(int port) {
        this.port = port;
        return this;
    }

    public KeyValueClient connection() {
        try {
            InetAddress inetAddress = InetAddress.getByName(ip);
            socket = new Socket(inetAddress.getHostAddress(), port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void set(String key, String value) {

    }

    public String get(String key) {
        return null;
    }

    public void remove(String key) {

    }

    public static void main(String[] args) throws IOException {
        new KeyValueClient().start();
    }


    private int index = 0;

    public KeyValueClient() throws IOException {
        // TODO Auto-generated constructor stub
        InetAddress inet = InetAddress.getByName("localhost");
        socket = new Socket(inet.getHostAddress(), 88);

        new Read(socket).start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                sleep(5000);
                index = index + 1;
                OutputStream out = socket.getOutputStream();

                PrintWriter writer = new PrintWriter(out);

                String message = UtilProtocol.result("自定义协议，发送次数: " + index);
                System.out.println("message = " + message);
                writer.print(message);
                writer.flush();
            } catch (IOException | InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
