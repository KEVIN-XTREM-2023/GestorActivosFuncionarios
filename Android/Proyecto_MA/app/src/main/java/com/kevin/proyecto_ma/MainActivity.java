package com.kevin.proyecto_ma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import kevin.proyecto_ma.R;

//import com.kevin.proyecto_ma.databinding.ActivityMainBinding;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private TextView txt1;
    String mensaje;
    Vector<SoapObject> result = null;
    SoapObject response = null;
    private ListView listView;
    ArrayList<Persona> personaArrayList;
    ArrayList<String> arraySpinner;
    Spinner spinner;
    String nombreSeleccion="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inicializamos boton actualizar
        btn1 = (Button) findViewById(R.id.button_listar);
        txt1 = (TextView) findViewById(R.id.txt_listar);
        //Inicializamos array para llenar lista de funcionarios y validaciones
        personaArrayList = new ArrayList<>();
        arraySpinner = new ArrayList<>();
        //Inicializamos listview y spinnes(para lista de validaciones)
        listView = (ListView) findViewById(R.id.listPersona);
        spinner = (Spinner) findViewById(R.id.spinner);

        //Ejecutamos proceso para llenar valiciones, lo realizamos en segundo plano
        SegundoPlano tarea = new SegundoPlano();
        tarea.execute();

        //evento click en cada item de la lista de funcionarios
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Ingresamos parametros del activity a utilizar
                Intent intent = new Intent(MainActivity.this, Validar.class);
                //obtenemos valores almacenados en el array que corresponde a la posicion de item seleccionado
                String nom=personaArrayList.get(position).getNombre();
                String ape=personaArrayList.get(position).getApellido();
                //guardamos en una variable la union del nombre y apellido
                String nomenv = nom +" "+ ape;
                //utilizamos putExtra para enviar datos al nuevo activity
                intent.putExtra("Nombre",nomenv);
                intent.putExtra("Cedula",personaArrayList.get(position).getCedula());
                //abrimos nuevo activity
                startActivity(intent);
            }
        });

        //evento click en el boton de actualziar
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Ejecutamos proceso para llenar valiciones, lo realizamos en segundo plano
                SegundoPlano tarea = new SegundoPlano();
                tarea.execute();
            }
        });

        //evento seleccionar item donde estan las validaciones
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //seleccionamos nombre que se encuentra en la posicion seleccionada
                nombreSeleccion=spinner.getItemAtPosition(position).toString();
                //ejecutamos proceso en segundo plano para cargar lista de funcionarios
                SegundoPlanoLista tareaDos = new SegundoPlanoLista();
                tareaDos.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //proceso en segundo plano
    private class SegundoPlano extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {

            //almacenamos en un tipo adapter la lista de validaciones
            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(getApplicationContext(),  android.R.layout.simple_spinner_item, arraySpinner);

            adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
            //enviamos al spinner la lista ingresada en el adapter
            spinner.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //limpiamos el array para que no se repitan los datos
            arraySpinner.clear();
            //llamamos a la funciona prar traer validaciones desde el servicio
            convertirValFuncionarios();
            return null;
        }
    }

    //funcion para proceso en segundo plano
    private class SegundoPlanoLista extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void unused) {
            //ubicamos en el listview la lista de funcionaros que ingresan desde el servicio
            ListAdapterPersona listAdapterPersona = new ListAdapterPersona(MainActivity.this,personaArrayList);
            listView.setAdapter(listAdapterPersona);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //limpiamos array de funcionarios para que exista repetidos
            personaArrayList.clear();
            //llamamos a la funciona prar traer funcionarios desde el servicio
            convertirFuncionarios();

            return null;
        }
    }

    private void convertirValFuncionarios(){
        //parametros para conectarnos al servicio
        String SOAP_ACTION ="";
        String METHOD_NAME="ListaValidaciones";
        String NAMESPACE="http://Servicios/";
        String URL="http://192.168.1.3:8080/Servicio_MA/Servicio";
        try{
            //declaramos e inicializamos objeto tipo SOAP
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            //seleccionamos version de soap
            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            //ingresamos datos principales a proceso de envio
            soapEnvelope.setOutputSoapObject(Request);
            //enviamos datos por http y pedimos resultado
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);
            //obtenemos resultado como vector
            result=(Vector<SoapObject>) soapEnvelope.getResponse();
            //verificamos la longitud del vector para recorrerlo y guardarlo en un array
            //que sera utilizado para mostrar la informacion
            int length = result.size();
            for (int i = 0; i < length; ++i) {
                SoapObject so = result.get(i);
                mensaje=so.getProperty(0).toString();
                arraySpinner.add(so.getProperty(0).toString());
            }

        }catch (Exception ex){
            mensaje="ERROR:"+ex.getMessage();
        }
    }

    private void convertirFuncionarios(){
        String SOAP_ACTION ="";
        String METHOD_NAME="listaValidacion";
        String NAMESPACE="http://Servicios/";
        String URL="http://192.168.1.3:8080/Servicio_MA/Servicio";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        try{
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("val",nombreSeleccion.toString());

            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);

            result=(Vector<SoapObject>) soapEnvelope.getResponse();

            int length = result.size();
            for (int i = 0; i < length; ++i) {
                SoapObject so = result.get(i);
                    mensaje=so.getProperty(0).toString() + " " +
                            so.getProperty(2).toString();
                Persona persona = new Persona(so.getProperty(1).toString(),so.getProperty(3).toString(),so.getProperty(0).toString(),so.getProperty(2).toString());
                personaArrayList.add(persona);

            }


        }catch (Exception ex){
            try {
                response = (SoapObject) soapEnvelope.getResponse();
                Persona persona = new Persona(response.getProperty(1).toString(),response.getProperty(3).toString(),response.getProperty(0).toString(),response.getProperty(2).toString());
                personaArrayList.add(persona);
            } catch (SoapFault e) {
                mensaje="ERROR:"+ex.getMessage();

            }

        }
    }

}