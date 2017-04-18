package com.nitian.client.keyValue;

import java.io.IOException;
import java.net.Socket;

public class Read extends Thread {

    private Socket socket;
    private int index = 0;

    public Read(Socket socket) {
        // TODO Auto-generated constructor stub
        this.socket = socket;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                index = index + 1;
                System.out.println("这是第几次请求:" + index);
                byte[] bs = new byte[1024 * 64];
                int size = socket.getInputStream().read(bs);
                System.out.println("读取到的数据 = " + new String(bs, 0, size));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
