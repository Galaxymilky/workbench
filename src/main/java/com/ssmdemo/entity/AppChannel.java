package com.ssmdemo.entity;

/**
 * Created by dynamicniu on 2017/5/23.
 */
public class AppChannel {
    private long channelId;

    private String channelName;

    private int display;

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public long getChannelId() {
        return channelId;
    }

    public void setChannelId(long channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
