import com.imthiyas.movietvshowapp.network.ApiClient
import com.imthiyas.movietvshowapp.network.ApiService
import com.imthiyas.movietvshowapp.repository.MovieRepository
import com.imthiyas.movietvshowapp.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Provide ApiService instance
    single<ApiService> { ApiClient.apiService }

    // Provide Repository instance
    single { MovieRepository(get()) }

    // Provide ViewModel instancew
    viewModel { MovieViewModel(get()) }
}