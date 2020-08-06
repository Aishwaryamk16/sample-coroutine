package com.example.signinappsample.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.signinappsample.fragment.NoteDatabaseFragment
import com.example.signinappsample.R
import com.example.signinappsample.fragment.SignInFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task


class HomeActivity : AppCompatActivity(),
    SignInFragment.Listener {
    private lateinit var googleSignInClient: GoogleSignInClient
     val TAG = "MainActivity"
    companion object{
        const val RC_SIGN_IN =1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(SignInFragment(this))
        initialiseGoogleSignIn()
    }

    private fun initialiseGoogleSignIn() {
        val gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    override fun onStart() {
        super.onStart()
        //If GoogleSignIn.getLastSignedInAccount returns a GoogleSignInAccount object (rather than null), the user has already signed in to your app with Google..
        //GoogleSignIn.getLastSignedInAccount returns null, the user has not yet signed in to your app with Google
        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)
    }

    private fun updateUI(account: GoogleSignInAccount?) {

    }

    fun loadFragment(fragment: Fragment) {
        when (fragment) {
            is SignInFragment -> {
                val manager: FragmentManager = supportFragmentManager
                val transaction: FragmentTransaction =
                    manager.beginTransaction()
                transaction.add(
                    R.id.container,
                    SignInFragment(this)
                ).commit()
            }

            is NoteDatabaseFragment ->{
                val manager: FragmentManager = supportFragmentManager
                val transaction: FragmentTransaction =
                    manager.beginTransaction()
                transaction.replace(
                    R.id.container,
                    NoteDatabaseFragment()
                ).commit()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

        }

    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>?) {
        try {
            val account: GoogleSignInAccount? = completedTask?.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("MainActivity", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }

    }

    override fun onClick() {
       Log.d("onclicked","onClicked")
        val signInIntent: Intent = googleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent,
            RC_SIGN_IN
        )
    }

}
