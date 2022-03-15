# Instagram giveaway

### Deploy DynamoDB
- Using Docker: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html

### NoSQL Workbench
- Download: https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/workbench.settingup.html

### DynamoDB SDK
- Java: https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-dynamodb.html

### DynamoDB enhanced usage
- https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/examples-dynamodb-enhanced.html
- https://github.com/aws/aws-sdk-java-v2/blob/master/services-custom/dynamodb-enhanced/README.md

### API documentation
- https://documenter.getpostman.com/view/12247254/UVsHV8h7

### Setup
1. Use IntelliJ to open the project, download all maven dependencies.
2. Use NoSQL workbench, insert database schema file "IGGiveaway-db.json".
3. Use `docker-compose up` to run docker-compose file. This docker-compose file will run a DynamoDB on your localhost.
4. Use IntelliJ to run the project.

### Configuration
1. docker-compose.yml determines which port is running DynamoDB.
2. src/main/resource/application.properties defines Spring boot service port, DynamoDb connection port, and AWS confidential keys.
