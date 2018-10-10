package com.example.hp.tourist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.hp.tourist.Activitys.LoginActivity;
import com.example.hp.tourist.Clases.Transacciones;
import com.example.hp.tourist.Fragments.FragmentRutas;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Transacciones tr = new Transacciones();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tr.inicializatedFireBase(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        FrameLayout fl = findViewById(R.id.FrFragment);
        fl.removeAllViews();
        manager.popBackStack();
        getFragmentManager().popBackStack();
        Fragment fragment = null;



        switch (item.getItemId()) {
            case R.id.nav_rutas: {
                fragment = new FragmentRutas();
                break;
            }
            case R.id.nav_chat: {

                break;
            }
            case R.id.nav_eventos: {

                break;
            }
            case R.id.nav_noticias: {

                break;
            }
            case R.id.nav_perfil: {

                break;
            }
            case R.id.nav_configuracion: {

                break;
            }
            case R.id.nav_logout: {
                cerrarSesion();
                break;
            }
        }


        if(fragment != null) {
            transaction.replace(R.id.FrFragment, fragment);
            transaction.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
    public void cerrarSesion(){
        tr.firebaseAuth.signOut();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}
