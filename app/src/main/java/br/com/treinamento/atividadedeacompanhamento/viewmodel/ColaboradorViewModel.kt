package br.com.treinamento.atividadedeacompanhamento.viewmodel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.treinamento.atividadedeacompanhamento.model.Colaborador
import br.com.treinamento.atividadedeacompanhamento.model.Nivel

class ColaboradorViewModel : ViewModel() {

    var nome by mutableStateOf("")
        private set

    var email by mutableStateOf("")
        private set

    var nivelSelecionado by mutableStateOf(Nivel.SUPORTE)
        private set

    private var proximoId by mutableIntStateOf(4)

    var selecionadoId by mutableStateOf<Int?>(null)
        private set

    private val _colaboradores = mutableStateListOf(
        Colaborador(1, "Ana Souza", "ana.souza@empresa.com", Nivel.SUPORTE),
        Colaborador(2, "Bruno Lima", "bruno.lima@empresa.com", Nivel.FINANCEIRO),
        Colaborador(3, "Carlos Eduardo", "carlos.eduardo@empresa.com", Nivel.ADMINISTRATIVO)
    )
    val colaboradores: List<Colaborador> get() = _colaboradores

    fun onNomeChange(valor: String) {
        nome = valor
    }

    fun onEmailChange(valor: String) {
        email = valor
    }

    fun onNivelChange(valor: Nivel) {
        nivelSelecionado = valor
    }

    fun selecionar(colaborador: Colaborador) {
        selecionadoId = colaborador.id
        nome = colaborador.nome
        email = colaborador.email
        nivelSelecionado = colaborador.nivel
    }

    fun salvar() {
        if (nome.isBlank() || email.isBlank()) return

        val idAtual = selecionadoId
        if (idAtual == null) {
            _colaboradores.add(
                Colaborador(
                    id = proximoId++,
                    nome = nome.trim(),
                    email = email.trim(),
                    nivel = nivelSelecionado
                )
            )
        } else {
            val indice = _colaboradores.indexOfFirst { it.id == idAtual }
            if (indice != -1) {
                _colaboradores[indice] = _colaboradores[indice].copy(
                    nome = nome.trim(),
                    email = email.trim(),
                    nivel = nivelSelecionado
                )
            }
        }

        cancelar()
    }

    fun removerSelecionado() {
        val idAtual = selecionadoId ?: return
        _colaboradores.removeAll { it.id == idAtual }
        cancelar()
    }

    fun cancelar() {
        selecionadoId = null
        nome = ""
        email = ""
        nivelSelecionado = Nivel.SUPORTE
    }

    fun emEdicao(): Boolean = selecionadoId != null
}
