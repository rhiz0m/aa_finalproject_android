package com.taokyone.aa_finalproject_android.viewModel

import androidx.lifecycle.ViewModel
import com.taokyone.aa_finalproject_android.model.apiData.Quotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class QuotesViewModel : ViewModel() {

    //Setup mutableSTateFlow
    private  val _uiState = MutableStateFlow(Quotes())
    val uiState: StateFlow<Quotes> = _uiState.asStateFlow()

    fun quotesState() {

        _uiState.update { state ->
            state.copy(
                q = state.q,
                a = state.a

                )
        }
    }

}