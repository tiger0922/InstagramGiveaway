package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;

@DynamoDbBean
public class Comment {
    private String postOwner;
    private String accountId;
    private String accountName;
    private String content;
    private String postId;
    private String postURL;
    private int timestamp;

    public Comment() {
        // Default constructor is required by AWS DynamoDB SDK
    }

    public Comment(String postOwner, String accountId, String accountName, String content, String postURL, String postId, int timestamp) {
        this.postOwner = postOwner;
        this.postURL = postURL;
        this.accountId = accountId;
        this.accountName = accountName;
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
    }

    @DynamoDbAttribute("PostOwner")
    public String getPostOwner() {
        return postOwner;
    }
    public void setPostOwner(String postOwner) {
        this.postOwner = postOwner;
    }

    @DynamoDbAttribute("PostURL")
    public String getPostURL() {
        return postURL;
    }
    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = "PostIdIndex")
    @DynamoDbAttribute("PostId")
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("AccountId")
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @DynamoDbAttribute("AccountName")
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @DynamoDbAttribute("Content")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("Timestamp")
    public int getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

}
