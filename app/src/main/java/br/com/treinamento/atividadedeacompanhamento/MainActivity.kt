package br.com.treinamento.atividadedeacompanhamento

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import br.com.treinamento.atividadedeacompanhamento.atividade02.evento.Activity02EventoApp
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.ui.Activity03NotasApp
import br.com.treinamento.atividadedeacompanhamento.ui.screen.ColaboradorScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Activity03NotasApp()

                // Atividade 1:
                // ColaboradorScreen()

                // Atividade 2:
                // Activity02EventoApp()

                // Atividade 3:
                // Activity03NotasApp()

            }
        }
    }
}
