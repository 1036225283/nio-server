package com.nitian.socket.util.write;

import com.nitian.socket.EngineSocket;
import com.nitian.socket.core.CoreType;
import com.nitian.socket.util.parse.UtilParseHttpWrite;
import com.nitian.util.log.LogManager;
import com.nitian.util.log.LogType;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

/**
 * 抽象写工具类
 */
public abstract class UtilWrite {

    protected static LogManager log = LogManager.getInstance();

    public abstract void write(Map<String, String> map, EngineSocket engineSocket);
}
