package com.example.hp.tourist.Clases;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.hp.tourist.NavigationActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.example.hp.tourist.Activitys.LoginActivity.calledAlready;


public class Transacciones {

    /////////////////////
    //Variables
    ////////////////////

    public DatabaseReference databaseReference;
    public FirebaseAuth firebaseAuth;
    public FirebaseDatabase firebaseDatabase;
    public ProgressDialog progressDialog;
    public FirebaseUser firebaseUser;
    public StorageReference mStorage;
    public FirebaseUser user ;
    ///////////////////
    //Constructor
    //////////////////

    public Transacciones() { }

    /////////////////////
    //Metodos
    ////////////////////

    /**
     * Metodo que inserta cualquier objeto en la base de datos en el nodo que se pasa como
     * parameto
     *  @param childDatabaseR direccion del nodo donde se guardará el objeto
     * @param key            llave primaria del objeto
     * @param object         objeto a guardar
     */

    private Task<Void> insertar(String childDatabaseR, String key, Object object) {
        return databaseReference.child(childDatabaseR).child(key).setValue(object);
    }

    public void inicializatedFireBase(Context context){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        mStorage = FirebaseStorage.getInstance().getReference();
        if (!calledAlready) {
            firebaseDatabase.setPersistenceEnabled(true);
            calledAlready = true;
        }
        databaseReference = firebaseDatabase.getReference();
    }

    public void account(final Context context, final Class<NavigationActivity> activityOpen){
        eventSelect("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = firebaseAuth.getCurrentUser();
                context.startActivity(new Intent(context, NavigationActivity.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    /**
     * Metodo que selecciona un dato referenciado el cua se pasa por parametro
     * @param usChildString
     */
    public DatabaseReference eventSelect( String usChildString){
        return databaseReference.child(usChildString);
    }

    public Task<AuthResult> login(String mail,String pass){
        return firebaseAuth.signInWithEmailAndPassword(mail, pass);
    }

    public void loguearse(final String mail, final String pass, final Activity activity,
                          final  Class<NavigationActivity> openActivity, final Context context, final ProgressDialog progressDialog){
        login(mail,pass).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    account(context, openActivity);
                    activity.finish();
                } else {
                    Toast.makeText(context, "Datos errados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

  public void registrarUser(final String email, final String password, final Context context, final String name,  final ProgressDialog pd){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Registrando usuario, por favor espera");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ingresarNuevo(email, password, context, name, pd);
                }else{
                    pd.dismiss();
                }

            }
        });
    }

    private void ingresarNuevo(String email, String password, final Context context, final String name, final ProgressDialog pd) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Usuario uE = new Usuario(name,user.getUid(),user.getEmail());
                    insertar("Users",user.getUid(),uE).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                context.startActivity(new Intent(context, NavigationActivity.class));
                                pd.dismiss();
                            }else{
                                pd.dismiss();
                            }

                        }
                    });
                }
            }
        });
    }





}
