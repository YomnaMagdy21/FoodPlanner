package com.example.foodplanner.signup.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.model.Client;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpFragment extends Fragment {


    TextView login;
    EditText username,email,password,confirmPassword;
    Button signUp;
    String emailPattern="[a-zA-Z-0-9._-]+@[a-z]+\\.+[a-z]+";

    private FirebaseAuth auth;
    private ProgressBar progressbar;
    private DatabaseReference mDatabase;

    public SignUpFragment() {
        // Required empty public constructor
    }

       public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sign_up, container, false);
        username=view.findViewById(R.id.editTxtEmail);
        email=view.findViewById(R.id.email);
        password=view.findViewById(R.id.password);
        confirmPassword=view.findViewById(R.id.confirm);
        signUp=view.findViewById(R.id.btnLogin);
        login=view.findViewById(R.id.loginTxt);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newUser();
            }
        });




        return view;    }
    private void newUser()
    {
        String strUsername=username.getText().toString();
        String strEmail = email.getText().toString();
        String strPassword = password.getText().toString();
        String strConfirmPassword = confirmPassword.getText().toString();

        if (TextUtils.isEmpty(strEmail)){
            email.setError("Email cannot be empty");
            password.requestFocus();
        }else if (TextUtils.isEmpty(strPassword)){
            email.setError("Password cannot be empty");
            password.requestFocus();
        }else if(!strEmail.matches(emailPattern)){
            email.setError("Invalid Email ");
            email.requestFocus();

        }else if(password.length()<3){
            password.setError("password should be more than 7 character");
            password.requestFocus();
        }else if (!strPassword.equals(strConfirmPassword)){
            confirmPassword.setError("not equal");
            confirmPassword.requestFocus();

        }
        else{
            auth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getContext(), "User registered successfully", Toast.LENGTH_SHORT).show();
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Client");

                        writeNewUser(strUsername,strEmail,strPassword);

                        startActivity(new Intent(getContext(), MainActivity.class));
                    }else{
                        Toast.makeText(getContext(), "Registration Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public void writeNewUser(String username, String email, String password) {
        Client client=new Client(username,email,password);
        // mDatabase.child("Client").child(username).setValue(c);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child(user.getUid()).setValue(client);

    }



}