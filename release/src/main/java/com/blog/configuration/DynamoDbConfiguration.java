package com.blog.configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.blog.repositories")
public class DynamoDbConfiguration {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Value("${amazon.aws.region}")
    private String amazonAWSRegion;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard();

        builder.setCredentials(awsCredentialsProvider());
        builder.setEndpointConfiguration(endpointConfiguration());

        return builder.build();
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(amazonAWSCredentials());
    }

    @Bean
    public AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(
                amazonDynamoDBEndpoint,
                amazonAWSRegion);
    }

}