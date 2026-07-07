package br.com.treinamento.atividadedeacompanhamento.atividade02.evento

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Activity02EventoApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rotaAtual = navBackStackEntry?.destination?.route

    val estaNaTelaDeDetalhe = rotaAtual == EventoRoutes.DETALHE

    val titulo = when (rotaAtual) {
        EventoRoutes.HOME -> "Dev Summit 2026"
        EventoRoutes.PROGRAMACAO -> "Programação"
        EventoRoutes.INSCRICAO -> "Inscrição"
        EventoRoutes.SOBRE -> "Sobre"
        EventoRoutes.DETALHE -> "Detalhes da palestra"
        else -> "Dev Summit 2026"
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = titulo)
                },
                navigationIcon = {
                    if (estaNaTelaDeDetalhe) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Voltar"
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = rotaAtual == EventoRoutes.HOME,
                    onClick = {
                        navController.navigate(EventoRoutes.HOME) {
                            popUpTo(EventoRoutes.HOME) {
                                inclusive = false
                            }
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Início"
                        )
                    },
                    label = {
                        Text("Início")
                    }
                )

                NavigationBarItem(
                    selected = rotaAtual == EventoRoutes.PROGRAMACAO,
                    onClick = {
                        navController.navigate(EventoRoutes.PROGRAMACAO) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Programação"
                        )
                    },
                    label = {
                        Text("Programação")
                    }
                )

                NavigationBarItem(
                    selected = rotaAtual == EventoRoutes.INSCRICAO,
                    onClick = {
                        navController.navigate(EventoRoutes.INSCRICAO) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            contentDescription = "Inscrição"
                        )
                    },
                    label = {
                        Text("Inscrição")
                    }
                )

                NavigationBarItem(
                    selected = rotaAtual == EventoRoutes.SOBRE,
                    onClick = {
                        navController.navigate(EventoRoutes.SOBRE) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Sobre"
                        )
                    },
                    label = {
                        Text("Sobre")
                    }
                )
            }
        }
    ) { paddingInterno ->

        NavHost(
            navController = navController,
            startDestination = EventoRoutes.HOME,
            modifier = Modifier.padding(paddingInterno)
        ) {
            composable(EventoRoutes.HOME) {
                HomeEventoScreen(
                    onVerProgramacaoClick = {
                        navController.navigate(EventoRoutes.PROGRAMACAO)
                    },
                    onInscricaoClick = {
                        navController.navigate(EventoRoutes.INSCRICAO)
                    }
                )
            }

            composable(EventoRoutes.PROGRAMACAO) {
                ProgramacaoEventoScreen(
                    palestras = EventoDados.palestras,
                    onPalestraClick = { palestraId ->
                        navController.navigate(
                            EventoRoutes.detalhePalestra(palestraId)
                        )
                    }
                )
            }

            composable(
                route = EventoRoutes.DETALHE,
                arguments = listOf(
                    navArgument("palestraId") {
                        type = NavType.IntType
                    }
                )
            ) { entrada ->
                val palestraId = entrada.arguments?.getInt("palestraId") ?: 0
                val palestra = EventoDados.buscarPalestraPorId(palestraId)

                DetalhePalestraEventoScreen(
                    palestra = palestra,
                    onInscreverClick = {
                        navController.navigate(EventoRoutes.INSCRICAO)
                    }
                )
            }

            composable(EventoRoutes.INSCRICAO) {
                InscricaoEventoScreen(
                    ingressos = EventoDados.ingressos
                )
            }

            composable(EventoRoutes.SOBRE) {
                SobreEventoScreen()
            }
        }
    }
}