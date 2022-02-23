package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

public class CommentsDao {
    public static Comment getItem(DynamoDbEnhancedClient enhancedClient, String keyValue) {
        try {
            //Create a DynamoDbTable object
            DynamoDbTable<Comment> mappedTable = enhancedClient.table("Comments", TableSchema.fromBean(Comment.class));

            //Create a KEY object
            Key key = Key.builder()
                    .partitionValue(keyValue)
                    .build();

            // Get the item by using the key
            Comment result = mappedTable.getItem(key);
            System.out.println("The UUID value is "+result.getUuid());
            return result;
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        return null;
    }
    public static void putItem(DynamoDbEnhancedClient enhancedClient, Comment comments) {
        try {
            DynamoDbTable<Comment> commentsTable = enhancedClient.table("Comments", TableSchema.fromBean(Comment.class));

            // Put the customer data into a DynamoDB table
            commentsTable.putItem(comments);

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        System.out.println("done");
    }
}
