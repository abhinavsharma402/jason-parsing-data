package com.knoldus.controller

import com.knoldus.models.TweetsRetrieve
import twitter4j.{Query, Status, Twitter}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.collection.JavaConverters._

class RetrieveTweets extends TweetsRetrieve {

  override def getTweetsOfHashTag: Future[List[Status]] = {
    val twitter: Twitter = TwitterInstance.getTwitterInstance

    val hashTag = new Query("#Delhi")

    Future {
      twitter.search(hashTag).getTweets.asScala.toList
    }

  }
}
