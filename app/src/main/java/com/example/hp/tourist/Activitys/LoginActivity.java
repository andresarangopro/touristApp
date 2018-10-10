package com.example.hp.tourist.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.tourist.NavigationActivity;
import com.example.hp.tourist.R;
import com.example.hp.tourist.Clases.Transacciones;
import com.example.hp.tourist.Util;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnIngresar;
    private EditText tvMail, tvPassword;
    private TextView tvRegistrate, tvForgotPass;
    public static boolean calledAlready = false;
    private Transacciones tr = new Transacciones();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tr.inicializatedFireBase(this);
        init();
    }

    public void init() {
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
        tvMail = findViewById(R.id.txtEmail);
        tvForgotPass = findViewById(R.id.tvOlvidastePass);
        tvForgotPass.setOnClickListener(this);
        tvPassword = findViewById(R.id.txtPassword);
        progressDialog = new ProgressDialog(this);
        tvRegistrate = findViewById(R.id.tvRegistrate);
        tvRegistrate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int v = view.getId();
        switch (v) {
            case R.id.btnIngresar: {
                String mail = Util.getTxt(tvMail);
                String pass = Util.getTxt(tvPassword);
                if(!Util.emptyCampMSG(tvMail,"Correo vacío") && !Util.emptyCampMSG(tvPassword, "Contraseña vacía")){
                    loginUser(mail, pass);
                }
                break;
            }
            case R.id.tvRegistrate:{
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
            }
            case R.id.tvOlvidastePass:{
                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
                break;
            }
        }
    }

    /**
     * andres
     *
     * @param mail
     * @param pass
     */
    private void loginUser(String mail, String pass) {
        progressDialog.setMessage("Ingresando, por favor espera");
        progressDialog.show();
        tr.loguearse(mail,pass,LoginActivity.this,  NavigationActivity.class,this,progressDialog);
    }
}