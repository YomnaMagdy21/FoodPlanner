package com.example.foodplanner.login.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.SplashActivity;
import com.example.foodplanner.firebase.Firebase;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.signup.view.SignUpFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginFragment extends Fragment {

    TextView signUp,email,password;
    Button login,guest;
    private FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    ImageView logo;
    private static final int RC_SIGN_IN = 123;
    private static final String TAG="LOGIN";
    Firebase firebase;
    public static boolean flag=false;
    static SharedPreferences sharedPreferences;
    DatabaseReference mDatabase;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance( ) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.clientID))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        firebase = new Firebase();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        signUp=view.findViewById(R.id.signUpTxt);
        email=view.findViewById(R.id.editTxtEmail);
        password=view.findViewById(R.id.editTxtPassword);
        login=view.findViewById(R.id.btnLogin);
        logo=view.findViewById(R.id.googleLogo);
        guest=view.findViewById(R.id.btnGuest);
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=true;
                //Navigation.findNavController(view).navigate(R.id.homeFragment);
                startActivity(new Intent(getContext(), HomeActivity.class));


            }
        });

        auth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Navigation.findNavController(view).navigate(R.id.signUpFragment);
                flag=false;

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginClient();
                flag=false;
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                flag=false;

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null && account.getIdToken() != null) {
                    handleSignInResult(account.getIdToken());
                    Toast.makeText(getContext(), "login with google successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e(TAG, "Google sign-in failed: ID token is null");
                }
            } catch (ApiException e) {
                Log.e(TAG, "Google sign-in failed: " + e.getStatusCode());
            }
        }
    }


    private void handleSignInResult(String idToken) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();
                        firebase.showFavFromFirebase(user,getContext());
                        firebase.showPlanFromFirebase(user,getContext(),"Sunday");
                        firebase.showPlanFromFirebase(user,getContext(),"Monday");
                        firebase.showPlanFromFirebase(user,getContext(),"Tuesday");
                        firebase.showPlanFromFirebase(user,getContext(),"Wednesday");
                        firebase.showPlanFromFirebase(user,getContext(),"Thursday");
                        firebase.showPlanFromFirebase(user,getContext(),"Friday");
                        firebase.showPlanFromFirebase(user,getContext(),"Saturday");


                       // SplashActivity.setLoggedIn(true);

                        startActivity(new Intent(getContext(), HomeActivity.class));

                    } else {

                        Log.e(TAG, "Google sign-in failed", task.getException());
                    }
                });

    }

    private void loginClient() {
        String Email = email.getText().toString();
        String Password = password.getText().toString();


        if (TextUtils.isEmpty(Email)){
            email.setError("Email cannot be empty");
            email.requestFocus();
        }else if (TextUtils.isEmpty(Password)){
            password.setError("Password cannot be empty");
            password.requestFocus();
        }else{
            auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser firebaseUser=auth.getCurrentUser();
                        firebase.showFavFromFirebase(firebaseUser,getContext());
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Sunday");
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Monday");
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Tuesday");
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Wednesday");
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Thursday");
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Friday");
                        firebase.showPlanFromFirebase(firebaseUser,getContext(),"Saturday");
                      ////  SplashActivity.setLoggedIn(true);

                        Toast.makeText(getContext(), "User logged in successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getContext(), HomeActivity.class));
                    }else{
                        Toast.makeText(getContext(), "Login Error: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}