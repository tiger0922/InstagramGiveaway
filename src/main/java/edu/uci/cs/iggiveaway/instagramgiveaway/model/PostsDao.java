package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.*;
import software.amazon.awssdk.enhanced.dynamodb.model.Page;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional.keyEqualTo;

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
    public static List<Post> queryAccountId(DynamoDbEnhancedClient enhancedClient, String accountId) {
        try{
            DynamoDbIndex<Post> postsByAccountId = enhancedClient.table("Posts", TableSchema.fromBean(Post.class)).index("AccountIdIndex");
            QueryConditional queryConditional = keyEqualTo(Key.builder()
                    .partitionValue(accountId)
                    .build());

            // Get items in the table and write out the ID value
            Iterator<Page<Post>> results =  postsByAccountId.query(queryConditional).iterator();
            List<Post> result = new ArrayList<>();

            while (results.hasNext()) {
                for (Post item : results.next().items()) {
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

