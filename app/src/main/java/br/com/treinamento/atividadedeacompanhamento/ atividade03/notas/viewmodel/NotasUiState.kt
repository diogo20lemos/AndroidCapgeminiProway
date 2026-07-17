package br.com.treinamento.atividadedeacompanhamento.atividade03.notas.viewmodel

import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.model.AlunoNota
import br.com.treinamento.atividadedeacompanhamento.atividade03.notas.model.SituacaoEscolar

data class NotasUiState(
    val nome: String = "",
    val nota1: String = "",
    val nota2: String = "",
    val alunos: List<AlunoNota> = emptyList(),
    val mensagem: String = ""
) {
    val quantidadeAprovados: Int
        get() = alunos.count { aluno ->
            aluno.situacao == SituacaoEscolar.APROVADO
        }

    val quantidadeRecuperacao: Int
        get() = alunos.count { aluno ->
            aluno.situacao == SituacaoEscolar.RECUPERACAO
        }

    val quantidadeReprovados: Int
        get() = alunos.count { aluno ->
            aluno.situacao == SituacaoEscolar.REPROVADO
        }

    val totalAlunos: Int
        get() = alunos.size
}