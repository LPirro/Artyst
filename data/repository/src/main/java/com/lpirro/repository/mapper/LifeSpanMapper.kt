package com.lpirro.repository.mapper

import com.lpirro.domain.model.LifeSpan
import com.lpirro.network.model.LifeSpanRemote

interface LifeSpanMapper {
    fun mapToDomain(lifeSpanRemote: LifeSpanRemote): LifeSpan
}
