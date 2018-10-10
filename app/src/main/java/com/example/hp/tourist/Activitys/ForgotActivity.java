package com.example.hp.tourist.Activitys;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.tourist.R;
import com.example.hp.tourist.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etMail;
    private Button btnRecuperar;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        init();
    }

    private void init(){
        etMail = findViewById(R.id.txtEmailF);
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View view) {
        int  vista = view.getId();
        switch (vista){
            case R.id.btnRecuperar:{
                 if(!Util.emptyCampMSG(etMail,"Ingrese un correo "))
                break;
            }
        }
    }

    private void recuperarPass(String mail){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress =mail;
        progressDialog.setMessage("Enviando correo electrónico de restablecimiento de contraseña");
        progressDialog.show();
        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotActivity.this,"Se ha enviado un correo de restablecimiento a su correo",Toast.LENGTH_LONG).show();
                            etMail.setText("");
                        }else{
                            Toast.makeText(ForgotActivity.this,"Ups! algo salio mal, ¿ya estas registrado?",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}
