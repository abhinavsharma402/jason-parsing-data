package com.knoldus.models



import twitter4j.{ Status, Twitter}

import scala.concurrent.Future

trait TwitterApi {
  def getTwitterInstance: Twitter
}

trait TweetsRetrieve {

  def getTweetsOfHashTag: Future[List[Status]]

}
