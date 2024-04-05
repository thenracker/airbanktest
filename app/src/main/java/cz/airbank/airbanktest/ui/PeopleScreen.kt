package cz.airbank.airbanktest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun PeopleScreen() {

    val people = remember {
        mutableStateOf(
            generatePeople(5)
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Lidé",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 16.dp),
            )
            people.value.forEach {
                Text(
                    text = it.name,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
        Button(
            modifier = Modifier
                .padding(all = 16.dp)
                //.fillMaxWidth()
                .align(Alignment.BottomEnd),
            onClick = {
                val newPeople = generatePeople(5)
                val oldPeople = people.value
                people.value = oldPeople + newPeople
            }
        ) {
            Text(
                text = "Přidat",
            )
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Ikona přidat",
            )
        }
    }
}


data class Person(
    val name: String,
    val surname: String,
    val age: Int = 0,
)

private val names = listOf("Karel", "Petr", "Lenka", "Alena", "Bára", "Natálie")
private val surnames = arrayOf("Weissar", "Novák", "Dvořák", "Synek", "Komoráš")

private fun generatePeople(howMany: Int): List<Person> {
    val people = mutableListOf<Person>()
    repeat(howMany) {
        people.add(
            Person(
                name = names.random(),
                surname = surnames.random(),
            )
        )
    }
    return people
}