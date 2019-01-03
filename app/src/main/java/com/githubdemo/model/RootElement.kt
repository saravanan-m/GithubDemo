package com.githubdemo.model


data class RootElement(
    val url: String? = null,
    val id: Long? = null,
    val nodeID: String? = null,
    val htmlURL: String? = null,
    val diffURL: String? = null,
    val patchURL: String? = null,
    val issueURL: String? = null,
    val number: Long? = null,
    val state: String? = null,
    val locked: Boolean? = null,
    val title: String? = null,
    val user: User? = null,
    val body: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val closedAt: Any? = null,
    val mergedAt: Any? = null,
    val mergeCommitSHA: String? = null,
    val assignee: Any? = null,
    val assignees: List<Any?>?= null,
    val requestedReviewers: List<Any?>? = null,
    val requestedTeams: List<Any?>? = null,
    val labels: List<Label>? = null,
    val milestone: Milestone? = null,
    val commitsURL: String? = null,
    val reviewCommentsURL: String? = null,
    val reviewCommentURL: String? = null,
    val commentsURL: String? = null,
    val statusesURL: String? = null,
    val head: Base? = null,
    val base: Base? = null,
    val links: Links? = null,
    val authorAssociation: String? = null,
    val loading:Boolean? = false
)

data class Base(
    val label: String,
    val ref: String,
    val sha: String,
    val user: User,
    val repo: Repo
)

data class Repo(
    val id: Long,
    val nodeID: String,
    val name: RepoName,
    val fullName: String,
    val private: Boolean,
    val owner: User,
    val htmlURL: String,
    val description: Description,
    val fork: Boolean,
    val url: String,
    val forksURL: String,
    val keysURL: String,
    val collaboratorsURL: String,
    val teamsURL: String,
    val hooksURL: String,
    val issueEventsURL: String,
    val eventsURL: String,
    val assigneesURL: String,
    val branchesURL: String,
    val tagsURL: String,
    val blobsURL: String,
    val gitTagsURL: String,
    val gitRefsURL: String,
    val treesURL: String,
    val statusesURL: String,
    val languagesURL: String,
    val stargazersURL: String,
    val contributorsURL: String,
    val subscribersURL: String,
    val subscriptionURL: String,
    val commitsURL: String,
    val gitCommitsURL: String,
    val commentsURL: String,
    val issueCommentURL: String,
    val contentsURL: String,
    val compareURL: String,
    val mergesURL: String,
    val archiveURL: String,
    val downloadsURL: String,
    val issuesURL: String,
    val pullsURL: String,
    val milestonesURL: String,
    val notificationsURL: String,
    val labelsURL: String,
    val releasesURL: String,
    val deploymentsURL: String,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val gitURL: String,
    val sshURL: String,
    val cloneURL: String,
    val svnURL: String,
    val homepage: String,
    val size: Long,
    val stargazersCount: Long,
    val watchersCount: Long,
    val language: Language,
    val hasIssues: Boolean,
    val hasProjects: Boolean,
    val hasDownloads: Boolean,
    val hasWiki: Boolean,
    val hasPages: Boolean,
    val forksCount: Long,
    val mirrorURL: Any? = null,
    val archived: Boolean,
    val openIssuesCount: Long,
    val license: License,
    val forks: Long,
    val openIssues: Long,
    val watchers: Long,
    val defaultBranch: DefaultBranch
)

enum class DefaultBranch {
    Master
}

enum class Description {
    TypeSafeHTTPClientForAndroidAndJavaBySquareInc,
    TypeSafeRESTClientForAndroidAndJavaBySquareInc
}

enum class Language {
    Java
}

data class License(
    val key: Key,
    val name: LicenseName,
    val spdxID: SpdxID,
    val url: String,
    val nodeID: NodeID
)

enum class Key {
    Apache20
}

enum class LicenseName {
    ApacheLicense20
}

enum class NodeID {
    MDc6TGljZW5ZZTI
}

enum class SpdxID {
    Apache20
}

enum class RepoName {
    Retrofit
}

data class User(
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


data class Label(
    val id: Long,
    val nodeID: String,
    val url: String,
    val name: String,
    val color: String,
    val default: Boolean
)

data class Links(
    val self: Comments,
    val html: Comments,
    val issue: Comments,
    val comments: Comments,
    val reviewComments: Comments,
    val reviewComment: Comments,
    val commits: Comments,
    val statuses: Comments
)

data class Comments(
    val href: String
)

data class Milestone(
    val url: String,
    val htmlURL: String,
    val labelsURL: String,
    val id: Long,
    val nodeID: String,
    val number: Long,
    val title: String,
    val description: String,
    val creator: User,
    val openIssues: Long,
    val closedIssues: Long,
    val state: String,
    val createdAt: String,
    val updatedAt: String,
    val dueOn: String,
    val closedAt: String
)
