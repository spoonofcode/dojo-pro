package com.spoonofcode.dojopro.feature.user.details

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.spoonofcode.dojopro.core.ui.BaseScreen
import com.spoonofcode.dojopro.core.ui.compose.Buttons
import com.spoonofcode.dojopro.core.ui.compose.Spacers
import com.spoonofcode.dojopro.core.ui.compose.Texts
import com.spoonofcode.dojopro.core.ui.ext.koinViewModel
import com.spoonofcode.dojopro.resources.Res
import com.spoonofcode.dojopro.resources.add_club_owner_role
import com.spoonofcode.dojopro.resources.add_coach_role
import com.spoonofcode.dojopro.resources.user_details
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

internal class UserDetailsScreen(
    override val screenTopAppBarTitle: StringResource = Res.string.user_details,
    val userId: Int,
) : BaseScreen<UserDetailsViewModel, UserDetailsViewState>() {

    @Composable
    override fun provideViewModel() = koinViewModel<UserDetailsViewModel>()

    @Composable
    override fun provideContentView(
        viewModel: UserDetailsViewModel,
        viewState: UserDetailsViewState
    ): @Composable ColumnScope.() -> Unit {

        LaunchedEffect(Unit) {
            viewModel.initView(userId = userId)
        }

        return ContentView(
            viewState = viewState,
            addCoachRole = { viewModel.addCoachRole() },
            addClubOwnerRole = { viewModel.addClubOwnerRole() },
        )
    }

    @Composable
    internal fun ContentView(
        viewState: UserDetailsViewState,
        addCoachRole: () -> Unit,
        addClubOwnerRole: () -> Unit,
    ): @Composable (ColumnScope.() -> Unit) {
        return {
            Texts.HS(viewState.user!!.fullName)
            Spacers.VerticalBetweenFields()

            Texts.HS(viewState.user!!.email)
            Spacers.VerticalBetweenFields()

            Texts.HS(viewState.roles.toString())
            Spacers.VerticalBetweenFields()

            if (viewState.isVisibleAddCoachRoleButton) {
                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.add_coach_role),
                    onClick = addCoachRole
                )
                Spacers.VerticalBetweenFields()
            }

            if (viewState.isVisibleAddClubOwnerRoleButton) {
                Buttons.PrimaryButton(
                    text = stringResource(resource = Res.string.add_club_owner_role),
                    onClick = addClubOwnerRole
                )
                Spacers.VerticalBetweenFields()
            }
        }
    }
}