package core.ui.theme


import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_bold
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_extra_bold
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_extra_light
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_light
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_medium
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_regular
import com.spoonofcode.dojopro.resources.plus_jakarta_sans_semi_bold

import org.jetbrains.compose.resources.Font

@Composable
fun bodyFontFamily() = FontFamily(
    Font(resource = Res.font.plus_jakarta_sans_extra_light, weight = FontWeight.ExtraLight),
    Font(resource = Res.font.plus_jakarta_sans_light, weight = FontWeight.Light),
    Font(resource = Res.font.plus_jakarta_sans_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.plus_jakarta_sans_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.plus_jakarta_sans_semi_bold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.plus_jakarta_sans_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.plus_jakarta_sans_extra_bold, weight = FontWeight.ExtraBold),
)
@Composable
fun displayFontFamily() = FontFamily(
    Font(resource = Res.font.plus_jakarta_sans_extra_light, weight = FontWeight.ExtraLight),
    Font(resource = Res.font.plus_jakarta_sans_light, weight = FontWeight.Light),
    Font(resource = Res.font.plus_jakarta_sans_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.plus_jakarta_sans_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.plus_jakarta_sans_semi_bold, weight = FontWeight.SemiBold),
    Font(resource = Res.font.plus_jakarta_sans_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.plus_jakarta_sans_extra_bold, weight = FontWeight.ExtraBold),)

// Default Material 3 typography values
val baseline = Typography()

@Composable
fun appTypography() = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily()),
    displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily()),
    displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily()),
    headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily()),
    headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily()),
    headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily()),
    titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily()),
    titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily()),
    titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily()),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily()),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily()),
    bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily()),
    labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily()),
    labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily()),
    labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily()),
)

