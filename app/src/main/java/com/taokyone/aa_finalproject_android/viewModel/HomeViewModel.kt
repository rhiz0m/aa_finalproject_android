package com.taokyone.aa_finalproject_android.viewModel

import androidx.lifecycle.ViewModel
import com.taokyone.aa_finalproject_android.model.Date
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.LocalTime


class HomeViewModel: ViewModel() {

    //Setup mutableSTateFlow
    private val _uiState = MutableStateFlow(Date(LocalDate.now(), LocalTime.now()))
    val uiState: StateFlow<Date> = _uiState.asStateFlow()
    fun add() {

        _uiState.update { state ->
            state.copy(
                time = LocalTime.now(),
                date = LocalDate.now(),
            )
        }
    }
}