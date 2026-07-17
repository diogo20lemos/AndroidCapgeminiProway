package br.com.treinamento.atividadedeacompanhamento.atividade03.notas.model

enum class SituacaoEscolar(val descricao: String) {
    APROVADO("Aprovado"),
    RECUPERACAO("Em recuperação"),
    REPROVADO("Reprovado")
}

data class AlunoNota(
    val id: Int,
    val nome: String,
    val nota1: Double,
    val nota2: Double
) {
    val media: Double
        get() = (nota1 + nota2) / 2

    val situacao: SituacaoEscolar
        get() = when {
            media >= 7.0 -> SituacaoEscolar.APROVADO
            media >= 5.0 -> SituacaoEscolar.RECUPERACAO
            else -> SituacaoEscolar.REPROVADO
        }
}