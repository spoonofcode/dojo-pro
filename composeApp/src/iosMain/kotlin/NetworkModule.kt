import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module

@OptIn(ExperimentalForeignApi::class)
private fun createUnsafeOkHttpClient(): HttpClient {
    return HttpClient(Darwin) {
//        engine {
//            this as DarwinClientEngineConfig
////            if (isTestEnvironment) {
//            handleChallenge { session, task, challenge, completionHandler -> }
//            TrustAllChallengeHandler()
////            }
//        }
    }
}

actual val networkModule = module {
    single {
        createUnsafeOkHttpClient()
    }
}