package br.com.treinamento.atividadedeacompanhamento.atividade02.evento

object EventoRoutes {
    const val HOME = "home_evento"
    const val PROGRAMACAO = "programacao_evento"
    const val INSCRICAO = "inscricao_evento"
    const val SOBRE = "sobre_evento"

    private const val DETALHE_BASE = "detalhe_palestra"
    const val DETALHE = "$DETALHE_BASE/{palestraId}"

    fun detalhePalestra(palestraId: Int): String {
        return "$DETALHE_BASE/$palestraId"
    }
}