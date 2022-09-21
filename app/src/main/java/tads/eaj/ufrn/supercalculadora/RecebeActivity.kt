package tads.eaj.ufrn.supercalculadora

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RecebeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recebe)

        val label = intent.extras?.getString("LABEL")
        val valor = intent.extras?.getInt("valor")


        val textViewVariavel = findViewById<TextView>(R.id.textViewRecebe)
        val editTextView = findViewById<EditText>(R.id.editTextRecebe)

        textViewVariavel.text = label
        editTextView.setText(valor.toString())


        val botaoOK = findViewById<Button>(R.id.botaoOKRecebe)
        val botaoCancelar = findViewById<Button>(R.id.botaoCancelarRecebe)

        botaoOK.setOnClickListener {
            val intent = Intent()
            val bundle = Bundle()
            val editTextView = findViewById<EditText>(R.id.editTextRecebe)
            bundle.putInt("valor", editTextView.text.toString().toInt())
            intent.putExtras(bundle)
            setResult(RESULT_OK, intent)
            finish()
        }

        botaoCancelar.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}