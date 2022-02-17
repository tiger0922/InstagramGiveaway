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

		String result = DynamoDbDAO.createTable(ddb, tableName, "Account");
		//DynamoDbDAO.deleteTable(ddb, "Test");

		Map<String, AttributeValue> item = new HashMap<>();
		item.put("Account", AttributeValue.builder().s("anderson").build());
		item.put("AccountId", AttributeValue.builder().s("123").build());
		item.put("Followers", AttributeValue.builder().ss("a","b","c").build());

		DynamoDbDAO.putItem(ddb, tableName, item);

		ddb.close();

	}


}
