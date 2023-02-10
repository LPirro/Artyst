package com.lpirro.repository.mapper

import com.lpirro.domain.model.LifeSpan
import com.lpirro.network.model.LifeSpanRemote

class LifeSpanMapperImpl : LifeSpanMapper {
    override fun mapToDomain(lifeSpanRemote: LifeSpanRemote) = LifeSpan(
        begin = lifeSpanRemote.begin,
        end = lifeSpanRemote.end
    )
}
