package com.lpirro.network.model

data class PaginatedResultRemote<T>(val offset: Int, val artists: T)
