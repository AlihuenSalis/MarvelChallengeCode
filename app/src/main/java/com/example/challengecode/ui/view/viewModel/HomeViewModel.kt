package com.example.challengecode.ui.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengecode.domain.model.character.CharacterResponse
import com.example.challengecode.domain.usesCase.GetCharacterListUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUC: GetCharacterListUC
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val characterList: MutableLiveData<CharacterResponse> = MutableLiveData()
    val responseError: MutableLiveData<Boolean> = MutableLiveData()

    fun getCharacters(hash: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val response = getCharactersUC(hash)

            response.enqueue(object :Callback<CharacterResponse>{
                override fun onResponse(call: Call<CharacterResponse>, response: Response<CharacterResponse>) {
                    println("RESPONSE OK= $response")
                    characterList.postValue(response.body())
                    isLoading.postValue(false)
                }

                override fun onFailure(call: Call<CharacterResponse>, t: Throwable) {
                    println("RESPONSE ERROR = ${t.message}")
                    responseError.postValue(false)
                    isLoading.postValue(false)
                }

            })
        }
    }
}