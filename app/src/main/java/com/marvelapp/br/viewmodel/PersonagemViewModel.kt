package com.marvelapp.br.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.marvelapp.br.sdk.API
import com.marvelapp.br.sdk.paging.PersonagemDataSource
import com.marvelapp.br.sdk.response.Result
import com.marvelapp.br.sdk.response.comic.PersonagemDetalheResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class PersonagemViewModel(private val api: API) : ViewModel() {

    //region Coroutines
    private val viewModelJob = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + viewModelJob)
    //end region

    private var _personagemDetalhe: MutableLiveData<PersonagemDetalheResponse> = MutableLiveData()
    var personagensLiveData: LiveData<PagedList<Result>>
    val personagemDetalheLiveData: LiveData<PersonagemDetalheResponse> = _personagemDetalhe

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(10)
            .setEnablePlaceholders(true)
            .setPrefetchDistance(2)
            .build()

        personagensLiveData = initPagedListBuilder(config).build()
    }

    fun getPersonagens(): LiveData<PagedList<Result>> = personagensLiveData

    fun getPersonagemDetalhe(id: Int) {
        scope.launch {
            try {
                val response = api.getPersonagem(id).await()
                when {
                    response.isSuccessful -> {
                        _personagemDetalhe.postValue(response.body())
                    }
                    else -> {
                        Log.e("PersonagemDataSource", "Deu ruinzao")
                    }
                }
            } catch (e: Exception) {
                Log.e("PersonagemDataSource", "Deu ruinzao")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun initPagedListBuilder(config: PagedList.Config) : LivePagedListBuilder<Int, Result> {
        val dataSourceFactory = object : DataSource.Factory<Int,Result>(){
            override fun create(): DataSource<Int, Result> {
                return PersonagemDataSource(api)
            }
        }
        return LivePagedListBuilder<Int, Result>(dataSourceFactory,config)
    }
}


