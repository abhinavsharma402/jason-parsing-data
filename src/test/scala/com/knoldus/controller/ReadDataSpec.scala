package com.knoldus.controller
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

import scala.io.Source
class ReadDataSpec extends AsyncFlatSpec with BeforeAndAfterAll{
  it should "fetch the user json data from the specified url" in {
    val actualString = ReadData.readJsonData("https://jsonplaceholder.typicode.com/users")
    val expectedString = Source.fromFile("./src/test/resources/JsonDataUsers").mkString
    assert(expectedString == actualString)
  }
  it should "fetch the comments json data from the specified url" in {
    val actualString = ReadData.readJsonData("https://jsonplaceholder.typicode.com/comments")
    val expectedString = Source.fromFile("./src/test/resources/JsonDataComments").mkString
    assert(expectedString == actualString)
  }
  it should "fetch the posts json data from the specified url" in {
    val actualString = ReadData.readJsonData("https://jsonplaceholder.typicode.com/posts")
    val expectedString = Source.fromFile("./src/test/resources/JsonDataPosts").mkString
    assert(expectedString == actualString)
  }
}
