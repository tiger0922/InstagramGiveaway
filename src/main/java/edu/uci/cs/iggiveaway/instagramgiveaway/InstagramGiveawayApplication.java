package edu.uci.cs.iggiveaway.instagramgiveaway;

import java.util.Arrays;
import java.net.*;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.*;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.waiters.DynamoDbWaiter;

@SpringBootApplication
public class InstagramGiveawayApplication {

	protected final static Logger logger = LoggerFactory.getLogger(InstagramGiveawayApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(InstagramGiveawayApplication.class, args);
		System.out.println("HelloWorld");
		logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");

		String tableName = "Test";

		Region region = Region.US_WEST_2;
		DynamoDbClient ddb = DynamoDbClient.builder()
				.region(region)
				.endpointOverride(URI.create("http://localhost:8000"))
				.build();

		String result = createTable(ddb, tableName, "Account");

		Map<String, AttributeValue> item = new HashMap<>();
		item.put("Account", AttributeValue.builder().s("anderson").build());
		item.put("AccountId", AttributeValue.builder().s("123").build());
		item.put("Followers", AttributeValue.builder().ss("a","b","c").build());

		PutItemRequest request = PutItemRequest.builder()
				.tableName(tableName)
				.item(item)
				.build();

		try {
			ddb.putItem(request);
		} catch (ResourceNotFoundException e) {
			System.err.format("Error: The table \"%s\" can't be found.\n", tableName);
			System.err.println("Be sure that it exists and that you've typed its name correctly!");
			System.exit(1);
		} catch (DynamoDbException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}

		ddb.close();

	}
	// snippet-start:[dynamodb.java2.create_table.main]
	public static String createTable(DynamoDbClient ddb, String tableName, String key) {

		DynamoDbWaiter dbWaiter = ddb.waiter();
		CreateTableRequest request = CreateTableRequest.builder()
				.attributeDefinitions(AttributeDefinition.builder()
						.attributeName(key)
						.attributeType(ScalarAttributeType.S)
						.build())
				.keySchema(KeySchemaElement.builder()
						.attributeName(key)
						.keyType(KeyType.HASH)
						.build())
				.provisionedThroughput(ProvisionedThroughput.builder()
						.readCapacityUnits(new Long(10))
						.writeCapacityUnits(new Long(10))
						.build())
				.tableName(tableName)
				.build();

		String newTable ="";
		try {
			CreateTableResponse response = ddb.createTable(request);
			DescribeTableRequest tableRequest = DescribeTableRequest.builder()
					.tableName(tableName)
					.build();

			// Wait until the Amazon DynamoDB table is created
			WaiterResponse<DescribeTableResponse> waiterResponse =  dbWaiter.waitUntilTableExists(tableRequest);
			waiterResponse.matched().response().ifPresent(System.out::println);

			newTable = response.tableDescription().tableName();
			return newTable;

		} catch (DynamoDbException e) {
			System.err.println(e.getMessage());
			//System.exit(1);
		}
		return "";
	}
	// snippet-end:[dynamodb.java2.create_table.main]

}
