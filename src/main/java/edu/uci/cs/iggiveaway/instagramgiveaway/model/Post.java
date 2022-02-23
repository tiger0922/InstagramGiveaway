package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.List;

@DynamoDbBean
public class Post {
    private String postId;
    private String postURL;
    private String accountId;
    private List<String> commenters;

    public Post() {
        // Default constructor is required by AWS DynamoDB SDK
    }

    public Post(String postId, String postURL, String accountId, List<String> commenters) {
        this.postId = postId;
        this.postURL = postURL;
        this.accountId = accountId;
        this.commenters = commenters;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PostId")
    public String getPostId() {
        return postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }

    @DynamoDbAttribute("PostURL")
    public String getPostURL() {
        return postURL;
    }
    public void setPostURL(String postURL) {
        this.postURL = postURL;
    }

    @DynamoDbAttribute("AccountId")
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @DynamoDbAttribute("Commenters")
    public List<String> getCommenters() {
        return commenters;
    }
    public void setCommenters(List<String> commenters) {
        this.commenters = commenters;
    }

}
