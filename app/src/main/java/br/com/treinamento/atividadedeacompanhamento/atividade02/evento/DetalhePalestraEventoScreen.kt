package br.com.treinamento.atividadedeacompanhamento.atividade02.evento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun DetalhePalestraEventoScreen(
    palestra: PalestraEvento?,
    onInscreverClick: () -> Unit
) {
    if (palestra == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Palestra não encontrada.",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFEAF2FF)
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = palestra.titulo,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                AssistChip(
                    onClick = {},
                    label = {
                        Text(palestra.nivel)
                    }
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                ItemDetalheEvento(
                    titulo = "Palestrante",
                    descricao = palestra.palestrante,
                    icone = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Palestrante"
                        )
                    }
                )

                ItemDetalheEvento(
                    titulo = "Horário",
                    descricao = palestra.horario,
                    icone = {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Horário"
                        )
                    }
                )

                ItemDetalheEvento(
                    titulo = "Local",
                    descricao = palestra.local,
                    icone = {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "Local"
                        )
                    }
                )

                ItemDetalheEvento(
                    titulo = "Descrição",
                    descricao = palestra.descricao,
                    icone = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Descrição"
                        )
                    }
                )
            }
        }

        Button(
            onClick = onInscreverClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(14.dp)
        ) {
            Text("Quero participar")
        }
    }
}

@Composable
private fun ItemDetalheEvento(
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