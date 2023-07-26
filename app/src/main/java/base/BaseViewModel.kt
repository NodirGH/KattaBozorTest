package base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


abstract class BaseViewModel : ViewModel() { //TODO hande all uncomment
//    val errorOther = SingleLiveData<Throwable>()
//    var loading = LiveEvent<Boolean>() // changed here from MutableLiveData to LiveEvent
    val handler = CoroutineExceptionHandler { _, exception ->
        errorProcess(exception)
    }

    val vmScope = viewModelScope + handler + Dispatchers.IO

    fun errorProcess(throwable: Throwable, f: ((t: Throwable) -> Unit)? = null) {
        viewModelScope.launch {
//            loading.postValue(false)
            throwable.printStackTrace()
//            Timber.d(throwable.message)
//            errorOther.postValue(SingleEvent(throwable))
        }
    }

    fun CoroutineScope.loadingLaunch(suspendCall: suspend () -> Unit): Job {
//        loading.postValue(true)
        return launch {
            suspendCall.invoke()
//            loading.postValue(false)
        }
    }

    fun <T> Flow<T>.handleErrors(): Flow<T> = flow {
        try {
            collect { value -> emit(value) }
        } catch (e: Throwable) {
            errorProcess(e)
        }
    }
}