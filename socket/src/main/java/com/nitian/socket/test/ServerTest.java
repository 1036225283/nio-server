package com.nitian.socket.test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.tcp.ServerTcp;
import com.nitian.socket.test.handler.ExitHandler;
import com.nitian.socket.test.handler.LoginHandler;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

public class ServerTest {

    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();

        try {
            ServerTcp serverTcp = new ServerTcp(88);
            serverTcp.getApplicationContext().getHandlerFactory()
                    .regist("/user/login", new LoginHandler())
                    .regist("/exit", new ExitHandler())
                    .regist("/test", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "this is test");
                        }
                    })
                    .regist("/love", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "l love you !!!");
                        }
                    })
                    .regist("/fuck", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "l fuck you !!!");
                        }
                    });
            serverTcp.start();
        } catch (Exception e) {
            // TODO: handle exception
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            e.printStackTrace(new PrintStream(baos));
            String exception = baos.toString();
            System.out.println("baos:" + exception);
            log.info(LogType.error, null, "error+" + exception);
        }

    }
}
