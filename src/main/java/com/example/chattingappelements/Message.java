package com.example.chattingappelements;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Comparable, Serializable {

    private String content;
    private LocalDateTime timestamp;
    private boolean isRead;
    private String sender; // Added sender field to track message origin
    private static int idCounter = 1;
    private String smsId;





    public Message(String content, String sender) {
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isRead = false;
        this.sender = sender;
        this.smsId = String.format("%d", idCounter++);
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getsmsId() {
        return smsId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public boolean isRead() {
        return isRead;
    }

    public String detailedMsg(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        return String.format("\t%-15s | sent time : %s" ,getContent(),timestamp.format(dateFormatter));
    }


    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + timestamp.format(dateFormatter) + "] " + sender + ": " + content;
    }

    @Override
    public int compareTo(Object o) {
        Message s = (Message) o;
        return s.getTimestamp().compareTo(this.timestamp); // Descending order
    }

    public String detail(){
        return String.format("%s : %-15s | sender : %-8s (%s)",getsmsId(),getContent(),sender,timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
    }

    public String onlyMessageContent(){
        return String.format("%s",content);
    }

    public String getMessageDate(){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s",timestamp.format(dateFormatter));
    }

    public String getMessageTime(){
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("%s",timestamp.format(timeFormatter));
    }
}
