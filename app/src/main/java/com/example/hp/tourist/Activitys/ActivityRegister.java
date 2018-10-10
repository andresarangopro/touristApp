package com.example.hp.tourist.Activitys;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.tourist.R;
import com.example.hp.tourist.Clases.Transacciones;
import com.example.hp.tourist.Util;


public class ActivityRegister extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistrar;
    private ProgressDialog pd;
    private EditText txtName,txtEmail, txtPass, txtConfirPass;
    private Transacciones mn = new Transacciones();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        iniciar();
        mn.inicializatedFireBase(this);
    }


    private void iniciar(){
        btnRegistrar = findViewById(R.id.btnRegisterUser);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtRegistroEmail);
        txtPass = findViewById(R.id.txtRegistroPass);
        txtConfirPass = findViewById(R.id.txtRegistroConfirPass);

        btnRegistrar.setOnClickListener(this);
        pd = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegisterUser:{
                if(validarCampos()){
                    if(validarPass()){
                        pd.setMessage("Ingresando, por favor espera");
                        pd.show();
                        mn.registrarUser(Util.getTxt(txtEmail),Util.getTxt(txtPass), ActivityRegister.this, Util.getTxt(txtName),pd);
                    }else{
                        txtConfirPass.setText("Las contraseñas no coinciden");
                    }
                }
                break;
            }
        }
    }

    private boolean validarCampos(){
        return !Util.emptyCampMSG(txtEmail,"Ingrese Correo") && !Util.emptyCampMSG(txtPass,"Ingrese contraseña") &&
                !Util.emptyCampMSG(txtConfirPass, "Ingrese confirmación contraseña") && !Util.emptyCampMSG(txtName,"Ingrese su nombre");
    }

    private boolean validarPass(){
        return Util.getTxt(txtPass).equals(Util.getTxt(txtConfirPass));
    }
}
