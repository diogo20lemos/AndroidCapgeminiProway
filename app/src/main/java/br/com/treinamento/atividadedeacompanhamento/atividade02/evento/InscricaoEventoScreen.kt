package br.com.treinamento.atividadedeacompanhamento.atividade02.evento

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun InscricaoEventoScreen(
    ingressos: List<IngressoEvento>
) {
    var nome by rememberSaveable {
        mutableStateOf("")
    }

    var email by rememberSaveable {
        mutableStateOf("")
    }

    var ingressoSelecionadoId by rememberSaveable {
        mutableIntStateOf(ingressos.firstOrNull()?.id ?: 0)
    }

    var mensagemConfirmacao by rememberSaveable {
        mutableStateOf("")
    }

    val ingressoSelecionado = ingressos.firstOrNull { ingresso ->
        ingresso.id == ingressoSelecionadoId
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Faça sua inscrição",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Preencha os dados abaixo. A inscrição é apenas uma simulação local, sem banco de dados.",
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = nome,
            onValueChange = {
                nome = it
                mensagemConfirmacao = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Nome")
            },
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                mensagemConfirmacao = ""
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("E-mail")
            },
            singleLine = true
        )

        Text(
            text = "Tipo de ingresso",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        ingressos.forEach { ingresso ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (ingresso.id == ingressoSelecionadoId) {
                        Color(0xFFEAF2FF)
                    } else {
                        Color(0xFFF7F7F7)
                    }
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = ingresso.id == ingressoSelecionadoId,
                        onClick = {
                            ingressoSelecionadoId = ingresso.id
                            mensagemConfirmacao = ""
                        }
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = ingresso.nome,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = ingresso.descricao,
                            style = MaterialTheme.typography.bodySmall
                        )

                        Text(
                            text = ingresso.preco,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Button(
            onClick = {
                mensagemConfirmacao = if (nome.isBlank() || email.isBlank()) {
                    "Preencha nome e e-mail para concluir a inscrição."
                } else {
                    "Inscrição de $nome confirmada como ${ingressoSelecionado?.nome ?: "Participante"}."
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text("Confirmar inscrição")
        }

        if (mensagemConfirmacao.isNotBlank()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFEAF7EA)
                )
            ) {
                Text(
                    text = mensagemConfirmacao,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}