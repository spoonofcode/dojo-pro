import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val authModule = module {
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}