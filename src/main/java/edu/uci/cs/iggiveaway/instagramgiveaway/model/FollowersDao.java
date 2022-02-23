package edu.uci.cs.iggiveaway.instagramgiveaway.model;

import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class FollowersDao {
    public static Followers getItem(DynamoDbEnhancedClient enhancedClient, String keyValue) {
        try {
            System.out.println(TableSchema.fromBean(Followers.class).attributeNames());
            //Create a DynamoDbTable object
            DynamoDbTable<Followers> mappedTable = enhancedClient.table("Followers", TableSchema.fromBean(Followers.class));

            //Create a KEY object
            Key key = Key.builder()
                    .partitionValue(keyValue)
                    .build();

            // Get the item by using the key
            Followers result = mappedTable.getItem(key);
            System.out.println("The AccountName value is "+result.getAccountName());
            return result;

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }

        return null;
    }
    public static void putItem(DynamoDbEnhancedClient enhancedClient, Followers followers) {
        try {
            DynamoDbTable<Followers> followersTable = enhancedClient.table("Followers", TableSchema.fromBean(Followers.class));

            // Put the customer data into a DynamoDB table
            followersTable.putItem(followers);

        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        System.out.println("done");
    }
    public static List<Followers> scan(DynamoDbEnhancedClient enhancedClient) {

        try{
            // Create a DynamoDbTable object
            DynamoDbTable<Followers> followersTable = enhancedClient.table("Followers", TableSchema.fromBean(Followers.class));
            Iterator<Followers> results = followersTable.scan().items().iterator();
            List<Followers> ret = new ArrayList<>();
            while (results.hasNext()) {
                Followers rec = results.next();
                ret.add(rec);
                System.out.println("The account name is "+rec.getAccountName());
                System.out.println("The account id is " +rec.getAccountId());
            }
            return ret;
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            //System.exit(1);
        }
        System.out.println("Done");
        return null;
    }
}
