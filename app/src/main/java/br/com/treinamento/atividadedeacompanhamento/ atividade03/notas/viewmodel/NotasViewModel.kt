package br.com.treinamento.atividadedeacompanhamento.atividade03.notas.viewmodel

import androidx.lifecycle.ViewModel
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.model.AlunoNota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NotasViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NotasUiState())
    val uiState: StateFlow<NotasUiState> = _uiState.asStateFlow()

    private var proximoId = 1

    fun onNomeChange(valor: String) {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                nome = valor,
                mensagem = ""
            )
        }
    }

    fun onNota1Change(valor: String) {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                nota1 = valor,
                mensagem = ""
            )
        }
    }

    fun onNota2Change(valor: String) {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                nota2 = valor,
                mensagem = ""
            )
        }
    }

    fun cadastrarAluno() {
        val estadoAtual = _uiState.value

        val nomeTratado = estadoAtual.nome.trim()
        val nota1Convertida = converterNota(estadoAtual.nota1)
        val nota2Convertida = converterNota(estadoAtual.nota2)

        if (nomeTratado.isBlank()) {
            atualizarMensagem("Informe o nome do aluno.")
            return
        }

        if (nota1Convertida == null || nota2Convertida == null) {
            atualizarMensagem("Informe notas válidas. Exemplo: 8.5 ou 8,5.")
            return
        }

        if (!notaValida(nota1Convertida) || !notaValida(nota2Convertida)) {
            atualizarMensagem("As notas devem estar entre 0 e 10.")
            return
        }

        val novoAluno = AlunoNota(
            id = proximoId++,
            nome = nomeTratado,
            nota1 = nota1Convertida,
            nota2 = nota2Convertida
        )

        _uiState.update { estado ->
            estado.copy(
                nome = "",
                nota1 = "",
                nota2 = "",
                alunos = estado.alunos + novoAluno,
                mensagem = "Aluno cadastrado com sucesso."
            )
        }
    }

    fun removerAluno(id: Int) {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                alunos = estadoAtual.alunos.filter { aluno ->
                    aluno.id != id
                },
                mensagem = "Aluno removido com sucesso."
            )
        }
    }

    fun limparMensagem() {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                mensagem = ""
            )
        }
    }

    private fun atualizarMensagem(mensagem: String) {
        _uiState.update { estadoAtual ->
            estadoAtual.copy(
                mensagem = mensagem
            )
        }
    }

    private fun converterNota(valor: String): Double? {
        return valor
            .replace(",", ".")
            .trim()
            .toDoubleOrNull()
    }

    private fun notaValida(nota: Double): Boolean {
        return nota in 0.0..10.0
    }
}
