package com.nitian.socket.util.protocol.ssl;

/**
 * ssl client hello
 * Created by xws on 11/25/17.
 */
public class SSLClientHello {

    private int version;
    private String time;
    private String random;


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }
}
