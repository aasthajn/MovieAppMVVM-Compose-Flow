package com.app.movieapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.movieapp.core.NetworkResponseState
import com.app.movieapp.domain.entity.Movie
import com.app.movieapp.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

const val STOP_TIMEOUT_WHILE_SUBSCRIBED = 5000L
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val _searchMovieState = MutableStateFlow<List<Movie>>(emptyList())

    val searchMovieState get() = _searchMovieState
    var searchQuery by mutableStateOf("")
        private set


    private val _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState


    /*val uiState = _searchMovieState.value.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_WHILE_SUBSCRIBED),
        initialValue = MovieUiState.Empty,
    )*/

    val text: LiveData<String> = _text

   /* val searchResults: StateFlow<List<Movie>> =
        snapshotFlow { searchQuery }
            .combine(get) { searchQuery, movies ->
                when {
                    searchQuery.isNotEmpty() -> movies.filter { movie ->
                        movie.name.contains(searchQuery, ignoreCase = true)
                    }
                    else -> movies
                }
            }.stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5_000)
            )*/
    init {
        getMovies()
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
        getSearchedMovies(searchQuery)
    }
    private fun getMovies() {
        viewModelScope.launch {
            repository.getMovieList().cachedIn(viewModelScope)
                .distinctUntilChanged().collect {
                _moviesState.value = it }
        }
    }

   /* private fun getSearchMovies(query: String) {
        viewModelScope.launch {
            repository.searchMovie(query)
                .distinctUntilChanged().collect {
                    _searchMovieState.value = it }
        }
    }*/

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun getSearchedMovies(query:String) {
        viewModelScope.launch {
            flowOf(searchQuery).debounce(300)
                .filter {
                    it.trim().isEmpty().not()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    repository.searchMovie(query)
                }.collect {
                    if (it is NetworkResponseState.Success){
                        it.result
                        _searchMovieState.value = it.result
                        //Timber.e(" data ${it.data.totalPages}")
                    }

                }
        }

    }
}
