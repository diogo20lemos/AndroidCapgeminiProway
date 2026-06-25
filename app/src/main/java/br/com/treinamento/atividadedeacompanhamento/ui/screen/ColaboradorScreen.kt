package br.com.treinamento.atividadedeacompanhamento.ui.screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenu
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.treinamento.atividadedeacompanhamento.model.Colaborador
import br.com.treinamento.atividadedeacompanhamento.model.Nivel
import br.com.treinamento.atividadedeacompanhamento.viewmodel.ColaboradorViewModel

@Composable
fun ColaboradorScreen(
    viewModel: ColaboradorViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        FormularioColaborador(
            nome = viewModel.nome,
            email = viewModel.email,
            nivel = viewModel.nivelSelecionado,
            emEdicao = viewModel.emEdicao(),
            onNomeChange = viewModel::onNomeChange,
            onEmailChange = viewModel::onEmailChange,
            onNivelChange = viewModel::onNivelChange,
            onSalvar = viewModel::salvar,
            onRemover = viewModel::removerSelecionado,
            onCancelar = viewModel::cancelar
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(viewModel.colaboradores, key = { it.id }) { colaborador ->
                CardColaborador(
                    colaborador = colaborador,
                    onSelecionar = { viewModel.selecionar(colaborador) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormularioColaborador(
    nome: String,
    email: String,
    nivel: Nivel,
    emEdicao: Boolean,
    onNomeChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onNivelChange: (Nivel) -> Unit,
    onSalvar: () -> Unit,
    onRemover: () -> Unit,
    onCancelar: () -> Unit
) {
    var expandido by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        OutlinedTextField(
            value = nome,
            onValueChange = onNomeChange,
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = expandido,
            onExpandedChange = { expandido = !expandido }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),
                readOnly = true,
                value = nivel.label,
                onValueChange = {},
                label = { Text("Nível") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandido) }
            )

            ExposedDropdownMenu(
                expanded = expandido,
                onDismissRequest = { expandido = false }
            ) {
                Nivel.entries.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item.label) },
                        onClick = {
                            onNivelChange(item)
                            expandido = false
                        }
                    )
                }
            }
        }

        Button(
            onClick = onSalvar,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50)
        ) {
            Text(if (emEdicao) "Salvar alterações" else "Cadastrar")
        }

        if (emEdicao) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = onRemover,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Remover")
                }

                Button(
                    onClick = onCancelar,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(50)
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}

@Composable
private fun CardColaborador(
    colaborador: Colaborador,
    onSelecionar: () -> Unit
) {
    val corFundo = when (colaborador.nivel) {
        Nivel.SUPORTE -> Color(0xFFEAF4FF)
        Nivel.FINANCEIRO -> Color(0xFFEFF8EE)
        Nivel.ADMINISTRATIVO -> Color(0xFFFBF5E8)
        Nivel.GERENCIA -> Color(0xFFF4ECFF)
    }

    val icone = when (colaborador.nivel) {
        Nivel.SUPORTE -> Icons.Default.Build
        Nivel.FINANCEIRO -> Icons.Default.AttachMoney
        Nivel.ADMINISTRATIVO -> Icons.Default.AccountBox
        Nivel.GERENCIA -> Icons.Default.Star
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = corFundo),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icone,
                    contentDescription = "Ícone do nível",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Nome: ${colaborador.nome}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "E-mail: ${colaborador.email}")
            Text(text = "Nível: ${colaborador.nivel.label}")

            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onSelecionar,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(50)
            ) {
                Text("Selecionar")
            }
        }
    }
}
