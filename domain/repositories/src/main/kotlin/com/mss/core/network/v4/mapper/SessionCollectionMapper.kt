package com.mss.core.network.v4.mapper

import com.mss.core.domain.repository.SessionRepository
import com.mss.core.domain.repository.SessionRepository.Collection.*
import com.mss.core.network.v4.api.SessionApiV4.SessionCollection
import com.mss.core.utils.Mapper

object SessionCollectionMapper : Mapper<SessionRepository.Collection, SessionCollection> {
    override fun SessionRepository.Collection.map(): SessionCollection = when (this) {
        MostRecent -> SessionCollection.MostRecent
        MostPopular -> SessionCollection.MostPopular
        Forthcoming -> SessionCollection.Forthcoming
    }
}