package com.example.financask.ui.activity

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.financask.R
import com.example.financask.extension.formataParaBrasileiro
import com.example.financask.model.Resumo
import com.example.financask.model.Tipo
import com.example.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal


class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)


    fun atualiza(){
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }



    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }

    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa
        with(
            view.resumo_card_despesa
        ) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }


    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPor(total)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        return if (valor >= BigDecimal.ZERO) {
            return corReceita
        } else {
            return corDespesa
        }
    }


}