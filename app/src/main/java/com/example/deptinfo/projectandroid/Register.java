package com.example.deptinfo.projectandroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private UserHelper db;
    private EditText email;
    private EditText password;
    private EditText username;
    private TextView registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        db = new UserHelper(this);
        register();


    }
    public void register(){
        email = findViewById(R.id.registerEmailEdit);
        password =findViewById(R.id.registerPasswordEdit);
        registerBtn =findViewById(R.id.btnRegister);
        username = findViewById(R.id.username);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().equals("")|| password.getText().toString().equals("")||username.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Tous les champs sont obligatoires",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), email.getText().toString(),Toast.LENGTH_SHORT).show();
                    insert(email,password,username);
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void insert(EditText email, EditText password,EditText username){
        try{
            Cursor resEmail = db.findEmail(email.getText().toString());
            resEmail.moveToFirst();
            boolean b = resEmail.isNull(1);
            Toast.makeText(getApplicationContext(), "L'adresse email existe",Toast.LENGTH_SHORT).show();

        }catch (IndexOutOfBoundsException e){
            db.insertUser(email.getText().toString(),password.getText().toString(),username.getText().toString());

        }
    }
}
