package cl.alc.mqtt;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    static String MQTTHOST = "tcp://192.168.1.9:1883    ";
    static String USERNAME = "N";
    static String PASSWROD = "Jehucalle123.";
    private MqttAndroidClient client;
    private final MemoryPersistence persistence = new MemoryPersistence();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String clienteId = MqttClient.generateClientId();
        client = new MqttAndroidClient(this.getApplicationContext(), MQTTHOST, clienteId);
         MqttConnectOptions options = new MqttConnectOptions();
         options.setUserName(USERNAME);
         options.setPassword(PASSWROD.toCharArray());
        Button btn = findViewById(R.id.BtnEnvi);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String clientId = MqttClient.generateClientId();
        final MqttAndroidClient client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://192.168.1.9:1883",
                        clientId);
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast toast =
                            Toast .makeText(getApplicationContext(),"Conexion exitosa",Toast.LENGTH_SHORT);
                    toast.show();
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Toast toast =
                            Toast .makeText(getApplicationContext(),"Fallo de conexion",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public void TestUno(View view){
        String topic = "/test";
        String message = "L1";
        try{
            client.publish(topic,message.getBytes(),0,false);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}