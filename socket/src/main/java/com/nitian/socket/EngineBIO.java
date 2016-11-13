package com.nitian.socket;

import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Created by 1036225283 on 2016/11/13.
 * SOCKET ENGINE BIO
 */
public class EngineBIO implements Engine {

    /**
     * 业务引擎
     */
    private Engine handleEngine;

    public EngineBIO(int port) {
        this.port = port;
    }

    public EngineBIO() {
    }

    @Override
    public void push(Map<String, Object> map) {

    }

    public Engine getHandleEngine() {
        return handleEngine;
    }

    public void setHandleEngine(Engine handleEngine) {
        this.handleEngine = handleEngine;
    }


    private ApplicationContext applicationContext = ApplicationContext
            .getInstance();

    private LogManager log = LogManager.getInstance();

    private Integer port;
    private ServerSocket serverSocket;


    public void start() throws IOException {
        if (port == null) {
            port = 8080;
        }
        Thread.currentThread().setName("线程：服务主线程");
        log.info(LogType.thread, this, Thread.currentThread().toString());
        serverSocket = new ServerSocket(port);
        log.info(LogType.debug, this, "server is start");
        while (true) {
            Socket socket = serverSocket.accept();
            log.dateInfo(LogType.time, this, "____________________________________________________");
            log.dateInfo(LogType.time, this, "第一步：接收socket开始");
            applicationContext.getQueueParse().push(socket);
//            WriteTest writeTest = new WriteTest(socket);
//            writeTest.start();
            log.dateInfo(LogType.time, this, "第一步：接收socket结束");
        }
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }


}
