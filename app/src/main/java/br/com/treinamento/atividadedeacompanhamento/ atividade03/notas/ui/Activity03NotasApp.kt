package br.com.treinamento.atividadedeacompanhamento.atividade03.notas.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.navigation.NotasRoutes
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.viewmodel.NotasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Activity03NotasApp(
    notasViewModel: NotasViewModel = viewModel()
) {
    val navController = rememberNavController()
    val uiState by notasViewModel.uiState.collectAsState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = navBackStackEntry?.destination?.route

    val titulo = when (rotaAtual) {
        NotasRoutes.ALUNOS -> "Gestão de Notas"
        NotasRoutes.RESUMO -> "Resumo da Turma"
        else -> "Gestão de Notas"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = titulo)
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = rotaAtual == NotasRoutes.ALUNOS,
                    onClick = {
                        navController.navigate(NotasRoutes.ALUNOS) {
                            popUpTo(NotasRoutes.ALUNOS) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.School,
                            contentDescription = "Alunos"
                        )
                    },
                    label = {
                        Text("Alunos")
                    }
                )

                NavigationBarItem(
                    selected = rotaAtual == NotasRoutes.RESUMO,
                    onClick = {
                        navController.navigate(NotasRoutes.RESUMO) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Assessment,
                            contentDescription = "Resumo"
                        )
                    },
                    label = {
                        Text("Resumo")
                    }
                )
            }
        }
    ) { paddingInterno ->
        NavHost(
            navController = navController,
            startDestination = NotasRoutes.ALUNOS,
            modifier = Modifier.padding(paddingInterno)
        ) {
            composable(NotasRoutes.ALUNOS) {
                AlunosNotasScreen(
                    uiState = uiState,
                    onNomeChange = notasViewModel::onNomeChange,
                    onNota1Change = notasViewModel::onNota1Change,
                    onNota2Change = notasViewModel::onNota2Change,
                    onCadastrarClick = notasViewModel::cadastrarAluno,
                    onRemoverClick = notasViewModel::removerAluno,
                    onResumoClick = {
                        navController.navigate(NotasRoutes.RESUMO)
                    }
                )
            }

            composable(NotasRoutes.RESUMO) {
                ResumoNotasScreen(
                    uiState = uiState,
                    onVoltarClick = {
                        navController.navigate(NotasRoutes.ALUNOS) {
                            popUpTo(NotasRoutes.ALUNOS) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}