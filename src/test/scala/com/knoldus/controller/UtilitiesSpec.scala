package com.knoldus.controller

import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

class UtilitiesSpec extends AsyncFlatSpec with BeforeAndAfterAll{
  "Max Finder" should "eventually find user with maximum post" in {
    val futureFind = Utilities.maxPostByUser( Utilities.userWithPostsList)

    futureFind map (find => assert(find._1.name == "Clementina DuBuque" && find._2 ==  10))
  }

  it should "eventually find user with maximum comments" in {
    val futureFindComment=Utilities.maxCommentOnPost( Utilities.postsWithCommentsList)
    futureFindComment map { find => assert(find._2 == 5) }
  }
}
