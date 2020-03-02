package com.knoldus.controller


import twitter4j.Status
import scala.concurrent.duration._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}

class TweetsAnalyse {

  def getTweetsCount(tweets: Future[List[Status]]): Future[Int] = {

    tweets.map(tweet => tweet.length)

  }

  def getAverageLikesPerTweet(tweets: List[Status]): Double = {
    val totalLikes = tweets.foldLeft(0)((acc, ele) => acc + ele.getFavoriteCount)
    totalLikes / tweets.length
  }

  def getReTweetOnTweet(tweets: List[Status]): List[(Long, Int)] = {
    tweets.map(post => (post.getId, post.getRetweetCount))
  }
}


object AppDriver extends App {
  val tweetsAnalyseObj = new TweetsAnalyse
  val tweetsRetrieveobj = new RetrieveTweets
  val tweets = tweetsRetrieveobj.getTweetsOfHashTag

  tweetsAnalyseObj.getTweetsCount(tweets)


  val averageLikes = (tweets.flatMap(tweet => Future(tweetsAnalyseObj.getAverageLikesPerTweet(tweet))))

  val reTweets = tweets.flatMap(tweet => Future(tweetsAnalyseObj.getReTweetOnTweet(tweet)))
  val miliseconds=8000
  Thread.sleep(miliseconds)
  println(averageLikes)
  println(reTweets)
}
