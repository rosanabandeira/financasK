package com.example.financask.ui.activity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.financask.R
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import com.example.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import kotlinx.android.synthetic.main.resumo_card.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = transacoesDeExemplo()

        configuraResumo(transacoes)

        configuraLista(transacoes)

        lista_transacoes_adiciona_receita
            .setOnClickListener {
                val view = window.decorView
                val viewCriada = LayoutInflater.from(this).inflate(
                    R.layout.form_transacao,
                    view as ViewGroup,
                    false
                )

                val ano = 2019
                val mes = 4
                val dia = 4


                val hoje = Calendar.getInstance()
                viewCriada.form_transacao_data
                    .setText(hoje.formataParaBrasileiro())

                viewCriada.form_transacao_data
                    .setOnClickListener {
                        DatePickerDialog(this,
                            DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                                val dataSelecionada = Calendar.getInstance()
                                dataSelecionada.set(ano, mes, dia)
                                viewCriada.form_transacao_data
                                    .setText(dataSelecionada.formataParaBrasileiro())

                            }, ano, mes, dia)
                            .show()
                    }

                val adapter = ArrayAdapter
                    .createFromResource(this,
                        R.array.categorias_de_receita,
                        android.R.layout.simple_dropdown_item_1line)

                viewCriada.form_transacao_categoria.adapter = adapter



                AlertDialog.Builder(this)
                    .setTitle(R.string.adiciona_receita)
                    .setView(viewCriada)
                    .setPositiveButton("Adicionar", null)
                    .setNegativeButton("Cancelar", null)
                    .show()

            }


    }


    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()

    }


    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(
            Transacao(
                valor = BigDecimal(100),
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
                valor = BigDecimal(200),
                categoria = "Premio",
                tipo = Tipo.RECEITA
            )

        )
    }

}