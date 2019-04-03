package com.example.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.financask.R
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()


        configuraLista(transacoes)

    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(
            Transacao(
                valor = BigDecimal(20.5),
                tipo = Tipo.DESPESA,
                categoria = "Almoco de final de semana",
                data = Calendar.getInstance()
            ),
            (Transacao(
                BigDecimal(100.0),
                tipo = Tipo.RECEITA,
                categoria = "Economia"
            )), Transacao(
                valor = BigDecimal(200),
                tipo = Tipo.DESPESA
            )
            , Transacao(
                valor = BigDecimal(500),
                categoria = "Premio",
                tipo = Tipo.RECEITA
            )

        )
    }

}