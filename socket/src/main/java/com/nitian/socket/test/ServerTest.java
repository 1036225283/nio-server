package com.nitian.socket.test;

import com.nitian.socket.ApplicationContext;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.core.Handler;
import com.nitian.socket.test.handler.ExitHandler;
import com.nitian.socket.test.handler.LoginHandler;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class ServerTest {


    public static void main(String[] args) {

        LogManager log = LogManager.getInstance();

        final Map<String, Long> countMap = new HashMap<>();

        try {
            log.putType(LogType.debug.toString());
            log.putType(LogType.error.toString());

            ApplicationContext applicationContext = ApplicationContext.getInstance();
            LogManager.setFileLog(true);
            countMap.put("count", 0L);

            applicationContext.getEngineHandle().getHandlerFactory()
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
                            long count = countMap.get("count");
                            map.put(CoreType.result.toString(), "l love you " + count + " days !!!");
                            count = count + 1;
                            countMap.put("count", count);
                        }
                    })
                    .regist("/fuck", new Handler() {
                        @Override
                        public void handle(Map<String, String> map) {
                            map.put(CoreType.result.toString(), "l fuck you !!!");
                        }
                    });

            applicationContext.getEngineSocket().start();
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
