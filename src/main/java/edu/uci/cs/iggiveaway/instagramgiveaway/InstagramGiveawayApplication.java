package edu.uci.cs.iggiveaway.instagramgiveaway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InstagramGiveawayApplication {

	protected final static Logger logger = LoggerFactory.getLogger(InstagramGiveawayApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(InstagramGiveawayApplication.class, args);
//		System.out.println("HelloWorld");
//		logger.debug("Debug log message");
//      logger.info("Info log message");
//      logger.error("Error log message");
//
//		Region region = Region.US_WEST_2;
//		DynamoDbClient ddb = DynamoDbClient.builder()
//				.region(region)
//				.endpointOverride(URI.create("http://localhost:8000"))
//				.build();
//
//		DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
//				.dynamoDbClient(ddb)
//				.build();
//
//		ddb.close();

	}


}
