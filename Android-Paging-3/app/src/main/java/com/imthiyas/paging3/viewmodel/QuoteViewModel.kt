package com.imthiyas.paging3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.imthiyas.paging3.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class QuoteViewModel @Inject constructor(val quoteRepository: QuoteRepository) : ViewModel() {
    val quoteList = quoteRepository.getQuotes().cachedIn(viewModelScope)
}