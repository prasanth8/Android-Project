package com.example.aswin.learningapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;


public class Details extends FragmentActivity implements GoogleApiClient.OnConnectionFailedListener {
    GoogleApiClient mGoogleApiClient;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    CircleMenu circleMenu;
    EditText name;
    EditText mobile_no;
    EditText linked_in;
    EditText password;
    Button next;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        //set button
        name=(EditText) findViewById(R.id.editText3);
        mobile_no=(EditText) findViewById(R.id.editText4);
        linked_in=(EditText) findViewById(R.id.editText5);
        next=(Button) findViewById(R.id.button4);
        password=(EditText) findViewById(R.id.editText6);
        //end button
//Circle Menu Part Start
        try {
            circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

            circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.plus, R.drawable.plus);
            circleMenu.addSubMenu(Color.parseColor("#258CFF"), R.drawable.t);
            circleMenu.addSubMenu(Color.parseColor("#30A400"), R.drawable.l);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Something",Toast.LENGTH_SHORT).show();
        }

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {

                                                 @Override
                                                 public void onMenuSelected(int index) {
                                                     switch (index) {
                                                         case 0:
                                                             Toast.makeText(Details.this, "T Button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;
                                                         case 1:
                                                             Toast.makeText(Details.this, "L button Clicked", Toast.LENGTH_SHORT).show();
                                                             break;

                                                     }
                                                 }
                                             }

        );

        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

                                                     @Override
                                                     public void onMenuOpened() {
                                                         Toast.makeText(Details.this, "Menu Opend", Toast.LENGTH_SHORT).show();
                                                     }

                                                     @Override
                                                     public void onMenuClosed() {
                                                         Toast.makeText(Details.this, "Menu Closed", Toast.LENGTH_SHORT).show();
                                                     }
                                                 }
        );




        //Circle Menu Part End
        //Google SignIn Part Start
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(getApplicationContext(),acct.getDisplayName()+" "+acct.getEmail()+" "+acct.getId(),Toast.LENGTH_SHORT).show();

        }


next.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View v) {
        if(name.getText().toString()!=null&&mobile_no.getText().toString()!=null&&linked_in.getText().toString()!=null&&(password.getText().toString()).length()>5){
            Intent intent=new Intent(Details.this,Current_Location.class);
            startActivity(intent);
        }
    }
});


    }


    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened())
            circleMenu.closeMenu();
        else
            finish();
    }
}