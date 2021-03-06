package org.oranki.perjantaipullo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Collections;

public class SelectActivity extends AppCompatActivity {

    GridView gridView_all;

    // String-array, jonka perusteella täytetään gridView_all adapterilla
    static final String[] all_numbers = new String[]{
            "1","2","3","4","5","6","7","8","9","10",
            "11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30",
            "31","32","33","34","35","36","37","38","39","40",
            "41","42","43","44","45","46","47","48","49","50",
            "51","52","53","54","55","56","57","58","59","60",
            "61","62","63","64","65","66","67","68","69","70",
            "71","72","73","74","75","76","77","78","79","80",
            "81","82","83","84","85","86","87","88","89","90",
            "91","92","93","94","95","96","97","98","99"
    };

    // Tähän ArrayListiin listataan valitut numerot ja viedään seuraavaan activityyn
    public ArrayList<Integer> selected_numbers = new ArrayList();

    // Apumetodi, jolla tarkistetaan onko parametrina annettu numero selected_numbersissa
    public int isSelected(int number) {
        for (int i = 0; i < selected_numbers.size(); i++) {
            if (number == selected_numbers.get(i)) return i;
        }
        return 0xFF;
        // palauttaa indeksin, jos numero on valittu, ja 0xFF jos ei valittu
    }

    // Luodaan ylätoimintopalkkiin "valikko", jossa on vain "VALMIS"-vaihtoehto
    // liittyy app/res/values/menu/activity_select_menu.xml:ään
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_menu, menu);
        return true;
    }

    // Määritetään, mitä tapahtuu, kun valikon itemiä painetaan
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.selection_done) {
            Intent intent = new Intent(this, LotteryActivity.class);
            intent.putExtra("numbers", selected_numbers);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        gridView_all = findViewById(R.id.gridView_numbers);

        // Täytetään Gridview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, all_numbers);
        gridView_all.setAdapter(adapter);
        gridView_all.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE); // Monivalinta-tila GridView:lle

        // onClick gridView:n itemille
        gridView_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Tarkistetaan, onko numero jo valittuna selected_numbersissa
                // helper = indeksi, jos löytyy
                int helper = isSelected(position + 1);

                if (helper == 0xFF) {   // Jos ei valittuna
                    selected_numbers.add(position + 1); // Lisätään numero selected_numbersiin
                    Collections.sort(selected_numbers); // Järjestetään selected_numbers nousevaan järjestykseen
                    // Debuggausta
                    Log.d("jarno", "added " + (position + 1) + ", selected_numbers: " + selected_numbers.toString()) ;
                }
                else {                  // Jos valittuna
                    selected_numbers.remove(helper);    // Poistetaan numero selected_numbersista
                    // Debuggausta
                    Log.d("jarno", "removed " + (position + 1) + ", selected_numbers: " + selected_numbers.toString()) ;
                }

            }
        });

    }
}