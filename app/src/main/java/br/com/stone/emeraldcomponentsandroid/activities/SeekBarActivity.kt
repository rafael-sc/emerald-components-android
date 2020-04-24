package br.com.stone.emeraldcomponentsandroid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.stone.emeraldcomponentsandroid.R
import kotlinx.android.synthetic.main.activity_seek_bar.*

class SeekBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar)

        emeraldScoreSeekbar.setChangeProgress = {
            Toast.makeText(
                    this,
                    "Selected Value $it",
                    Toast.LENGTH_SHORT).show()
        }

    }
}
