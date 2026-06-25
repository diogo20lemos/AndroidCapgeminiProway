package br.com.treinamento.atividadedeacompanhamento.model

enum class Nivel(val label: String) {
    ADMINISTRATIVO("Administrativo"),
    FINANCEIRO("Financeiro"),
    GERENCIA("Gerência"),
    SUPORTE("Suporte")
}

data class Colaborador(
    val id: Int,
    val nome: String,
    val email: String,
    val nivel: Nivel
)

