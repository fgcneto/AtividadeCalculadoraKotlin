package tads.eaj.ufrn.supercalculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    var variavelX:Int = 0
    var variavelY:Int = 0
    var resultado:Int = 0

    val setVariavelXlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result ->
            if (result.resultCode == RESULT_OK){
                variavelX = result.data!!.getIntExtra("valor",0)
                val labelX = findViewById<TextView>(R.id.labelXMain)
                labelX.text = "${variavelX}"
            }else{
                Toast.makeText(this, "Você cancelou a inserção de dados", Toast.LENGTH_SHORT).show()
            }
    }

    val setVariavelYlauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if (result.resultCode == RESULT_OK){
            variavelY = result.data!!.getIntExtra("valor",0)
            val labelY = findViewById<TextView>(R.id.labelYMain)
            labelY.text = "${variavelY}"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val labelX = findViewById<TextView>(R.id.labelXMain)
        val labelY = findViewById<TextView>(R.id.labelYMain)
        val textViewresultado = findViewById<TextView>(R.id.textViewResultadoMain)

        labelX.text = "${variavelX}"
        labelY.text = "${variavelY}"
        textViewresultado.text = "${resultado}"

        val botaoX = findViewById<Button>(R.id.botaoXMain)
        val botaoY = findViewById<Button>(R.id.botaoYMain)
        val botaoCalcular = findViewById<Button>(R.id.botaoCalcularMain)

        botaoX.setOnClickListener {
            val intent = Intent(this, RecebeActivity::class.java)
            val bundle = Bundle()
            bundle.putString("LABEL", "variavel X")
            bundle.putInt("valor", variavelX)
            intent.putExtras(bundle)
            setVariavelXlauncher.launch(intent)
        }

        botaoY.setOnClickListener {
            val intent = Intent(this, RecebeActivity::class.java)
            val bundle = Bundle()
            bundle.putString("LABEL", "variavel Y")
            bundle.putInt("valor", variavelY)
            intent.putExtras(bundle)
            setVariavelYlauncher.launch(intent)
        }

        botaoCalcular.setOnClickListener {
            textViewresultado.text = (variavelX + variavelY).toString()
            Toast.makeText(this, getString(R.string.realizando_calculos), Toast.LENGTH_SHORT).show()
        }
    }
}