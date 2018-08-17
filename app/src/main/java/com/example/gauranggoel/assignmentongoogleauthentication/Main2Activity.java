package com.example.gauranggoel.assignmentongoogleauthentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main2Activity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        firebaseAuth = FirebaseAuth.getInstance();

        final Bundle b= getIntent().getExtras();
        final TextView textView= (TextView) findViewById(R.id.textView);
        final TextView textView2= (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(R.id.button2);

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user!=null)
                {
                    if(user.getDisplayName()!=null)
                    {

                        String k = user.getDisplayName().toString();
                        String k1= user.getEmail().toString();
                        Toast.makeText(Main2Activity.this, k+"\n"+k1, Toast.LENGTH_SHORT).show();
                        textView.setText(k);
                        textView2.setText(k1);

                    }
                }
            }
        };




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                firebaseAuth.removeAuthStateListener(authStateListener);
                /*
                Auth.GoogleSignInApi.signOut(new MainActivity().getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Toast.makeText(Main2Activity.this, "Sign Out succesfully", Toast.LENGTH_SHORT).show();
                    }
                });*/



                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
