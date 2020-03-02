package com.knoldus.controller


import com.knoldus.models.{Address, Comment, Company, Geo, Parse, Post, User}
import net.liftweb.json.DefaultFormats
import com.knoldus.controller.ReadData.jsonUserData

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

/**
 * ParseData used to parse the json data.
 */
object ParseData extends Parse{

//  def userParseData(jsonData: String): List[User] = {
//
//    implicit val formats: DefaultFormats.type = DefaultFormats
//
//    val parsingData = net.liftweb.json.parse(jsonData)
//
//
//    parsingData.children map { user =>
//
//      val id = (user \ "id").extract[String]
//
//      val name = (user \ "name").extract[String]
//
//      val username = (user \ "username").extract[String]
//      val email = (user \ "email").extract[String]
//      val street = (user \ "address" \ "street").extract[String]
//      val suite = (user \ "address" \ "suite").extract[String]
//      val city = (user \ "address" \ "city").extract[String]
//      val zipcode = (user \ "address" \ "zipcode").extract[String]
//      val lat = (user \ "address" \ "geo" \ "lat").extract[String]
//      val lng = (user \ "address" \ "geo" \ "lng").extract[String]
//
//      val phone = (user \ "phone").extract[String]
//      val website = (user \ "website").extract[String]
//      val companyName = (user \ "company" \ "name").extract[String]
//      val catchPhrase = (user \ "company" \ "catchPhrase").extract[String]
//      val bs = (user \ "company" \ "bs").extract[String]
//      User(id, name, username, email, Address(street, suite, city, zipcode, Geo(lat, lng)), phone, website, Company(companyName, catchPhrase, bs))
//
//    }
//  }
//  /**
//   *userParseData used to parse the comment json data.
//   * @param jsonData take json data in form of string
//   * @return list of Comment case class .
//   */
//  def parseComment(jsonData: String): List[Comment] = {
//    val parsedJsonData = net.liftweb.json.parse(jsonData)
//    parsedJsonData.children map { comment =>
//
//      val postId = (comment \ "postId").extract[String]
//      val id = (comment \ "id").extract[String]
//      val name = (comment \ "name").extract[String]
//      val email = (comment \ "email").extract[String]
//      val body = (comment \ "body").extract[String]
//
//      Comment(postId, id, name, email, body)
//    }
//  }
  /**
   *parseJson used to parse the post json data.
   * @param jsonData take json data in form of string
   * @return list of type T case class .
   */

  def parseJson[T](jsonData: String)(implicit m:Manifest[T]): List[T] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    //parsedJsonData.e
    parsedJsonData.children.map { data =>
data.extract[T]
    }
  }

  implicit val formats: DefaultFormats.type = DefaultFormats
  val parsedJsonUserData: Future[List[User]] = for {
    userData <- ReadData.jsonUserData
    parsedJsonData <- Future(ParseData.parseJson[User](userData))
  } yield parsedJsonData

  val parsedJsonComments: Future[List[Comment]] = for {
    commentsData <- ReadData.jsonCommentsData
    parsedJsonData1 <- Future(ParseData.parseJson[Comment](commentsData))
  } yield parsedJsonData1

  val parsedJsonPosts = for {
    postsData <- ReadData.jsonPostsData
    parsedJsonData <- Future(ParseData.parseJson[Post](postsData))
  } yield parsedJsonData
}
