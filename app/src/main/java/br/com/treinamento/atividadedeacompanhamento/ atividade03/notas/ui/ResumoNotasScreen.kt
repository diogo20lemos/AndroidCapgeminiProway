package br.com.treinamento.atividadedeacompanhamento.atividade03.notas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Report
import androidx.compose.material.icons.filled.Warning
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
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.viewmodel.NotasUiState

@Composable
fun ResumoNotasScreen(
    uiState: NotasUiState,
    onVoltarClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Resumo da turma",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Esta tela exibe a quantidade de alunos por situação escolar.",
            style = MaterialTheme.typography.bodyMedium
        )

        CardResumo(
            titulo = "Total de alunos",
            quantidade = uiState.totalAlunos,
            cor = Color(0xFFEAF2FF),
            icone = {
                Icon(
                    imageVector = Icons.Default.Groups,
                    contentDescription = "Total de alunos"
                )
            }
        )

        CardResumo(
            titulo = "Aprovados",
            quantidade = uiState.quantidadeAprovados,
            cor = Color(0xFFEAF7EA),
            icone = {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Aprovados"
                )
            }
        )

        CardResumo(
            titulo = "Em recuperação",
            quantidade = uiState.quantidadeRecuperacao,
            cor = Color(0xFFFFF8E1),
            icone = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Em recuperação"
                )
            }
        )

        CardResumo(
            titulo = "Reprovados",
            quantidade = uiState.quantidadeReprovados,
            cor = Color(0xFFFFEBEE),
            icone = {
                Icon(
                    imageVector = Icons.Default.Report,
                    contentDescription = "Reprovados"
                )
            }
        )

        Button(
            onClick = onVoltarClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(14.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar"
            )

            Text("Voltar para alunos")
        }
    }
}

@Composable
private fun CardResumo(
    titulo: String,
    quantidade: Int,
    cor: Color,
    icone: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = cor
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            icone()

            Text(
                text = titulo,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = quantidade.toString(),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}