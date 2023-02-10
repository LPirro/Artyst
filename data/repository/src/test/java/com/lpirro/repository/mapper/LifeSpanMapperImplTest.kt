package com.lpirro.repository.mapper

import com.lpirro.network.model.LifeSpanRemote
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LifeSpanMapperImplTest {

    lateinit var lifeSpanMapper: LifeSpanMapper

    @Before
    fun setup() {
        lifeSpanMapper = LifeSpanMapperImpl()
    }

    @Test
    fun `LifeSpanRemote to LifeSpan Maps Correctly`() {
        val lifeSpanRemote = LifeSpanRemote("12-01-1954", "18-09-2013")
        val result = lifeSpanMapper.mapToDomain(lifeSpanRemote)
        assertEquals(lifeSpanRemote.begin, result.begin)
        assertEquals(lifeSpanRemote.end, result.end)
    }
}
