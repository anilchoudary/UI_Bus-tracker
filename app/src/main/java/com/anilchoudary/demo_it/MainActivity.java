package com.anilchoudary.demo_it;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN=0;
    public Button sign;
    public Button driver;
    public Button user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            Log.d("Auth",auth.getCurrentUser().getEmail());
            //User already signed in
        }
        else {
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder().setLogo(R.drawable.logo)
                            .setAvailableProviders(Arrays.asList
                                    (new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()
                                    )
                            ).build(), RC_SIGN_IN);
        }

       sign=(Button) findViewById(R.id.button2);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                finish();
                /*if(view.getId()==R.id.button2){
                    AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Log.d("Auth","User Loggeed out");
                            finish();
                        }
                    });
                }*/

            }
        });

        driver =(Button)findViewById(R.id.button3);
        driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(getApplicationContext(),layout_driver.class);
                startActivity(d);
            }
        });

        user = (Button)findViewById(R.id.button4);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent u = new Intent(getApplicationContext(),trip_activity.class);
                startActivity(u);
            }
        });
    }

}