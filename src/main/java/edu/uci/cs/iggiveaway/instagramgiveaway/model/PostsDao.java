package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class PostsDao {
    public static Post getItem(DynamoDbEnhancedClient enhancedClient, String keyValue) {
        try {
            //Create a DynamoDbTable object
            DynamoDbTable<Post> mappedTable = enhancedClient.table("Posts", TableSchema.fromBean(Post.class));

            //Create a KEY object
            Key key = Key.builder()
                    .partitionValue(keyValue)
                    .build();

            // Get the item by using the key
            Post result = mappedTable.getItem(r->r.key(key));
            System.out.println("The PostId value is "+result.getPostId());
            return result;
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        return null;
    }
    public static void putItem(DynamoDbEnhancedClient enhancedClient, Post posts) {
        try {
            DynamoDbTable<Post> postsTable = enhancedClient.table("Posts", TableSchema.fromBean(Post.class));

            // Put the customer data into a DynamoDB table
            postsTable.putItem(posts);

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        System.out.println("done");
    }
}

