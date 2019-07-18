package com.example.kafka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterConfig {

    @Value("${twitter.kafka.sample.apikey}")
    private String apiKey;

    @Value("${twitter.kafka.sample.apisecret}")
    private String apiSecret;

    @Value("${twitter.kafka.sample.accessToken}")
    private String accessToken;

    @Value("${twitter.kafka.sample.accessTokenSecret}")
    private String accessTokenSecret;

    @Bean
    ConfigurationBuilder configurationBuilder() {
        ConfigurationBuilder builder = new ConfigurationBuilder();
        builder.setOAuthConsumerKey(apiKey).setOAuthConsumerSecret(apiSecret).setOAuthAccessToken(accessToken)
                .setOAuthAccessTokenSecret(accessTokenSecret);
        return builder;
    }

    @Bean
    public TwitterStream twitterStream() {
        return new TwitterStreamFactory(configurationBuilder().build()).getInstance();
    }
}