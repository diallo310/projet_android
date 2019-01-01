package com.example.deptinfo.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button   loginBtn;
    private UserHelper db;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        db = new UserHelper(this);
       login();
       register();
    }

    public void login(){
       email = findViewById(R.id.emailLoginEdit);
       password =findViewById(R.id.passwordEdit);
       loginBtn =findViewById(R.id.btnLogin);
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Cursor resUser = db.findUser(email.getText().toString(),password.getText().toString());
                   try {
                       resUser.moveToFirst();
                       boolean b = resUser.isNull(1);
                       loginBtn.setBackgroundColor(Color.GREEN);
                       Log.v("select",DatabaseUtils.dumpCurrentRowToString(resUser));
                       resUser.close();
                       Intent intent = new Intent(Login.this, MainActivity.class);
                       startActivity(intent);

                   }catch (IndexOutOfBoundsException e){
                       loginBtn.setBackgroundColor(Color.RED);
                       Toast.makeText(getApplicationContext(), "email or password  not exist",Toast.LENGTH_SHORT).show();

                   }

           }

       });
    }

    public void register(){
        register = findViewById(R.id.link_signup);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);

            }
        });
    }

}
