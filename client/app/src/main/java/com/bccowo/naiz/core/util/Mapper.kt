package com.bccowo.naiz.core.util

import com.bccowo.naiz.core.data.source.local.entities.CandiEntity
import com.bccowo.naiz.domain.model.Candi

object Mapper {
    fun candiModelToEntity(candi: Candi): CandiEntity {
        return CandiEntity(candi.id, candi.name, candi.address, candi.image, candi.rating)
    }

    fun candiEntityListToModel(candiList: List<CandiEntity>): List<Candi> {
        return candiList.map {
            Candi(it.id, it.name, it.address, it.image, it.rating)
        }
    }
}