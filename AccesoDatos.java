/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.m001.jsonretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author FP Mañana A
 */
class AccesoDatos {
static List<Data> lsitacovid=null;
    static List<Data> recogerDatos() throws Exception{
        
        String baseUrl = "http://datos.comunidad.madrid/";

        //Instancia a GSON
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        
        //Instancia a retrofit agregando la baseURL y el convertidor GSON
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        
        //Se crea el servicio
        OperacionInterface service = retrofit.create(OperacionInterface.class);

        //Se realiza la llamada
        Call<Covid> call = service.estaciones();

        //la llamada es asincronica
        //Retrofit descarga y analiza los datos del API en un subproceso en
        //paralelo y entrega los resultados a traves de los metodos
        // onFailure o onResponse
          
        call.enqueue(new Callback<Covid>() {

            @Override
            public void onFailure(Call<Covid> call, Throwable t) {
               System.out.println("Network Error :: " + t.getLocalizedMessage());
            }

          
            @Override
            public void onResponse(Call<Covid> call, Response<Covid> response) {

                   Covid  articulo = response.body();
                   lsitacovid=articulo.getData();
                  // System.out.println(articulo);
            }
        });
        return lsitacovid;
        
        
    }

    static String pintaHTML(List<Data> listacovid) {
       String html="<html><head></head><body>";
        
        	
            html=html+"<table border=1><tr><th>codigo_geometria</th><th>zona_basica_salud</th><th>tasa_incidencia_acumulada_ultimos_14dias</th><th>tasa_incidencia_acumulada_total</th><th>casos_confirmados_totales</th><th>casos_confirmados_ultimos_14dias</th><th>fecha_informe</th></tr>";            	
					
            for (Data a : listacovid) {
            	
            	//((-100/max)*n_casos+100))
                html=html+"<tr><td>"+a.getCodigoGeometria()+"</td><td>"+a.getZonaBasicaSalud()+"</td><td>"+a.getTasaIncidenciaAcumuladaUltimos14dias()+"</td><td>"+a.getTasaIncidenciaAcumuladaTotal()+"</td><td>"+a.getCasosConfirmadosTotales()+"</td><td>"+a.getCasosConfirmadosUltimos14dias()+"</td><td>"+a.getFechaInforme()+"</td></tr>";
               // System.out.println("Hay casos "+n_casos+" el máximo es "+max+" y el color es: "+color);
            }
            html=html+"</table></body></html>";
        
        return html;
    }

    static void grabarHTML(String html) {
        File f=new File("C:\\TextoNetbeans\\html\\covid.html");
    try {
        FileWriter fr=new FileWriter(f);
        fr.write(html);
        fr.close();
    } catch (IOException ex) {
        Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    }
    

