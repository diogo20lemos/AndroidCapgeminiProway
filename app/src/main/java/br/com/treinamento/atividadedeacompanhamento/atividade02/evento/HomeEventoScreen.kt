package br.com.treinamento.atividadedeacompanhamento.atividade02.evento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeEventoScreen(
    onVerProgramacaoClick: () -> Unit,
    onInscricaoClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ElevatedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = Color(0xFFEAF2FF)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Ícone do evento",
                    tint = Color(0xFF2457A6)
                )

                Text(
                    text = "Dev Summit 2026",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Um evento fictício para estudantes de Android, Kotlin e Jetpack Compose.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoEvento(
                    titulo = "Data e horário",
                    descricao = "20 de setembro de 2026, das 09:00 às 17:00",
                    icone = {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = "Horário"
                        )
                    }
                )

                InfoEvento(
                    titulo = "Local",
                    descricao = "Centro de Convenções Tech Hall",
                    icone = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Local"
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onVerProgramacaoClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(14.dp)
        ) {
            Text("Ver programação")
        }

        OutlinedButton(
            onClick = onInscricaoClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(14.dp)
        ) {
            Text("Fazer inscrição")
        }
    }
}

@Composable
private fun InfoEvento(
    titulo: String,
    descricao: String,
    icone: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        icone()

        Text(
            text = titulo,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = descricao,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}