package com.bccowo.naiz.core.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bccowo.naiz.core.data.source.local.entities.CandiEntity

@Dao
interface NaizDao {
    @Insert
    fun insertBookmark(candi: CandiEntity)

    @Query("select * from candi_bookmarks")
    fun getBookmarkedCandi(): LiveData<List<CandiEntity>>

    @Query("select count(*) > 0 from candi_bookmarks where id = :id")
    fun checkCandiBookmarked(id: Int): LiveData<Boolean>

    @Delete
    fun deleteBookmark(candiEntity: CandiEntity)

    @Query("delete from candi_bookmarks")
    fun deleteAllBookmark()
}