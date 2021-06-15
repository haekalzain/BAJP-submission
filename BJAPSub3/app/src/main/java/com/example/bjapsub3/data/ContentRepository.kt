package com.example.bjapsub3.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.bjapsub3.data.source.local.LocalDataSource
import com.example.bjapsub3.data.source.local.entity.ContentEntity
import com.example.bjapsub3.data.source.remote.ApiResponse
import com.example.bjapsub3.data.source.remote.RemoteDataSource
import com.example.bjapsub3.data.source.remote.response.ContentResponse
import com.example.bjapsub3.util.AppExecutors
import com.example.bjapsub3.vo.Resource

class ContentRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ContentDataSource {

    companion object {
        @Volatile
        private var instance: ContentRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): ContentRepository =
            instance ?: synchronized(this) {
                instance ?: ContentRepository(
                    remoteData,
                    localDataSource,
                    appExecutors
                ).apply { instance = this }
            }
    }


    override fun getAllContentByType(contentType: String): LiveData<Resource<PagedList<ContentEntity>>> {
        return object :
            NetworkBoundResource<PagedList<ContentEntity>, List<ContentResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<ContentEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getAllContentByType(contentType),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<ContentEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<ContentResponse>>> =
                remoteDataSource.getAllContent(contentType)


            public override fun saveCallResult(courseResponses: List<ContentResponse>) {
                val moviesList = ArrayList<ContentEntity>()
                for (response in courseResponses) {
                    val content = ContentEntity(
                        response.id,
                        response.title,
                        response.overview,
                        response.released,
                        response.genre,
                        response.duration,
                        false,
                        response.imagePath,
                        response.typeContent
                    )
                    moviesList.add(content)
                }

                localDataSource.insertContent(moviesList)
            }
        }.asLiveData()
    }

    override fun getFavoritedContentByType(contentType: String): LiveData<PagedList<ContentEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(
            localDataSource.getFavoritedContentsByType(contentType),
            config
        ).build()
    }

    override fun getContentWithIdAndType(
        contentId: String,
        contentType: String
    ): LiveData<Resource<ContentEntity>> {
        return object :
            NetworkBoundResource<ContentEntity, List<ContentResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<ContentEntity> =
                localDataSource.getContentById(contentId)

            override fun shouldFetch(contentEntity: ContentEntity?): Boolean =
                contentEntity == null

            override fun createCall(): LiveData<ApiResponse<List<ContentResponse>>> =
                remoteDataSource.getAllContent(contentType)

            override fun saveCallResult(contentResponses: List<ContentResponse>) {
                val moduleList = ArrayList<ContentEntity>()
                for (response in contentResponses) {
                    if (response.id == contentId) {
                        val course = ContentEntity(
                            response.id,
                            response.title,
                            response.overview,
                            response.released,
                            response.genre,
                            response.duration,
                            false,
                            response.imagePath,
                            response.typeContent
                        )
                        moduleList.add(course)
                    }
                }
                localDataSource.insertContent(moduleList)
            }
        }.asLiveData()
    }

    override fun setContentFavorite(course: ContentEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setContentFavorite(course, state) }

}