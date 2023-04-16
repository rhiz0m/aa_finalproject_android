package com.taokyone.aa_finalproject_android.viewModel

import androidx.lifecycle.ViewModel
import com.taokyone.aa_finalproject_android.model.NasaImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NasaViewModel : ViewModel() {

    //Setup mutableSTateFlow
    private  val _uiState = MutableStateFlow(NasaImage())
    val uiState: StateFlow<NasaImage> = _uiState.asStateFlow()

    fun nasaState() {

        _uiState.update { state ->
            state.copy(
                hdurl = state.hdurl,

            )
        }
    }

}