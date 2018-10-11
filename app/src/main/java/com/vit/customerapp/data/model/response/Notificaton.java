package com.vit.customerapp.data.model.response;

public class Notificaton {

    private long id;

    private String title;

    private String content;

    private String time;

    private boolean isSeen;

    private String noti;

    public Notificaton(String title, String content, String time, boolean isSeen) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.isSeen = isSeen;
        noti = String.format("<b>%s:</b> %s", title, content);
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSeen() {
        return isSeen;
    }

    public void setSeen(boolean seen) {
        isSeen = seen;
    }
}
