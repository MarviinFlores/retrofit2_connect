
package com.example.retroapilusgson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.health.ServiceHealthStats;
import android.util.Log;
import android.widget.TextView;

import com.example.retroapilusgson.Interface.JasonPlaceHolder;
import com.example.retroapilusgson.Model.Post;
import com.example.retroapilusgson.Model.Shipment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private TextView mJsoTxtView;
    private TextView txtTrackingNumber;
    private TextView txtCustomerXKey;
    private TextView txtReceiverFullName;
    private TextView txtXContent;

    String dataBaseName ;
    String trackingNumber;


//    private JasonPlaceHolder JsonPlaceHolderApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mJsoTxtView = findViewById(R.id.jsonText);
        /**txtCustomerXKey = findViewById(R.id.txtCustomerXKey);
        txtReceiverFullName = findViewById(R.id.txtReceiverFullName);
        txtTrackingNumber = findViewById(R.id.txtTrackingNumber);
        txtXContent = findViewById(R.id.txtXcontent);*/

        //getPosts();
        //getShipment();
        getInfoShipment();
        //ShipmentInfoGet();
    }


    private void  getInfoShipment() {

        // creacion de Instacia RETROFIT
                 Retrofit retrofit = new Retrofit.Builder()


                         .baseUrl("https://api.busterminal.octword.net")  // OCTWORD AP
                         .addConverterFactory(GsonConverterFactory.create())         //    GSON CONVERRTER
                         .build();
        // Llama a la  clase INTERFAZ
        JasonPlaceHolder jsonPlaceHolder = retrofit.create(JasonPlaceHolder.class);
        //Call<List<Shipment>>  call = jsonPlaceHolder.ShipmentInfoGet("OWDATABASE_BUSTERMINAL_1G","0124577778"); // query
        //Call<List<Shipment>>  call = jsonPlaceHolder.getInfoShipment("OWDATABASE_BUSTERMINAL_1G","0124577778");// path

        //Call<List<Shipment>>  call = jsonPlaceHolder.getInfoShipment(dataBaseName,trackingNumber);
        Call<List<Shipment>>  call = jsonPlaceHolder.getShipmentInfo();
        call.enqueue(new Callback<List<Shipment>>() {
        @Override
            public void onResponse(Call<List<Shipment>> call, Response<List<Shipment>> response) {
                if (!response.isSuccessful()){
                    mJsoTxtView.setText("Codigo:   "+response.code());
                    return;
                }
                List<Shipment>  infoTrack = response.body8  (); // respuesta del servidor

                 for ( Shipment  trackInfo : infoTrack){
                 String content = "";
                     
                 content+= "XKey:" + trackInfo.getXKey()+"\n";
                 content+="SenderCustomerXKey:" + trackInfo.getSenderCustomerXKey()+"\n";
                 content+="ReceiverFullName:" +trackInfo.getReceiverFullName()+"\n";
                 content+="ReceiverMobilPhone:"+trackInfo.getReceiverMobilPhone()+"\n";
                 content+="ReceiverEmail:"+trackInfo.getReceiverEmail()+"\n";
                 content+="XPassword:"+trackInfo.getXPassword()+"\n";
                 content+="TrackingNumber:" + trackInfo.getTrackingNumber()+"\n";
                 content+="XDate:"+trackInfo.getXDate()+"\n";
                     content+="XFrom:"+trackInfo.getXFrom()+"\n";
                     content+="XTo:"+trackInfo.getXTo()+"\n";
                     content+="XContent:"+trackInfo.getXContent()+"\n";
                     content+="DeclaredAmount:"+trackInfo.getDeclaredAmount()+"\n";
                     content+="Fee:"+trackInfo.getFee()+"\n";
                     content+="PayWhenReceived:"+trackInfo.getPayWhenReceived()+"\n";
                     content+="PaymentStatus:"+trackInfo.getPaymentStatus()+"\n";
                     content+="InvoiceXValue:"+trackInfo.getInvoiceXValue()+"\n";
                     content+="ShipmentStatus:"+trackInfo.getShipmentStatus()+"\n";
                     content+="BusXKey:"+trackInfo.getBusXKey()+"\n";
                     content+="BusDriverXKey:"+trackInfo.getBusDriverXKey()+"\n";



                 mJsoTxtView.append(content);

                 }

            }

            @Override
            public void onFailure(Call<List<Shipment>> call, Throwable t) {
                mJsoTxtView.setText(t.getMessage());
            }
        });

    }
}




        /**Call<Shipment> call = jsonPlaceHolder.ShipmentInfoGet( "OWDATABASE_BUSTERMINAL_1G", "0124577778");
        call.enqueue (new Callback<Shipment>() {
            @Override
            public void onResponse(Call<Shipment> call, Response<Shipment> response) {


            }

            @Override
            public void onFailure(Call<Shipment> call, Throwable t) {
            Log.e("Error:    ", toString());
            }


        });

    }
         Call<List<Post>>  call = jsonPlaceHolder.getPosts();
         call.enqueue(new Callback<List<Post>>() {
        @Override
        public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if (!response.isSuccessful()){
        mJsoTxtView.setText("Codigo:   "+response.code());
        return;
        }
        List<Post> postList = response.body(); // respuesta del servidor
        /**RECORRIDO DE CADA UNO D 100 ELEMENTOS EN LA RESPUESTA DEL SERVER
                for ( Post  post : postList){
                        String content = "";
                        content+="userID:" + post.getUserID()+"\n";
                        content+="id:" + post.getId()+"\n";
                        content+="title:" + post.getTitle()+"\n";
                        content+="body:" + post.getBody()+"\n\n";
                        mJsoTxtView.append(content);

                        }
                        }

                        */

