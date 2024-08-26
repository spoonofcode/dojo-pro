package core.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.dojo_room
import core.ext.formatedLocalDateTime
import core.ui.Dimens
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class CarouselSportEventItem(
    // TODO Now set static image, later change to loaded image from backend
    val imageResId: DrawableResource = Res.drawable.dojo_room,
    val sportEventId: Int,
    val title: String,
    val startEventDateTime: LocalDateTime,
)

object Carousels {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomCarousel(
        title: String,
        items: List<CarouselSportEventItem>,
        onItemClick: (sportEventId: Int) -> Unit,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Texts.HS(
                text = title,
                modifier = Modifier.padding(start = Dimens.screenPadding),
            )
            Spacers.VerticalBetweenFields()
            HorizontalUncontainedCarousel(
                state = rememberCarouselState { items.count() },
                modifier = Modifier.height(221.dp).fillMaxWidth(),
                itemWidth = 186.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) { i ->
                val item = items[i]
                ElevatedCard(
                    modifier = Modifier.clickable {
                        onItemClick(item.sportEventId)
                    },
                    elevation = CardDefaults.elevatedCardElevation(defaultElevation = 12.dp),
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxWidth(),
                            painter = painterResource(resource = item.imageResId),
                            contentDescription = item.title,
                            contentScale = ContentScale.Crop,
                        )
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Texts.BL(
                                text = item.startEventDateTime.formatedLocalDateTime(),
                            )
                            Spacers.VerticalBetweenFields()
                            Texts.BL(
                                text = item.title,
                            )
                        }
                    }
                }
            }
            Spacers.VerticalBetweenFields()
        }
    }
}

