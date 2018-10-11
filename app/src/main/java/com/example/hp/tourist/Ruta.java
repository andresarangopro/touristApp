package com.example.hp.tourist;

import com.example.hp.tourist.Clases.MineLatLng;
import com.google.android.gms.maps.model.LatLng;

public class Ruta implements FirebaseItems {

    private MineLatLng puntoInicio;
    private MineLatLng puntoFinal;
    private String fecha;
    private String idUsuario;


    public Ruta(MineLatLng puntoInicio, MineLatLng puntoFinal, String fecha, String idUsuario) {
        this.puntoInicio = puntoInicio;
        this.puntoFinal = puntoFinal;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
    }

    public Ruta(){}


    public MineLatLng getPuntoInicio() {
        return puntoInicio;
    }

    public void setPuntoInicio(MineLatLng puntoInicio) {
        this.puntoInicio = puntoInicio;
    }

    public MineLatLng getPuntoFinal() {
        return puntoFinal;
    }

    public void setPuntoFinal(MineLatLng puntoFinal) {
        this.puntoFinal = puntoFinal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String getFirebaseNodeName() {
        return "Rutas";
    }
}
