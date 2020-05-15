package com.netcracker.curs.backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "followers", schema = "backend", catalog = "")
public class FollowersEntity {
    private UserEntity userByIdFollower;
    private UserEntity userByIdFollowed;
    private String id;

    @ManyToOne
    @JoinColumn(name = "id_follower", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByIdFollower() {
        return userByIdFollower;
    }

    public void setUserByIdFollower(UserEntity userByIdFollower) {
        this.userByIdFollower = userByIdFollower;
    }

    @ManyToOne
    @JoinColumn(name = "id_followed", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByIdFollowed() {
        return userByIdFollowed;
    }

    public void setUserByIdFollowed(UserEntity userByIdFollowed) {
        this.userByIdFollowed = userByIdFollowed;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }
}
