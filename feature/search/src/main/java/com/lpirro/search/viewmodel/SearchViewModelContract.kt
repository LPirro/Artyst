package com.lpirro.search.viewmodel

import kotlinx.coroutines.Job

interface SearchViewModelContract {
    fun search(searchQuery: String): Job
}
