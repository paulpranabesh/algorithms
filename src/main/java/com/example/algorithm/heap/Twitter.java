package com.example.algorithm.heap;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
 *
 * Implement the Twitter class:
 *
 * Twitter() Initializes your twitter object.
 * void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
 * List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
 * void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
 * void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.
 */
public class Twitter {

    Map<Integer, Set<Integer>> followersMap= new HashMap<>();
    Map<Integer, LinkedList<TweetDetails>> tweets= new HashMap<>();
    int currentTime = 0;

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        tweets.compute(userId, (now, prev)->{
            LinkedList<TweetDetails> list = prev == null ? new LinkedList<>() : prev;
            list.addFirst(new TweetDetails(tweetId, currentTime++));
            return list;
        });
    }

    public List<Integer> getNewsFeed(int userId) {
        Queue<TweetDetails> queue = new PriorityQueue<>(Comparator.comparing(TweetDetails::getTime));
        follow(userId, userId);
        LinkedList<TweetDetails> list = new LinkedList<>();
        followersMap.get(userId).forEach(u->{
            tweets.getOrDefault(u, new LinkedList<>()).stream().forEach(list::add);
        });
        list.stream().forEach(t->{
            if(queue.size() == 10){
                if(queue.peek().time<t.time){
                    queue.poll();
                    queue.offer(t);
                }
            }else{
                queue.offer(t);
            }
        });
        return queue.stream().sorted(Comparator.comparing(TweetDetails::getTime, Comparator.reverseOrder())).map(TweetDetails::getTweet).collect(Collectors.toCollection(LinkedList::new));
    }

    public void follow(int followerId, int followeeId) {
        followersMap.compute(followerId, (now, prev)->{
                Set<Integer> set = prev == null ? new HashSet<>() : prev;
                set.add(followeeId);
                return set;
        });
    }

    public void unfollow(int followerId, int followeeId) {
        followersMap.compute(followerId, (now, prev)->{
            if(prev == null){
                return null;
            }
            prev.remove(followeeId);
            return prev.isEmpty() ? null : prev;
        });
    }

    private class TweetDetails{
        int tweet;
        int time;

        public TweetDetails(int tweet, int time) {
            this.tweet = tweet;
            this.time = time;
        }

        public int getTweet() {
            return tweet;
        }

        public int getTime() {
            return time;
        }
    }
}
