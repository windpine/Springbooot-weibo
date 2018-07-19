package com.my.db;

public class User_info {
  private String uid;
  private String avatar_url;
  private Long followers;
  private Long follows;
  private Long tweets;
  private String sex;

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getAvatar_url() {
    return avatar_url;
  }

  public void setAvatar_url(String avatar_url) {
    this.avatar_url = avatar_url;
  }

  public Long getFollowers() {
    return followers;
  }

  public void setFollowers(Long followers) {
    this.followers = followers;
  }

  public Long getFollows() {
    return follows;
  }

  public void setFollows(Long follows) {
    this.follows = follows;
  }

  public Long getTweets() {
    return tweets;
  }

  public void setTweets(Long tweets) {
    this.tweets = tweets;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }
}
