/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m001.jsonretrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author FP Mañana A
 */
public class Operacion {
      private static final String url = "https://datos.comunidad.madrid/catalogo/dataset/3a1ab315-c3a5-405c-b2c8-8586e272d97b/resource/0a8cf335-46d9-46f6-a88e-5f70337ff5f2/download/calidad_aire_estaciones.json";

    private static final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


     private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                   ;
}
