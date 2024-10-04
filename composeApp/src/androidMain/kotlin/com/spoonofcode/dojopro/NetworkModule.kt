import com.spoonofcode.dojopro.AllCertsTrustManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import okhttp3.OkHttpClient
import org.koin.dsl.module
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.X509TrustManager

private fun createUnsafeOkHttpClient(): OkHttpClient {
    // Create a trust manager that does not validate certificate chains
    val trustAllCerts = arrayOf(AllCertsTrustManager())

    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, SecureRandom())

    // Create an ssl socket factory with our all-trusting manager
    val sslSocketFactory = sslContext.socketFactory

    return OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .build()
}

actual val networkModule = module {
    single { createUnsafeOkHttpClient() }
    single {
        HttpClient {
            engine {
                this as OkHttpConfig
                preconfigured = get<OkHttpClient>()
            }
            install(ContentNegotiation) {
                json()
            }
        }
    }
}

