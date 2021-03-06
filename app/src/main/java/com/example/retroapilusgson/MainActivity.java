
package com.example.retroapilusgson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.health.ServiceHealthStats;
import android.util.Log;
import android.widget.TextView;

import com.example.retroapilusgson.Interface.JasonPlaceHolder;
import com.example.retroapilusgson.Model.Post;
import com.example.retroapilusgson.Model.Shipment;
import com.example.retroapilusgson.Model.UTCDateTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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

    String dataBaseName;
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

    // llamado de  la interface q devuelve el metodo GET Retrofit
    private void getInfoShipment() {
                 // Registro del LocalDateTime Converter
                 Gson gson = new GsonBuilder()

                .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
                @Override
                public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                    return LocalDateTime.parse(json.getAsString());
                    //return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy::MM::dd HH::mm::ss"));
                }
            }).create();
             // creacion de Instacia RETROFIT
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.busterminal.octword.net")  // OCTWORD AP
                    .addConverterFactory(GsonConverterFactory.create(gson))         //    GSON CONVERRTER
                    .build();        // Llama a la  clase INTERFAZ
            JasonPlaceHolder jsonPlaceHolder = retrofit.create(JasonPlaceHolder.class);


            Call<Shipment> call = jsonPlaceHolder.getShipmentInfo();
            call.enqueue(new Callback<Shipment>() {
                @Override
                public void onResponse(Call<Shipment> call, Response<Shipment> response) {
                    if (!response.isSuccessful()) {
                        // mesaje de Error
                        mJsoTxtView.setText("Codigo:   " + response.code());
                        return;
                    }
                    //  response del  servidor0

                    String content = "";

                    content = "XKey:" + response.body().getXKey() +
                            "\n SenderCustomerXKey:" + response.body().getSenderCustomerXKey() +
                            "\n ReceiverFullName:" + response.body().getReceiverFullName() +
                            "\n ReceiverMobilPhone:" + response.body().getReceiverMobilPhone() +
                            "\n ReceiverEmail:" + response.body().getReceiverEmail() +
                            "\n XPassword:" + response.body().getXPassword() +
                            "\n TrackingNumber:" + response.body().getTrackingNumber() +
                            "\n XDate:" + response.body().getXDate()+
                            "\n XFrom:" + response.body().getXFrom() +
                            "\n XTo:" + response.body().getXTo() +
                            "\n XContent:" + response.body().getXContent() +
                            "\n DeclaredAmount:" + response.body().getDeclaredAmount() +
                            "\n Fee:" + response.body().getFee() +
                            "\n PayWhenReceived:" + response.body().getPayWhenReceived() +
                            "\n PaymentStatus:" + response.body().getPaymentStatus() +
                            "\n InvoiceXValue:" + response.body().getInvoiceXValue() +
                            "\n ShipmentStatus:" + response.body().getShipmentStatus() +
                            "\n BusXKey:" + response.body().getBusXKey() +
                            "\n BusDriverXKey:" + response.body().getBusDriverXKey();


                    mJsoTxtView.append(content);


                }

                @Override
                public void onFailure(Call<Shipment> call, Throwable t) {
                    mJsoTxtView.setText(t.getMessage());
                }
            });
            {

            }

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

