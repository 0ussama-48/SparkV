package com.example.sparkv_v1.Helper;

import android.content.Context;
import android.widget.Toast;


import com.example.sparkv_v1.CLIENTE.Adaptadores.PopularDomain;

import java.util.ArrayList;

public class ManagmentCarro {
    private Context context;
    private TinyDB tinyDB;

    public ManagmentCarro(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertServicio(PopularDomain item){
        ArrayList<PopularDomain> listaServicios =getListaCarro();
        boolean existe=false;
        int n = 0;
        for (int i = 0; i < listaServicios.size(); i++) {
            if (listaServicios.get(i).equals(item.getTitle())){
                existe = true;
                n = i;
                break;
            }
        }
        if(existe){
            listaServicios.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listaServicios.add(item);
        }
        tinyDB.putListObject("ListaCarro",listaServicios);
        Toast.makeText(context, "Añadido a tu carro", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<PopularDomain> getListaCarro(){
        return tinyDB.getListObject("listaCarro");
    }
}
