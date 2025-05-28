package com.matirmony.matirmony.userDetails;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FriendList")
public class FriendList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friendListId")
    private int friendId;
    private ArrayList<String> sentRequest;
    private ArrayList<String> receiveRequest;
    private ArrayList<String> allFriends;

    public FriendList() {
    }

    public FriendList(int friendId, ArrayList<String> sentRequest, ArrayList<String> receiveRequest, ArrayList<String> allFriends) {
        this.friendId = friendId;
        this.sentRequest = sentRequest;
        this.receiveRequest = receiveRequest;
        this.allFriends = allFriends;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public ArrayList<String> getSentRequest() {
        return sentRequest;
    }

    public void setSentRequest(ArrayList<String> sentRequest) {
        this.sentRequest = sentRequest;
    }

    public ArrayList<String> getReceiveRequest() {
        return receiveRequest;
    }

    public void setReceiveRequest(ArrayList<String> receiveRequest) {
        this.receiveRequest = receiveRequest;
    }

    public ArrayList<String> getAllFriends() {
        return allFriends;
    }

    public void setAllFriends(ArrayList<String> allFriends) {
        this.allFriends = allFriends;
    }

    @Override
    public String toString() {
        return "FriendList{" +
                "friendId=" + friendId +
                ", sentRequest=" + sentRequest +
                ", receiveRequest=" + receiveRequest +
                ", allFriends=" + allFriends +
                '}';
    }
}
