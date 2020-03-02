package com.knoldus.controller

import com.knoldus.models._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Utilities {


  val usersList = ParseData.parsedJsonUserData
  val postsList = ParseData.parsedJsonPosts
  val commentsList = ParseData.parsedJsonComments

  /**
   * userPostUtility used to give user with their posts in form of lists
   * @param post take list of posts
   * @param user take list of users
   * @return
   */
  def userPostUtility(post: Future[List[Post]], user: Future[List[User]]): Future[List[UserWithPosts]] = {
    def userWithPostsUtility(posts: List[Post], users: List[User]): List[UserWithPosts] = {

      users.map(users => UserWithPosts(users, posts.filter(users.id == _.userId)))
    }

    for {
      listofuser <- user
      listofpost <- post

    } yield userWithPostsUtility(listofpost, listofuser)
  }
  /**
   * postCommentUtility used to give post with their comments in form of lists
   * @param post take list of posts
   * @param comment take list of comments
   * @return
   */
  def postCommentUtility(post: Future[List[Post]], comment: Future[List[Comment]]): Future[List[PostsWithComments]] = {
    def postsWithCommentsUtility(posts: List[Post], comments: List[Comment]): List[PostsWithComments] = {
      posts.map(posts => PostsWithComments(posts, comments.filter(posts.id == _.postId)))
    }

    for {
      listOfComments <- comment
      listOfPosts <- post

    } yield postsWithCommentsUtility(listOfPosts, listOfComments)

  }

  val userWithPostsList: Future[List[UserWithPosts]] = Utilities.userPostUtility(postsList, usersList)
  val postsWithCommentsList: Future[List[PostsWithComments]] = Utilities.postCommentUtility(postsList, commentsList)

  /**
   * maxPostByUser gives maximum posts done bt which user
   * @param userWithPosts take list of user with their posts
   * @return user with maximum post
   */
  def maxPostByUser(userWithPosts: Future[List[UserWithPosts]]): Future[(User, Int)] = {
    val postCount = for {postsByUser <- userWithPosts

                         } yield postsByUser.map(x => (x.user, x.posts.length))

    postCount.map(x => (x.reduceLeft((first, second) => if (first._2 > second._2) first else second)))
  }

  /**
   * maxCommentonPost used to find post has maximum comments
   * @param postWithComment take posts with their comments
   * @return post has maximum comments
   */
  def maxCommentOnPost(postWithComment: Future[List[PostsWithComments]]): Future[(Post, Int)] = {
    val commentCount = for {commentsOnPost <- postWithComment

                            } yield commentsOnPost.map(x => (x.post, x.comments.length))

    commentCount.map(x => (x.reduceLeft((first, second) => if (first._2 > second._2) first else second)))
  }


}






