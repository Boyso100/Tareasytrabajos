package com.example.boyso996.agregarusuario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarContacto extends AppCompatActivity {
    Button Guardar;
    EditText nombre;
    EditText email;
    EditText twiter;
    EditText tel;
    EditText fec;
    Intent atras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);
        Guardar = (Button) findViewById(R.id.GUARDAR);
        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto alum = new Contacto();
                nombre = (EditText) findViewById(R.id.USUARIO);
                email = (EditText) findViewById(R.id.EMAIL);
                twiter = (EditText) findViewById(R.id.TWITER);
                tel = (EditText) findViewById(R.id.TELEFONO);
                fec = (EditText) findViewById(R.id.FECHA);
                alum.set_nombre(nombre.getText().toString());
                alum.set_email(email.getText().toString());
                alum.set_twiter(twiter.getText().toString());
                alum.set_tel(tel.getText().toString());
                alum.set_fec(fec.getText().toString());
                atras = new Intent(AgregarContacto.this, MainActivity.class);
                atras.putExtra("micontacto", alum);
                setResult(RESULT_OK,atras);
                finish();
            }
        });


    }
}
