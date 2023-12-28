package com.kevin.proyecto_ma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Vector;

import kevin.proyecto_ma.R;

public class Validar extends AppCompatActivity {

    TextView Nombre, Cedula, Id;
    Button Codigo;
    String nombre, cedula, mensaje, idActivo;
    ArrayList<Activo> activosArrayList = new ArrayList<Activo>();
    private ListView listView;
    Vector<SoapObject> result = null;
    SoapObject response = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validar);
        Nombre = (TextView) findViewById(R.id.txtNombreFuncionarioValidar);
        Cedula = (TextView) findViewById(R.id.txtCedulaFuncionarioValidar);
        listView = (ListView) findViewById(R.id.listActivo);
        Codigo = (Button) findViewById(R.id.btnCamara);
        Id = (TextView) findViewById(R.id.txtCodigoCamara);

        Bundle bundle = this.getIntent().getExtras();
        nombre = bundle.getString("Nombre");
        cedula = bundle.getString("Cedula");
        idActivo = "";
        Nombre.setText(nombre);
        Cedula.setText(cedula);

        Validar.SegundoPlanoLista tarea = new Validar.SegundoPlanoLista();
        tarea.execute();

        //evento click del boton que permite acceder a la camara para capturar codigo qr

        Codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //utilizamos IntentIntegrator que ya viene integrado en android para
                //lectura automatica de codigo qr
                IntentIntegrator integrator = new IntentIntegrator(Validar.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Seleccione - Activo");
                //codigo 0 para camara trasera del celular
                integrator.setCameraId(0);
                //para el sonido al capturar la imagen
                integrator.setBeepEnabled(true);
                //para poder leer codigo de barras y codigo qr
                integrator.setBarcodeImageEnabled(true);
                //inicializamos la camara
                integrator.initiateScan();

            }
        });

        //evento al dar click en la lista de activos
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //obtenemos codigo del activo seleccionado
                idActivo=activosArrayList.get(position).getId();
                //ejecutamos evento en segundo plano para enviar datos a traves del servicio
                Validar.SegundoPlanoActualizar tarea = new Validar.SegundoPlanoActualizar();
                tarea.execute();
            }
        });
    }

    //evento en segundo plano para actualizar el estado del activo
    private class SegundoPlanoActualizar extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void unused) {
            //al actualizar el estado recargamos la lista de activos para ver el cambio de estado
            ListAdapterActivo listAdapterActivo = new ListAdapterActivo(Validar.this,activosArrayList);
            listView.setAdapter(listAdapterActivo);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //actualizamos el estado de revisado a ok
            actualizarActivos();
            //limpiamos lista de activos
            activosArrayList.clear();
            //realizamos la peticion de los activos, ya que se ha actualizado el estado
            convertirActivos();
            return null;
        }
    }


    //funcion que se ejecuta al activar la camara para tomar la captura del codigo qr
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()==null){
                //si se cierra la camara se cancela el proceso
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show();
            }else{
                //obtenemos el codigo generado por el codigo qr
                //este proceso lo hace automaticamente
                String envId=result.getContents();
                idActivo=envId;
                //enviamos este codigo a acutalizar el estado mediante el servicio
                Validar.SegundoPlanoActualizar tarea = new Validar.SegundoPlanoActualizar();
                tarea.execute();

            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    //proceso en segundo plano para traer lista de activos
    private class SegundoPlanoLista extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Void unused) {
            //listamos activos
            ListAdapterActivo listAdapterActivo = new ListAdapterActivo(Validar.this,activosArrayList);
            listView.setAdapter(listAdapterActivo);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //limpiamos array para que no haya datos repetidos

            activosArrayList.clear();
            //llamamos a funcion convertir activos para traer datos de activos
            convertirActivos();
            return null;
        }
    }

    private void convertirActivos(){
        String SOAP_ACTION ="";
        String METHOD_NAME="ListaActivosFuncionario";
        String NAMESPACE="http://Servicios/";
        String URL="http://192.168.1.3:8080/Servicio_MA/Servicio";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        try{
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            Request.addProperty("ci",cedula.toString());

            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);

            result=(Vector<SoapObject>) soapEnvelope.getResponse();

            int length = result.size();
            for (int i = 0; i < length; ++i) {
                SoapObject so = result.get(i);
                Activo activo = new Activo(so.getProperty(1).toString(),so.getProperty(2).toString(),so.getProperty(0).toString());
                activosArrayList.add(activo);

            }


        }catch (Exception ex){
            try {
                response = (SoapObject) soapEnvelope.getResponse();
                Activo activo = new Activo(response.getProperty(1).toString(),response.getProperty(2).toString(),response.getProperty(0).toString());
                activosArrayList.add(activo);
            } catch (SoapFault e) {
               mensaje="ERROR:"+ex.getMessage();

            }

        }
    }

    private void actualizarActivos(){
        String SOAP_ACTION ="";
        String METHOD_NAME="actualizarValidacion";
        String NAMESPACE="http://Servicios/";
        String URL="http://192.168.1.3:8080/Servicio_MA/Servicio";
        SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        try{
            SoapObject Request = new SoapObject(NAMESPACE, METHOD_NAME);
            //ingresamos parametos a enviar al servicio
            Request.addProperty("cedula",cedula.toString());
            Request.addProperty("idActivo",idActivo.toString());

            soapEnvelope.setOutputSoapObject(Request);
            HttpTransportSE transport = new HttpTransportSE(URL);
            transport.call(SOAP_ACTION, soapEnvelope);

            soapEnvelope.getResponse();



        }catch (Exception ex){


        }
    }

}



