package br.com.treinamento.atividadedeacompanhamento.atividade03.notas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Summarize
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.model.AlunoNota
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.model.SituacaoEscolar
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.viewmodel.NotasUiState
import java.util.Locale

@Composable
fun AlunosNotasScreen(
    uiState: NotasUiState,
    onNomeChange: (String) -> Unit,
    onNota1Change: (String) -> Unit,
    onNota2Change: (String) -> Unit,
    onCadastrarClick: () -> Unit,
    onRemoverClick: (Int) -> Unit,
    onResumoClick: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            FormularioNotas(
                nome = uiState.nome,
                nota1 = uiState.nota1,
                nota2 = uiState.nota2,
                mensagem = uiState.mensagem,
                onNomeChange = onNomeChange,
                onNota1Change = onNota1Change,
                onNota2Change = onNota2Change,
                onCadastrarClick = onCadastrarClick,
                onResumoClick = onResumoClick
            )
        }

        item {
            Text(
                text = "Alunos cadastrados",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Total: ${uiState.totalAlunos}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        if (uiState.alunos.isEmpty()) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F5F5)
                    )
                ) {
                    Text(
                        text = "Nenhum aluno cadastrado ainda.",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        } else {
            items(
                items = uiState.alunos,
                key = { aluno ->
                    aluno.id
                }
            ) { aluno ->
                CardAlunoNota(
                    aluno = aluno,
                    onRemoverClick = {
                        onRemoverClick(aluno.id)
                    }
                )
            }
        }
    }
}

@Composable
private fun FormularioNotas(
    nome: String,
    nota1: String,
    nota2: String,
    mensagem: String,
    onNomeChange: (String) -> Unit,
    onNota1Change: (String) -> Unit,
    onNota2Change: (String) -> Unit,
    onCadastrarClick: () -> Unit,
    onResumoClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color(0xFFEAF2FF)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Aluno"
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = "Cadastro de aluno",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            OutlinedTextField(
                value = nome,
                onValueChange = onNomeChange,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nome do aluno")
                },
                singleLine = true
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = nota1,
                    onValueChange = onNota1Change,
                    modifier = Modifier.weight(1f),
                    label = {
                        Text("Nota 1")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )

                OutlinedTextField(
                    value = nota2,
                    onValueChange = onNota2Change,
                    modifier = Modifier.weight(1f),
                    label = {
                        Text("Nota 2")
                    },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    )
                )
            }

            Button(
                onClick = onCadastrarClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            ) {
                Text("Cadastrar aluno")
            }

            OutlinedButton(
                onClick = onResumoClick,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(50)
            ) {
                Icon(
                    imageVector = Icons.Default.Summarize,
                    contentDescription = "Resumo"
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text("Ver resumo da turma")
            }

            if (mensagem.isNotBlank()) {
                Text(
                    text = mensagem,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun CardAlunoNota(
    aluno: AlunoNota,
    onRemoverClick: () -> Unit
) {
    val corSituacao = when (aluno.situacao) {
        SituacaoEscolar.APROVADO -> Color(0xFFEAF7EA)
        SituacaoEscolar.RECUPERACAO -> Color(0xFFFFF8E1)
        SituacaoEscolar.REPROVADO -> Color(0xFFFFEBEE)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = corSituacao
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = aluno.nome,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Nota 1: ${formatarNumero(aluno.nota1)}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "Nota 2: ${formatarNumero(aluno.nota2)}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    Text(
                        text = "Média: ${formatarNumero(aluno.media)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                IconButton(
                    onClick = onRemoverClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remover aluno"
                    )
                }
            }

            AssistChip(
                onClick = {},
                label = {
                    Text(aluno.situacao.descricao)
                }
            )
        }
    }
}

private fun formatarNumero(valor: Double): String {
    return String.format(Locale("pt", "BR"), "%.1f", valor)
}