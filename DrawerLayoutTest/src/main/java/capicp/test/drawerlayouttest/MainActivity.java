package capicp.test.drawerlayouttest;

import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity{

    ArrayAdapter mAdapter;
    ActionBarDrawerToggle mDrawerToggle;
    ListView opcionesDrawer;
    String[] opciones;
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        opciones = getResources().getStringArray(R.array.opciones);

        mAdapter = new ArrayAdapter(this, R.layout.opcion_view, opciones );

        opcionesDrawer = (ListView) findViewById(R.id.nav_drawer);
        opcionesDrawer.setAdapter(mAdapter);
        opcionesDrawer.setOnItemClickListener(new DrawerItemSeleccionadoEscucha());

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_abierto,
                R.string.drawer_cerrado){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                getActionBar().setTitle("Opciones");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(opciones[opcionesDrawer.getCheckedItemPosition()]);

            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        if ( savedInstanceState == null )
            cambiarContenido(0);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void cambiarContenido(int seleccionado){

        Fragment fragmento = new FragmentPrueba();
        Bundle argumentos = new Bundle();
        argumentos.putInt(FragmentPrueba.POS_OPCION, seleccionado);
        fragmento.setArguments(argumentos);

        FragmentManager fManager = getSupportFragmentManager();
        fManager.beginTransaction().replace(R.id.contenido, fragmento).commit();

        opcionesDrawer.setItemChecked(seleccionado, true);
        getActionBar().setTitle(opciones[seleccionado]);
        mDrawerLayout.closeDrawer(opcionesDrawer);

    }

    private class DrawerItemSeleccionadoEscucha implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
            cambiarContenido(pos);
        }
    }
}
