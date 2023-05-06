package com.example.quizapp_fragments.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quizapp_fragments.Adapters.Userauth
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignupFragment:Fragment() {
    private val emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    private lateinit var auth:FirebaseAuth
    private lateinit var database: FirebaseDatabase
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*
        In the Signup fragment the navigation drawer is locked too.
         */
        (activity as QuizAppController).findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(
            DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        val inf=inflater.inflate(R.layout.signupfragment,container,false)

        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()


        val signupUsername=inf.findViewById<EditText>(R.id.signupUsername)
        val signupEmail=inf.findViewById<EditText>(R.id.signupEmail)
        val signupPassword=inf.findViewById<EditText>(R.id.signupPassword)
        val signupRePassword=inf.findViewById<EditText>(R.id.signupRePassword)

        val createAccountButton=inf.findViewById<Button>(R.id.createAccountButton)
        createAccountButton.setOnClickListener {
//            val loginFragment=LoginFragment()
//            replaceFragments(loginFragment)

            val username = signupUsername.text.toString()
            val email = signupEmail.text.toString()
            val password = signupPassword.text.toString()
            val rePassword = signupRePassword.text.toString()


            if(username.isEmpty() || email.isEmpty()  || password.isEmpty() || rePassword.isEmpty()){
                if(username.isEmpty()){
                    signupUsername.error = "Please Enter your name"
                }
                if(email.isEmpty()){
                    signupEmail.error = "Please Enter your Email"
                }
                if(password.isEmpty()){
                    signupPassword.error = "Please Enter your Password "
                }
                if(rePassword.isEmpty()){
                    signupRePassword.error = "Please Enter your Password Again"
                }
                Toast.makeText(context,"Enter your valid details",Toast.LENGTH_SHORT).show()
            }
            else if(!email.matches(emailPattern.toRegex())){
                signupEmail.error="Enter valid email!"
                Toast.makeText(context, "Please Enter valid email!",Toast.LENGTH_SHORT).show()
            }
            else if(password.length < 6){
                signupPassword.error="Enter password more than 6 character long"
                Toast.makeText(context,"Enter password more than 6 character long",Toast.LENGTH_SHORT).show()
            }

            else if(password != rePassword){
                signupRePassword.error="Enter password does not matches"
                Toast.makeText(context,"Enter password does not mathces",Toast.LENGTH_SHORT).show()
            }

            else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val databaseRef = database.reference.child("users").child(auth.currentUser!!.uid)
                        val users : Userauth = Userauth(username, email, auth.currentUser!!.uid)

                        databaseRef.setValue(users).addOnCompleteListener {
                            if(it.isSuccessful){
                                val loginFragment=LoginFragment()
                                replaceFragments(loginFragment)
                            } else{
                                Toast.makeText(context,"Error adding user details: ${it.exception?.message}",Toast.LENGTH_LONG).show()
                            }
                        }

                    }else{
                        val checking="Checking"
                        Toast.makeText(context,"Error adding user details: ${it.exception?.message}",Toast.LENGTH_LONG).show()
                        Log.d(checking,"Error adding user details: ${it.exception?.message}")
                    }
                }
            }


        }

        return inf
    }
    fun replaceFragments(fragment: Fragment){

        val fragmentManager: FragmentManager =requireActivity().supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.flcontainer1,fragment)
        fragmentTransaction.addToBackStack(
            null
        )
        fragmentTransaction.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Re-enable the navigation drawer when the fragment is destroyed
        (activity as QuizAppController).findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

}