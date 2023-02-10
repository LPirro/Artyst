package com.lpirro.network

import com.lpirro.network.model.ArtistRemote
import com.lpirro.network.model.PaginatedResultRemote
import com.lpirro.network.model.ReleaseGroupsRemote
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MusicBrainzApiService {

    @GET("artist")
    suspend fun searchArtists(
        @Query("query") searchQuery: String?,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("fmt") responseFormat: String = "json"
    ): PaginatedResultRemote<List<ArtistRemote>>

    @GET("artist/{id}")
    suspend fun getArtist(
        @Path("id") id: String,
        @Query("inc") include: String = "url-rels",
        @Query("fmt") responseFormat: String = "json"
    ): ArtistRemote

    @GET("release-group")
    suspend fun releasedAlbums(
        @Query("artist") artistId: String,
        @Query("type") include: String = "album",
        @Query("fmt") responseFormat: String = "json"
    ): ReleaseGroupsRemote
}
