package cz.airbank.airbanktest.ui.spacex.detail

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cz.airbank.airbanktest.ui.common.StateHost
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun RocketDetailScreen(
    rocketId: String,
    viewModel: RocketDetailViewModel = koinViewModel {
        parametersOf(rocketId)
    },
) {

    val state = viewModel.launchState.collectAsState()
    val rocket = viewModel.detail.collectAsState()

    val context = LocalContext.current

    StateHost(state = state.value, onCancel = { /*TODO*/ }) {
        rocket.value?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.headlineLarge,
                )
                Text(text = it.description)

                it.link?.let { wikipediaLink ->
                    Button(
                        onClick = {
                            context.startActivity(
                                Intent(Intent.ACTION_VIEW, Uri.parse(wikipediaLink))
                            )
                        },
                    ) {
                        Text(text = "Wikipedia")
                    }
                }
            }
        }
    }
}

private fun letAlsoRunApply() {
    val res1 = "A".let {
        "ABC"
        it.count()
    }
    res1 // 1

    val res2 = "A".also {
        "ABC"
        it.count()
    }
    res2 // "A"

    val res3 = "A".run {
        "ABC"
        count()
    }
    res3 // 1

    val res4 = "A".apply {
        "ABC"
        count()
    }
    res4 // "A"

}







