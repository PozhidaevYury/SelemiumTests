package vk.feed

import org.testng.annotations.Ignore
import vk.FeedSettings
import org.testng.annotations.Test


class FeedTest : FeedSettings() {

    @Test
    fun numberOfPostsIs10() {
        feedPage.checkNumberOfPosts()
    }

    @Test
    fun checkLikeButton() {
        feedPage.clickLikeButton()
    }

    @Ignore
    @Test
    fun loadNewPostsAfterScrolling() {
        feedPage.loadPosts()
    }

    @Test
    fun commentPost() {
        feedPage.editComment("hello")
    }
}