package capicp.test.drawerlayouttest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by capi on 14/06/13.
 */
public class FragmentPrueba extends Fragment {

   public static final String POS_OPCION = "posicion_opcion";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View vistaRaiz = inflater.inflate(R.layout.fragment_prueba, container,false);
        TextView texto = (TextView) vistaRaiz.findViewById(R.id.texto);

        int i = getArguments().getInt(POS_OPCION);

        texto.setText(getResources().getTextArray(R.array.opciones)[i]);

        return vistaRaiz;

    }
}
