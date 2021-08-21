package com.bccowo.naiz.core.data.source.local

import com.bccowo.naiz.core.data.source.local.entities.CandiEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDataSource(private val naizDao: NaizDao) {
    suspend fun insertBookmark(candi: CandiEntity) {
        withContext(Dispatchers.IO) {
            naizDao.insertBookmark(candi)
        }
    }

    suspend fun deleteBookmarkedCandi(candi: CandiEntity) {
        withContext(Dispatchers.IO) {
            naizDao.deleteBookmark(candi)
        }
    }

    suspend fun deleteAllBookmarks() {
        withContext(Dispatchers.IO) {
            naizDao.deleteAllBookmark()
        }
    }

    fun getAllBookmarks() = naizDao.getBookmarkedCandi()
    fun checkCandiBookmarked(candiId: Int) = naizDao.checkCandiBookmarked(candiId)

}