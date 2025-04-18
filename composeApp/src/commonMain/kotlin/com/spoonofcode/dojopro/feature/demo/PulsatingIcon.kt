package com.spoonofcode.dojopro.feature.demo

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_training
import org.jetbrains.compose.resources.painterResource

/**
 * A pulsating version of your app icon, suitable for a loading overlay.
 *
 * @param iconRes   Drawable resource for the app icon (e.g. R.drawable.ic_launcher_foreground)
 * @param sizeDp    Target “resting” size of the icon in dp
 * @param duration  One‑way travel time (ms) for the pulse before reversing
 */
@Composable
fun PulsatingIcon(
    iconRes: Int? = null,
    sizeDp: Int = 72,
    duration: Int = 650
) {
    // 1️⃣  Set up an endless animation that repeats forward → reverse
    val infiniteTransition = rememberInfiniteTransition(label = "pulseTransition")

    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue  = 1.15f,
        animationSpec = infiniteRepeatable(
            animation    = tween(durationMillis = duration, easing = FastOutSlowInEasing),
            repeatMode   = RepeatMode.Reverse
        ),
        label = "scaleAnim"
    )

    val alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue  = 0.6f,
        animationSpec = infiniteRepeatable(
            animation    = tween(durationMillis = duration, easing = FastOutSlowInEasing),
            repeatMode   = RepeatMode.Reverse
        ),
        label = "alphaAnim"
    )

    // 2️⃣  Draw the icon with the animated scale/alpha
    Image(
        painter = painterResource(resource = Res.drawable.dojo_training),
        contentDescription = null,            // decorative
        modifier = Modifier
            .size(sizeDp.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .alpha(alpha)
    )
}

/**
 * Drop‑in full‑screen loading overlay that centers the pulsating icon.
 */
@Composable
fun AppLoadingOverlay(iconRes: Int) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PulsatingIcon(iconRes = iconRes)
    }
}