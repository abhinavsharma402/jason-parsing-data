package com.knoldus.controller

import com.knoldus.models.TwitterApi
import com.typesafe.config.ConfigFactory
import twitter4j.auth.AccessToken
import twitter4j.{Twitter, TwitterFactory}

object TwitterInstance extends TwitterApi {
  override def getTwitterInstance: Twitter = {

    //    val consumerKey = "e6uS4phTxImI68qlA6h4V3zwR"
    //    val consumerSecret = "M8b4Q3sudgU9mNZgJx1onUlqQYi5h5YCK1GVacjAc8yHDAohFc"
    //    val token = "160922224-AKOoOasbqi3huqT7uyq4Og0Oqlucn8rKeD9IcUvU"
    //    val tokenSecret = "7HgIJUmjOX2AZThvVp7RPWsZwOrW1ffpvkEpjeBSQynnH"

    val config = ConfigFactory.load()
    val twitter: Twitter = new TwitterFactory().getInstance() // Authorising with your Twitter Application credentials
    twitter.setOAuthConsumer(config.getString("consumer.key"),
      config.getString("consumer.secret"))
    twitter.setOAuthAccessToken(new AccessToken(
      config.getString("token.key"),
      config.getString("token.secret")))
    twitter
  }


}
