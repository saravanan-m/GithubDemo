package com.githubdemo.model


data class RootElement (
    val url: String = "",
    val repositoryURL: String = "",
    val labelsURL: String = "",
    val commentsURL: String = "",
    val eventsURL: String ="",
    val htmlURL: String ="",
    val id: Long = 0,
    val nodeID: String = "",
    val number: Long = 0,
    val title: String ="",
    val user: User? = null,
    val labels: List<Label>? = null,
    val state: String = "",
    val locked: Boolean = false,
    val assignee: Any? = null,
    val assignees: List<Any?>? = null,
    val milestone: Any? = null,
    val comments: Long = 0,
    val createdAt: String = "",
    val updatedAt: String = "",
    val closedAt: Any? = null,
    val authorAssociation: String = "",
    val body: String = "",
    val pullRequest: PullRequest? = null,
    val loading:Boolean = false
)


data class Label (
    val id: Long,
    val nodeID: String,
    val url: String,
    val name: String,
    val color: String,
    val default: Boolean
)

data class PullRequest (
    val url: String,
    val htmlURL: String,
    val diffURL: String,
    val patchURL: String
)

data class User (
    val login: String,
    val id: Long,
    val nodeID: String,
    val avatarURL: String,
    val gravatarID: String,
    val url: String,
    val htmlURL: String,
    val followersURL: String,
    val followingURL: String,
    val gistsURL: String,
    val starredURL: String,
    val subscriptionsURL: String,
    val organizationsURL: String,
    val reposURL: String,
    val eventsURL: String,
    val receivedEventsURL: String,
    val type: String,
    val siteAdmin: Boolean
)

