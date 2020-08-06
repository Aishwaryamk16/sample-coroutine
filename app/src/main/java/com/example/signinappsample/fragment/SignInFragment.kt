package com.example.signinappsample.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.signinappsample.R
import com.example.signinappsample.activity.HomeActivity
import com.example.signinappsample.activity.ProfileActivity
import com.example.signinappsample.util.InputValidator
import com.example.signinappsample.viewmodel.SignInViewModal
import kotlinx.android.synthetic.main.sign_in_fragment.*


class SignInFragment(private val listener: Listener) : Fragment(), View.OnClickListener {
    private var mail: String? = null
    private var password: String? = null
    private lateinit var viewModel: SignInViewModal
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.sign_in_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[SignInViewModal::class.java]
        observeLiveData()
        initView()
    }

    private fun observeLiveData() {
        viewModel.isEmailValidObserver.observeForever {
            handleMail(it)
        }
        viewModel.isPasswordValidObserver.observeForever {
            handlePassword(it)
        }
    }

    private fun handlePassword(it: Boolean?) {
        if (it == true) {
            //do nothing
        } else {
            txtpassword.setError("Enter the correct password");

        }
    }

    private fun handleMail(it: Boolean?) {
        if (it == true) {
            //do nothing
        } else {
            txtMail.setError("Enter the correct mail");
        }

    }

    private fun initView() {
        setListeners()
        txtMail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                mail = p0.toString()
                mail?.let {
                    viewModel.updateEmail(mail!!, InputValidator.Validate)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        txtpassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                password = p0.toString()
                password?.let {
                    viewModel.updatePassword(password!!, InputValidator.Validate)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun setListeners() {
        txtpassword.setOnClickListener(this)
        txtMail.setOnClickListener(this)
        googleSinIn.setOnClickListener(this)
        txtShow.setOnClickListener(this)
        nextDatabase.setOnClickListener(this)
        nextCoroutine.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.googleSinIn -> {
                signIn();
            }
            R.id.txtMail -> {
                (if (mail == null) {
                    txtMail.setError("This field can not be blank");
                }
                        )
            }
            R.id.txtpassword -> {

                if (password == null) {
                    txtpassword.setError("This field can not be blank");
                }
            }
            R.id.txtShow -> {
                password?.let {
                    if (txtShow.text.toString().equals("Show")) {
                        txtpassword.transformationMethod =
                            HideReturnsTransformationMethod.getInstance()
                        txtShow.text = "Hide"
                    } else {
                        txtpassword.transformationMethod =
                            PasswordTransformationMethod.getInstance()
                        txtShow.text = "Show"
                    }
                }
            }
            R.id.nextDatabase -> {
                (activity as HomeActivity).loadFragment(NoteDatabaseFragment())
            }
            R.id.nextCoroutine -> {
                val intent = Intent(context, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }


    private fun signIn() {
        listener.onClick()
    }

    interface Listener {
        fun onClick()
    }
}