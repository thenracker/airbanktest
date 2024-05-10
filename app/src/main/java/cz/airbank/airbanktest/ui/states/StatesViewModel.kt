package cz.airbank.airbanktest.ui.states

import cz.airbank.airbanktest.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random

class StatesViewModel : BaseViewModel() {

//    init {
//        performAction()
//    }

    fun performAction() {
        launch {
            delay(2000)
            if (Random.nextBoolean()) {
                throw Exception("Ukončeno spojení")
            }
        }
    }
}