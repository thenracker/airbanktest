package cz.airbank.airbanktest.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = "Lidé",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(vertical = 16.dp),
            )
            people.value.forEachIndexed { index, person ->

                // TODO po kliku na přidat
                // - dialog s 2 vstupními polemi (jméno, přijmen)
                // - validace na to, aby bylo vyplněno

                PersonCard(
                    person = person,
                    canMoveUp = index > 0,
                    onUpdate = { updatedPerson ->
                        val newPeople = people.value.toMutableList()
                        // newPeople.set(index, updatedPerson)
                        newPeople[index] = updatedPerson
                        people.value = newPeople
                    },
                    onMoveUp = {
                        val sortedPeople = people.value.toMutableList()
                        val upperPerson = sortedPeople[index - 1]
                        sortedPeople[index - 1] = person
                        sortedPeople[index] = upperPerson
                        people.value = sortedPeople
                    }
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

@Composable
fun PersonCard(
    person: Person,
    canMoveUp: Boolean,
    onUpdate: (Person) -> Unit,
    onMoveUp: () -> Unit,
) {
    Card {
        Text(
            text = "${person.name} ${person.surname}",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
        ) {
            Text(text = "Věk:")
            IconButton(onClick = {
                onUpdate(person.copy(age = person.age - 1))
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Snížit věk",
                )
            }
            Text(text = person.age.toString())
            IconButton(onClick = {
                onUpdate(person.copy(age = person.age + 1))
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Zvýšit věk",
                )
            }
        }
        if (canMoveUp) {
            Button(
                onClick = { onMoveUp() },
                modifier = Modifier.align(Alignment.End),
            ) {
                Text(text = "Posunout výše")
            }
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