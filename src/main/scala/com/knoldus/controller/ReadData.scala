package com.knoldus.controller

import com.knoldus.models.Read
import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

/**
 * ReadData is used to read the jason data
 */
object ReadData extends Read {
  /**
   * reaJsonData used to read the data from url
   * @param url url of json data
   * @return json data in form of string
   */
  override def readJsonData(url: String): String = {
    val request = new HttpGet(url)
    val client = HttpClientBuilder.create().build()
    val response = client.execute(request)
    val res = IOUtils.toString(response.getEntity.getContent)
    res
  }
  val jsonUserData: Future[String] = Future {
    ReadData.readJsonData("https://jsonplaceholder.typicode.com/users")
  }
  val jsonCommentsData: Future[String] = Future {
    ReadData.readJsonData("https://jsonplaceholder.typicode.com/comments")
  }

  val jsonPostsData: Future[String] = Future {
    ReadData.readJsonData("https://jsonplaceholder.typicode.com/posts")
  }


}
