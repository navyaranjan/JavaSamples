package com.example.kafka.service;

import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.HashtagEntity;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;

@Service
public class TweetStreamService {

    @Autowired
    private TwitterStream twitterStream;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private LinkedBlockingQueue<Status> queue = new LinkedBlockingQueue<Status>(1000);

    Logger logger = LoggerFactory.getLogger(TweetStreamService.class);

    String[] keywordsArray = { "Cricket", "footbal" }; // filter based on your choice of keywords
    FilterQuery filtre = new FilterQuery(keywordsArray);

    public void run() throws InterruptedException {
        StatusListener listener = new StatusListener() {

            @Override
            public void onException(Exception ex) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onStatus(Status status) {
                queue.offer(status);
            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }
        };
        twitterStream.addListener(listener);
        FilterQuery query = new FilterQuery().track(new String[] { "ENGvsAUS", "CWC19" });
        // twitterStream.filter(query);
        twitterStream.sample("en");

        Thread.sleep(5000);
        while (true) {
            Status tweets = queue.poll();

            if (tweets == null) {
                Thread.sleep(100);

            } else {
                kafkaTemplate.send("twitter_stream", tweets.getText());

                // for (HashtagEntity hashtage : tweets.ge){
                // System.out.println("Hashtag: " + hashtage.getText());
                // kafkaTemplate.send("twitter_stream", hashtage.getText());
                // }
            }
        }

    }
}