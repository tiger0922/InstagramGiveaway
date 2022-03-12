package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import java.util.List;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Followers {
    private String accountName;
    private String accountId;
    private List<String> followers;

    public Followers() {
        // Default constructor is required by AWS DynamoDB SDK
    }

    public Followers(String accountName, String accountId, List<String> followers) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.followers = followers;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("AccountId")
    public String getAccountId() {
        return accountId;
    };

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

    @DynamoDbAttribute("Followers")
    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

}
