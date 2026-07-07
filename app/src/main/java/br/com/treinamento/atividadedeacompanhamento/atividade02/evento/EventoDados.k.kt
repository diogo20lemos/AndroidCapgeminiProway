package br.com.treinamento.atividadedeacompanhamento.atividade02.evento

data class PalestraEvento(
    val id: Int,
    val titulo: String,
    val palestrante: String,
    val horario: String,
    val local: String,
    val descricao: String,
    val nivel: String
)

data class IngressoEvento(
    val id: Int,
    val nome: String,
    val descricao: String,
    val preco: String
)

object EventoDados {
    val palestras = listOf(
        PalestraEvento(
            id = 1,
            titulo = "Introdução ao Jetpack Compose",
            palestrante = "Mariana Costa",
            horario = "09:00",
            local = "Auditório Principal",
            descricao = "Uma palestra introdutória sobre criação de interfaces modernas usando Jetpack Compose no Android.",
            nivel = "Iniciante"
        ),
        PalestraEvento(
            id = 2,
            titulo = "Navegação entre Telas no Android",
            palestrante = "Rafael Lima",
            horario = "10:30",
            local = "Sala 2",
            descricao = "Demonstração prática de navegação com Navigation Compose, rotas e passagem de parâmetros.",
            nivel = "Intermediário"
        ),
        PalestraEvento(
            id = 3,
            titulo = "Boas Práticas com Kotlin",
            palestrante = "Fernanda Alves",
            horario = "13:30",
            local = "Sala 1",
            descricao = "Apresentação de recursos do Kotlin que ajudam a escrever código mais limpo, seguro e organizado.",
            nivel = "Intermediário"
        ),
        PalestraEvento(
            id = 4,
            titulo = "Publicando seu App Android",
            palestrante = "Carlos Mendes",
            horario = "15:00",
            local = "Auditório Principal",
            descricao = "Passo a passo conceitual para preparar um aplicativo Android para publicação.",
            nivel = "Avançado"
        )
    )

    val ingressos = listOf(
        IngressoEvento(
            id = 1,
            nome = "Visitante",
            descricao = "Acesso às palestras principais",
            preco = "Gratuito"
        ),
        IngressoEvento(
            id = 2,
            nome = "Estudante",
            descricao = "Acesso às palestras e certificado de participação",
            preco = "R$ 20,00"
        ),
        IngressoEvento(
            id = 3,
            nome = "Profissional",
            descricao = "Acesso completo ao evento, certificado e material de apoio",
            preco = "R$ 50,00"
        )
    )

    fun buscarPalestraPorId(id: Int): PalestraEvento? {
        return palestras.firstOrNull { palestra ->
            palestra.id == id
        }
    }
}