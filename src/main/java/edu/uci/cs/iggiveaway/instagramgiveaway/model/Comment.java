package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Comment {
    private String uuid;
    private String follows;
    private String account;
    private String content;
    private String postURL;
    private int timestamp;

    public Comment() {
        // Default constructor is required by AWS DynamoDB SDK
    }

    public Comment(String uuid, String follows, String account, String content, String postURL, int timestamp) {
        this.uuid = uuid;
        this.follows = follows;
        this.postURL = postURL;
        this.account = account;
        this.content = content;
        this.timestamp = timestamp;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("UUID")
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @DynamoDbAttribute("Follows")
    public String getFollows() {
        return follows;
    }
    public void setFollows(String follows) {
        this.follows = follows;
    }

    @DynamoDbAttribute("PostURL")
    public String getPostURL() {
        return postURL;
    }
    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    @DynamoDbAttribute("Account")
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    @DynamoDbAttribute("Content")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @DynamoDbAttribute("Timestamp")
    public int getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

}
