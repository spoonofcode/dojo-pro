package core.ui.ext

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun CoroutineScope.launchWithProgress(
    onProgress: (Boolean) -> Unit,
    block: suspend CoroutineScope.() -> Unit,
): Job = launch {
    onProgress(true)
    try {
        block.invoke(this)
    } finally {
        onProgress(false)
    }
}