package com.puao.bean;

public class CurrentInfo {
    private String ip;

    private String port;

    private String user;

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getUser() {
        return user;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user +":"+ip+":"+port;
    }
}
