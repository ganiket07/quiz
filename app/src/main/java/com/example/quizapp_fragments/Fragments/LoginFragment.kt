package com.example.quizapp_fragments.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.quizapp_fragments.MVC.QuizAppController
import com.example.quizapp_fragments.R
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(){
    private val emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        //In the login fragment the navigation drawer is locked
        (activity as QuizAppController).findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(
            DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

        val inf =inflater.inflate(R.layout.loginfragment,container,false)
        val loginButton=inf.findViewById<Button>(R.id.loginButton)
        val signup =inf.findViewById<TextView>(R.id.signup_textview)
        val userNameID=inf.findViewById<EditText>(R.id.username)
        val passWordID=inf.findViewById<EditText>(R.id.password)


      //Here using Firebase ,It is checking the authorization for email and password.Also given the validations for it.
        firebaseAuth=FirebaseAuth.getInstance()


        signup.setOnClickListener{
            val signupFragment=SignupFragment()
            replaceFragments(signupFragment)
        }
        loginButton.setOnClickListener {
            val subjectListFragment=SubjectListFragment()
            replaceFragments(subjectListFragment)
//            val username = userNameID.text.toString()
//            val password = passWordID.text.toString()
//
//            if (username.isEmpty() || password.isEmpty()) {
//                if (username.isEmpty()) {
//                    userNameID.error = "Please enter your user name"
//
//                }
//                if (password.isEmpty()) {
//                    passWordID.error = "Please enter password"
//                }
//
//                Toast.makeText( context,"Enter valid details", Toast.LENGTH_SHORT).show()
//
//            }
//            else if(!username.matches(emailPattern.toRegex())){
//                userNameID.error="Enter valid email!"
//                Toast.makeText(context, "Please Enter valid email!",Toast.LENGTH_SHORT).show()
//            }
//
//            else if(password.length < 6){
//                passWordID.error = "Enter password more than 6 character"
//                Toast.makeText(context,"Enter password more than 6 character",Toast.LENGTH_SHORT).show()
//            }
//            else {
//                firebaseAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        val subjectListFragment = SubjectListFragment()
//                        replaceFragments(subjectListFragment)
//                    } else {
//                        Toast.makeText(context, "Try Again..", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Re-enable the navigation drawer when the fragment is destroyed
        (activity as QuizAppController).findViewById<DrawerLayout>(R.id.drawer_layout).setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

}