@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp


@Composable
fun PeopleScreen() {

    val people = remember { mutableStateOf(generatePeople(2)) }
    val createPersonDialogShown = remember { mutableStateOf(false) }

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
                createPersonDialogShown.value = true
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
        if (createPersonDialogShown.value) {
            PersonInputDialog(
                onDismiss = { createPersonDialogShown.value = false },
                onCreatePerson = { newPerson ->
                    createPersonDialogShown.value = false
                    val newPeople = people.value.toMutableList()
                    newPeople.add(newPerson)
                    people.value = newPeople
                },
            )
        }
    }
}

@Composable
fun PersonInputDialog(
    onDismiss: () -> Unit,
    onCreatePerson: (Person) -> Unit,
) {
    val name = remember { mutableStateOf("") }
    val surname = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            TextField(
                value = name.value,
                onValueChange = { name.value = it },
                label = { Text(text = "Jméno") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                ),
            )
            TextField(
                value = surname.value,
                onValueChange = { surname.value = it },
                label = { Text(text = "Přijmení") },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                ),
            )
            Row(
                modifier = Modifier.align(Alignment.End),
            ) {
                TextButton(onClick = onDismiss) {
                    Text(text = "Zrušit")
                }
                TextButton(
                    enabled = name.value.isNotEmpty() && surname.value.isNotEmpty(),
                    onClick = {
                        onCreatePerson(
                            Person(
                                name = name.value,
                                surname = surname.value,
                            )
                        )
                    },
                ) {
                    Text(text = "Uložit")
                }
            }
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