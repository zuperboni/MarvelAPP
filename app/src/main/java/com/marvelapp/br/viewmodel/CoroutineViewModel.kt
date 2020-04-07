package com.marvelapp.br.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class CoroutineViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Main

    protected val jobs = ArrayList<Job>()

    infix fun ArrayList<Job>.add(job: Job) {
        this.add(job)
    }

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { if (!it.isCancelled) it.cancel() }
    }
}