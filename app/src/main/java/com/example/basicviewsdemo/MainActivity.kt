package com.example.basicviewsdemo

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val checkTerms = findViewById<CheckBox>(R.id.checkTerms)
        val toggleMode = findViewById<ToggleButton>(R.id.toggleMode)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val imgProfile = findViewById<ImageButton>(R.id.imgProfile)

        imgProfile.setOnClickListener {
            Toast.makeText(this, "Profile Picture Clicked", Toast.LENGTH_SHORT).show()
        }

        toggleMode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show()
            }
        }

        btnRegister.setOnClickListener {

            val name = etName.text.toString()
            val email = etEmail.text.toString()

            val gender = when (radioGroup.checkedRadioButtonId) {
                R.id.rbMale -> "Male"
                R.id.rbFemale -> "Female"
                R.id.rbOther -> "Other"
                else -> "Not Selected"
            }

            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please enter all details", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!checkTerms.isChecked) {
                Toast.makeText(this, "Please accept Terms & Conditions", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val message = """
Registration Successful 🎉

Name: $name
Email: $email
Gender: $gender
Dark Mode: ${if (toggleMode.isChecked) "ON" else "OFF"}
            """.trimIndent()

            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }
}