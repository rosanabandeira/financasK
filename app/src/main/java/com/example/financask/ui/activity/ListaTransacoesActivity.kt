package com.example.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.financask.R
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)



        configuraResumo()

        configuraLista()

        lista_transacoes_adiciona_receita
            .setOnClickListener {
                configuraDialog()

            }


    }



    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }


    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()

    }


    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

//    private fun transacoesDeExemplo(): List<Transacao> {
//        return listOf(
//            Transacao(
//                valor = BigDecimal(100),
//                tipo = Tipo.DESPESA,
//                categoria = "Almoco de final de semana",
//                data = Calendar.getInstance()
//            ),
//            (Transacao(
//                BigDecimal(100.0),
//                tipo = Tipo.RECEITA,
//                categoria = "Economia"
//            )), Transacao(
//                valor = BigDecimal(200),
//                tipo = Tipo.DESPESA
//            )
//            , Transacao(
//                valor = BigDecimal(200),
//                categoria = "Premio",
//                tipo = Tipo.RECEITA
//            )
//
//        )
//    }

}