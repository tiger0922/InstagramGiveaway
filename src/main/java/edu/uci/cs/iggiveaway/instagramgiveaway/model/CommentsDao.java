package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.core.pagination.sync.SdkIterable;
import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo;

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
            if (result == null) return null;
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
    public static List<Comment> queryPostId(DynamoDbEnhancedClient enhancedClient, String postId) {
        try{
            DynamoDbIndex<Comment> commentByPostId = enhancedClient.table("Comments", TableSchema.fromBean(Comment.class)).index("PostIdIndex");
            QueryConditional queryConditional = keyEqualTo(Key.builder()
                            .partitionValue(postId)
                            .build());

            // Get items in the table and write out the ID value
            Iterator<Page<Comment>> results =  commentByPostId.query(queryConditional).iterator();
            List<Comment> result = new ArrayList<>();

            while (results.hasNext()) {
                for (Comment item : results.next().items()) {
                    result.add(item);
                }
                //System.out.println("The record uuid is "+result);
            }
            return result;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        return null;
    }
}
