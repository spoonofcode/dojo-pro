import org.koin.dsl.bind
import org.koin.dsl.module
import network.NetworkConfig

data class NetworkConfigAndroid(
    override val host: String = "10.0.2.2",
    override val port: String = "8443",
    override val baseUrl: String = "https://$host:$port",
) : NetworkConfig

actual val networkModule = module {
    single { NetworkConfigAndroid() } bind NetworkConfig::class
}

