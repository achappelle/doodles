package com.me.itu.example.persistence;

public class Message {
    private String content;

    public Message() {}
    public Message(String content) {
        this.content = content;
    }

    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; }
}
