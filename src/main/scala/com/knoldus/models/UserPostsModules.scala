package com.knoldus.models

trait Read {
  def readJsonData(url: String): String
}
trait Parse{

  def parseJson[T](jsonData: String)(implicit m:Manifest[T]): List[T]
}

case class User(id: String, name: String, username: String, email: String, address: Address, phone: String, website: String, company: Company)

case class Geo(lat: String, lng: String)

case class Address(street: String, suite: String, city: String, zipcode: String, geo: Geo)

case class Company(name: String, catchPhrase: String, bs: String)

case class Comment(postId: String, id: String, name: String, email: String, body: String)

case class Post(userId: String, id: String, title: String, body: String)

case class UserWithPosts(user: User, posts: List[Post])

case class PostsWithComments(post: Post, comments: List[Comment])

