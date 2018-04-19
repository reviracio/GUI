/*import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.reviracio.gui_03.R;

import java.util.List;

public class InteraktywnyAdapterTablicy extends ArrayAdapter<ModelOceny>{
    private List<ModelOceny> listaOcen;
    private Activity kontekst;

    public InteraktywnyAdapterTablicy(Activity kontekst, List<ModelOceny> listaOcen){
        super(kontekst, R.layout.ocena, listaOcen);
    }

    @Override
    public View getView(int numerWiersza, View widokDoRecyklingu, ViewGrup parent){
        View widok = null;
        if(widokDoRecyklingu == null){
            LayoutInflater pompka = kontekst.getLayoutInflater();
            widok = pompka.inflate(R.layout.ocena, null);


            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
            grupaOceny.setOnCheckedChangeListener(
                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                            aktualizuj(group, checkButtonId);
                        }
                    }

            );
            grupaOceny.setTag(listaOcen.get(numerWiersza));
        }else{
            widok = widokDoRecyklingu;
            RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);

        }
        TextView etykieta = (TextView) widok.findViewById(R.id.listaTextEtyketa);

        RadioGroup grupaOceny = (RadioGroup) widok.findViewById(R.id.grupaOceny);
        return widok;
    }



}*/