package com.picpay.desafio.android.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.UserRepository
import com.picpay.desafio.android.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>>
        get() = _users


    fun fetchUsers() {
        GlobalScope.launch(Dispatchers.Unconfined) {
            _users.postValue(Resource.loading(null))
            userRepository.getUsers().let {
                if (it.isSuccessful) {
                    _users.postValue(Resource.success(it.body()))
                } else _users.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }
}