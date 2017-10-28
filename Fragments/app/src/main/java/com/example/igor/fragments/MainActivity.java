package com.example.igor.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.igor.fragments.fragments.BottomFragment1;
import com.example.igor.fragments.fragments.BottomFragment2;
import com.example.igor.fragments.fragments.CentralFragment1;
import com.example.igor.fragments.fragments.CentralFragment2;
import com.example.igor.fragments.fragments.TopFragment1;
import com.example.igor.fragments.fragments.TopFragment2;

import java.util.HashMap;


public class MainActivity extends Activity {
    /**
     * Primer nivel de commits, en este nivel guardaremos los commits que se realicen para mostrar
     * el fragment en la columna central.
     */
    private final static int FIRST_COMMIT = 1;
    /**
     * Segundo nivel de commits, en este nivel guardaremos los commits que se realicen para mostrar
     * el fragment en la columna de la derecha.
     */
    private final static int SECOND_COMMIT = 2;

    /**
     * Mapa en el que vamos a guardar la referencia del nivel en el que se realiza un commit, y qué
     * commit se ha realizado.
     */
    private HashMap<Integer, Integer> commits;

    private FragmentManager fragManager;
    private CentralFragment1 fragCentral1;
    private CentralFragment2 fragCentral2;
    private TopFragment1 fragTop1;
    private TopFragment2 fragTop2;
    private BottomFragment1 fragBottom1;
    private BottomFragment2 fragBottom2;
    private View leftColumn, centerColumn, rightColumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this,"se ejecuto el metodo ocreate",Toast.LENGTH_LONG).show();
        fragManager = getFragmentManager();

        leftColumn = findViewById(R.id.mainFragment);
        centerColumn = findViewById(R.id.flCentral);
        rightColumn = findViewById(R.id.llRight);

        fragManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                // Comprobar el estado de los commits para eliminar commits del mapa.
                checkCommits();

                // Comprobar el estado de la pantalla y mostrar la columna debida.
                setLayoutParams();
            }
        });

        commits = new HashMap<Integer, Integer>();
        setLayoutParams();
    }

    // definimos el evento callbacks de eventos a la actividad onPause


    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"se ejecuto el metodo opause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"se ejecuto el metodo onrestart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"se ejecuto el metodo onresume",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"se ejecuto el metodo onstart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"se ejecuto el metodo onDestroy",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"se ejecuto el metodo onStop",Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Siempre que cambie la configuración del dispositivo establecemos el layout de nuevo.
        // Este evento se lanzará cuando el dispositivo cambie de orientación.
        setLayoutParams();
        super.onConfigurationChanged(newConfig);
    }

    /**
     * Comprobar el estado de los commits para eliminar commits del mapa.
     */
    private void checkCommits() {
        // Comprobar si en el mapa existe el segundo nivel de commits, de ser así comprobar si
        // alguno de los fragments que se encuentran en este nivel está en pantalla.
        if (commits.get(SECOND_COMMIT) != null && ((fragTop1 == null || !fragTop1.isVisible()) &&
                (fragTop2 == null || !fragTop2.isVisible()) &&
                (fragBottom1 == null || !fragBottom1.isVisible()) &&
                (fragBottom2 == null || !fragBottom2.isVisible()))) {
            // Existe el commit en el mapa, pero no hay fragment en la última columna.
            // Eliminar commit.
            commits.remove(SECOND_COMMIT);
        }

        // Hacer la misma tarea con el primer nivel, pero comprobar primero si existe un commit
        // de nivel superior.
        if (commits.get(SECOND_COMMIT) == null && commits.get(FIRST_COMMIT) != null &&
                ((fragCentral1 == null || !fragCentral1.isVisible()) &&
                        (fragCentral2 == null || !fragCentral2.isVisible()))) {
            // Existe el commit en el mapa, pero no hay fragment en la columna central.
            // Eliminar commit.
            commits.remove(FIRST_COMMIT);
        }
    }

    /**
     * Comprueba que la orientación del dispositivo y los commits realizados para mostrar la
     * columna
     * debida o todas las columnas.
     */
    private void setLayoutParams() {
        // Estando en portrait muestra la última columna que contenga un fragment.
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            //Si existe un commit en este nivel es que se está mostrando el fragment en la
            // columna de la derecha, por lo tanto se ocultan las demás y se muestra esta.
            if (commits.get(SECOND_COMMIT) != null) {
                // Se establece el ancho de la primera columna a 0.
                LinearLayout.LayoutParams leftColParams =
                        (LinearLayout.LayoutParams) leftColumn.getLayoutParams();
                leftColParams.width = 0;
                leftColParams.weight = 0;
                leftColumn.setLayoutParams(leftColParams);

                // Se establece el ancho de la segunda columna a 0.
                LinearLayout.LayoutParams centerColParams =
                        (LinearLayout.LayoutParams) centerColumn.getLayoutParams();
                centerColParams.width = 0;
                centerColParams.weight = 0;
                centerColumn.setLayoutParams(centerColParams);

                // Se establece el ancho de la última columna al ancho de pantalla.
                LinearLayout.LayoutParams rightColParams =
                        (LinearLayout.LayoutParams) rightColumn.getLayoutParams();
                rightColParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                rightColParams.weight = 1;
                rightColumn.setLayoutParams(rightColParams);

            } else if (commits.get(FIRST_COMMIT) != null) {

                // Se establece el ancho de la primera columna a 0.
                LinearLayout.LayoutParams leftColParams =
                        (LinearLayout.LayoutParams) leftColumn.getLayoutParams();
                leftColParams.width = 0;
                leftColParams.weight = 0;
                leftColumn.setLayoutParams(leftColParams);

                // Se establece el ancho de la segunda columna al ancho de pantalla.
                LinearLayout.LayoutParams centerColParams =
                        (LinearLayout.LayoutParams) centerColumn.getLayoutParams();
                centerColParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                centerColParams.weight = 1;
                centerColumn.setLayoutParams(centerColParams);

                // Se establece el ancho de la tercera columna a 0.
                LinearLayout.LayoutParams rightColParams =
                        (LinearLayout.LayoutParams) rightColumn.getLayoutParams();
                rightColParams.width = 0;
                rightColParams.weight = 0;
                rightColumn.setLayoutParams(rightColParams);

            } else {
                // Se establece el ancho de la primera columna al ancho de pantalla.
                LinearLayout.LayoutParams leftColParams =
                        (LinearLayout.LayoutParams) leftColumn.getLayoutParams();
                leftColParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
                leftColParams.weight = 1;
                leftColumn.setLayoutParams(leftColParams);

                // Se establece el ancho de la segunda columna a 0.
                LinearLayout.LayoutParams centerColParams =
                        (LinearLayout.LayoutParams) centerColumn.getLayoutParams();
                centerColParams.width = 0;
                centerColParams.weight = 0;
                centerColumn.setLayoutParams(centerColParams);

                // Se establece el ancho de la tercera columna a 0.
                LinearLayout.LayoutParams rightColParams =
                        (LinearLayout.LayoutParams) rightColumn.getLayoutParams();
                rightColParams.width = 0;
                rightColParams.weight = 0;
                rightColumn.setLayoutParams(rightColParams);
            }

        } else {
            // El se establece el peso de cada columna a 1 para que todas ocupen el mismo
            // espacio en pantalla.
            LinearLayout.LayoutParams leftColParams =
                    (LinearLayout.LayoutParams) leftColumn.getLayoutParams();
            leftColParams.width = 0;
            leftColParams.weight = 1;
            leftColumn.setLayoutParams(leftColParams);

            LinearLayout.LayoutParams centerColParams =
                    (LinearLayout.LayoutParams) centerColumn.getLayoutParams();
            centerColParams.width = 0;
            centerColParams.weight = 1;
            centerColumn.setLayoutParams(centerColParams);

            LinearLayout.LayoutParams rightColParams =
                    (LinearLayout.LayoutParams) rightColumn.getLayoutParams();
            rightColParams.width = 0;
            rightColParams.weight = 1;
            rightColumn.setLayoutParams(rightColParams);
        }
    }

    public void showCenterFrag1(View v) {
        // Comprobar que no se encuentra ya en pantalla el fragment.
        if (fragCentral1 == null || !fragCentral1.isVisible()) {
            // Si existe un commit al mismo nivel al que vamos a asignar este fragment
            // eliminamos de la pila lo que exista a partir de ese commit.
            if (commits.get(FIRST_COMMIT) != null) {
                // El primer parámetro identifica el commit, el segundo indica que
                // también se elimine el commit indicado.
                fragManager.popBackStack(commits.get(FIRST_COMMIT),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            // Iniciamos una nueva transacción
            FragmentTransaction transaction = fragManager.beginTransaction();

            // Inicializamos el fragment
            fragCentral1 = CentralFragment1.newInstance();

            // Indicamos dónde queremos mostrar el fragment, y qué fragment es.
            transaction.replace(R.id.flCentral, fragCentral1);

            // Añadimos la operación a la pila de fragments para que mantengan la
            // lógica al pulsar el botón atrás. De no hacer esta llamada no se
            // guardará en la pila, por lo que al pulsar el botón atrás no podremos
            // recuperar este estado.
            transaction.addToBackStack(null);

            // Realizamos el commit de la transacción y lo almacenamos en el mapa.
            commits.put(FIRST_COMMIT, transaction.commit());

            // Ejectuamos las tareas pendientes, para que finalmente se muestren en pantalla.
            fragManager.executePendingTransactions();
        }
    }


    public void showCenterFrag2(View v) {
        if (fragCentral2 == null || !fragCentral2.isVisible()) {
            if (commits.get(FIRST_COMMIT) != null) {
                fragManager.popBackStack(commits.get(FIRST_COMMIT),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);

                fragManager.executePendingTransactions();
            }
            FragmentTransaction transaction = fragManager.beginTransaction();

            fragCentral2 = CentralFragment2.newInstance();

            transaction.replace(R.id.flCentral, fragCentral2);

            transaction.addToBackStack(null);

            commits.put(FIRST_COMMIT, transaction.commit());

            fragManager.executePendingTransactions();
        }
    }

    public void showDetailFrag1(View v) {
        if (fragTop1 == null || !fragTop1.isVisible()) {
            if (commits.get(SECOND_COMMIT) != null) {
                fragManager.popBackStack(commits.get(SECOND_COMMIT),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);

                fragManager.executePendingTransactions();
            }
            FragmentTransaction transaction = fragManager.beginTransaction();

            fragTop1 = TopFragment1.newInstance();
            fragBottom1 = BottomFragment1.newInstance();

            transaction.replace(R.id.flDetailTop, fragTop1);
            transaction.replace(R.id.flDetailBottom, fragBottom1);

            transaction.addToBackStack(null);

            commits.put(SECOND_COMMIT, transaction.commit());

            fragManager.executePendingTransactions();
        }
    }

    public void showDetailFrag2(View v) {
        if (fragTop2 == null || !fragTop2.isVisible()) {
            if (commits.get(SECOND_COMMIT) != null) {
                fragManager.popBackStack(commits.get(SECOND_COMMIT),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE);

                fragManager.executePendingTransactions();
            }
            FragmentTransaction transaction = fragManager.beginTransaction();

            fragTop2 = TopFragment2.newInstance();
            fragBottom2 = BottomFragment2.newInstance();

            transaction.replace(R.id.flDetailTop, fragTop2);
            transaction.replace(R.id.flDetailBottom, fragBottom2);

            transaction.addToBackStack(null);

            commits.put(SECOND_COMMIT, transaction.commit());

            fragManager.executePendingTransactions();
        }
    }
    public void btnFragHardcode_click(View v){
        startActivity(
                new Intent(this, ActividadFragmentoHardcode.class)
        );
    }

}
