package com.example.financask.ui.dialog

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.financask.R
import com.example.financask.extension.converteParaCalendar
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) {

    private val viewCriada = criaLayout()

    private fun configuraDialog() {
        configuraCampoData()
        configuraCampoCategoria()
        configuraFormulario()
    }

    private fun configuraFormulario() {
        AlertDialog.Builder(context)
            .setTitle(R.string.adiciona_receita)
            .setView(viewCriada)
            .setPositiveButton(
                "Adicionar"
            ) { dialog, _ ->
                val valorEmTexto = viewCriada
                    .form_transacao_valor.text.toString()
                val dataEmTexto = viewCriada
                    .form_transacao_data.text.toString()
                val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()


                val valor = converteCampoValor(valorEmTexto)
                val data = dataEmTexto.converteParaCalendar()


                val transacaoCriada = Transacao(
                    tipo = Tipo.RECEITA, valor = valor,
                    data = data,
                    categoria = categoriaEmTexto
                )

                atualizaTransacoes(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)

            }
            .setNegativeButton("Cancelar", null)
            .show()
    }



    private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        return try {
            BigDecimal(valorEmTexto)

        } catch (exception: NumberFormatException) {
            Toast.makeText(context, "Falha na conversao de valor", Toast.LENGTH_LONG).show()
            BigDecimal.ZERO

        }
    }

    private fun configuraCampoCategoria() {
        val adapter = ArrayAdapter
            .createFromResource(
                context,
                R.array.categorias_de_receita,
                android.R.layout.simple_dropdown_item_1line
            )

        viewCriada.form_transacao_categoria.adapter = adapter
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)



        viewCriada.form_transacao_data
            .setText(hoje.formataParaBrasileiro())

        viewCriada.form_transacao_data
            .setOnClickListener {
                DatePickerDialog(
                    context,
                    DatePickerDialog.OnDateSetListener { _, ano, mes, dia ->
                        val dataSelecionada = Calendar.getInstance()
                        dataSelecionada.set(ano, mes, dia)
                        viewCriada.form_transacao_data
                            .setText(dataSelecionada.formataParaBrasileiro())

                    }, ano, mes, dia
                )
                    .show()
            }
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context).inflate(
            R.layout.form_transacao, viewGroup,
            false
        )
    }


}