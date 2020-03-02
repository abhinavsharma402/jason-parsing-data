package com.knoldus.controller
import com.knoldus.models.{Comment, Post, User}
import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

import scala.io.Source

class ParseDataSpec  extends AsyncFlatSpec with BeforeAndAfterAll{
  it should "eventually fetch the json data  of all the comments from the  json string " in {
    val jsonDataString = Source.fromFile("./src/test/resources/JsonDataComments").getLines.mkString
    val actualPostsList = ParseData.parseJson[Comment](jsonDataString)
    val expectedCommentName = "id labore ex et quam laborum"
    assert(actualPostsList(0).name == expectedCommentName)
  }
  it should "eventually fetch the json data  of all the posts from the  json string " in {
    val jsonDataString = Source.fromFile("./src/test/resources/JsonDataPosts").getLines.mkString
    val actualPostsList = ParseData.parseJson[Post](jsonDataString)
    val expectedFirstPostId = "1"
    assert(actualPostsList(0).id == expectedFirstPostId)
  }
  it should "eventually fetch the json data  of all the users from the  json string " in {
    val jsonDataString = Source.fromFile("./src/test/resources/JsonDataUsers").getLines.mkString
    val actualPostsList = ParseData.parseJson[User](jsonDataString)
    val expectedUserName="Leanne Graham"
    assert(actualPostsList(0).name == expectedUserName)
  }

}
