package com.example.boyso996.agregarusuario;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.boyso996.agregarusuario.R.id.lbcontactos;
public class MainActivity extends AppCompatActivity {

    private ListView listacontactos;
    private ArrayAdapter<String>adapter;

    private Button agregarContacto;
    private TextView lb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listacontactos=(ListView) findViewById(R.id.Listw);
        agregarContacto=(Button) findViewById(R.id.Agregar);
        agregarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i= new Intent(getApplication(),AgregarContacto.class);
                i.putStringArrayListExtra("arr",list);
                startActivityForResult(i,2);
            }
        });
    }
    ArrayList <String> list=new ArrayList<>();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            Contacto objeto;
            objeto = (Contacto) data.getExtras().getSerializable("micontacto");
            list.add(objeto.get_nombre()+" ----"+objeto.get_email()+" ---" +objeto.get_twiter()+" ---"+objeto.get_tel()+"--- "+objeto.get_fec());

            ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            listacontactos.setAdapter(adp);

        } else {

        }

    }
}
