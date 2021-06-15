package ge.george.btu_android_exercise_9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ge.george.btu_android_exercise_9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLetsGo.setOnClickListener { register() }
    }

    private fun register() {
        binding.apply {
            if (!etEmail.isValidEmail()) {
                etEmail.makeError()
                return
            }

            if (!etPassword.isPasswordValid()) {
                etPassword.makeError()
                return
            }

            if (!etPassword.isPasswordMatch(etRepeatPassword)) {
                etRepeatPassword.makeError("Passwords do not match!")
                return
            }

            signUpToFirebase()
        }
    }

    private fun signUpToFirebase() {
        binding.progress.isVisible = true
        Firebase.auth.createUserWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        ).addOnCompleteListener {
            binding.progress.isVisible = false

            if (it.isSuccessful) {
                binding.root.snackBar("Successfully Registered!", Snackbar.LENGTH_INDEFINITE)
            } else {
                binding.root.snackBar(it.exception?.message!!, Snackbar.LENGTH_INDEFINITE)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}