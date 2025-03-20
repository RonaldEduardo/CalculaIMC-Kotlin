package com.ronaldgustmann.calculaimc_kotlin

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var etPeso: EditText // lateinit é uma inicialização depois
    private lateinit var etAltura: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        val variavelConstante : Int = 10 // val é tipo uma constante
        var variavel : Int = 10 // var é tipo uma variável
        */

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpar = findViewById(R.id.btnLimpar)

        btnCalcular.setOnClickListener {
            btnCalcularOnClick()
        }

        btnLimpar.setOnClickListener {
            btnLimparOnClick()
        }

        btnLimpar.setOnLongClickListener {
            btnLimparOnLongClick()
            false // false para não consumir o clique padrao
        }
    }

    private fun btnLimparOnClick() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.setText("0.0")
        etPeso.requestFocus()
    }

    private fun btnCalcularOnClick() {

        if (etPeso.text.isEmpty()) {
            etPeso.error = "Campo obrigatório"
            etPeso.requestFocus()
            return
        }

        if (etAltura.text.isEmpty()) {
            etAltura.error = "Campo obrigatório"
            etAltura.requestFocus()
            return
        }

        var altura = etAltura.text.toString().toDouble()

        // caso preencha em cm, divide por 100
        if (!etAltura.text.toString().contains(".")) {
            altura = altura / 100
        }

        val peso = etPeso.text.toString().toDouble()

        val imc = calculaIMC(peso, altura, Locale.getDefault().language)

        tvResultado.text = "%.2f".format(imc)

    }

    private fun btnLimparOnLongClick() {
        Toast.makeText(this, "Limpar tudo", Toast.LENGTH_SHORT).show()
    }


    companion object {
        fun calculaIMC(peso: Double, altura: Double, locale: String): Double{
            var imc = peso / altura.pow(2)

            if(locale.equals("en")){ //trabalhando com internacionalização
                imc = imc * 730
            }
            return imc
        }
    }
}
