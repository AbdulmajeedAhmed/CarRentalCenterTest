package alyafei.abdulmajid.mystore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ShowAll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        final Button addCar = (Button) findViewById(R.id.JAddCarButton);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowAll.this, AddCar.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        /*
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .deleteRealmIfMigrationNeeded()
                .build();
                */
        Realm realm=Realm.getInstance(getApplicationContext());
        //  realm.beginTransaction(); // No need for this line because there is no writing or updating to the db.
        List<Car> cars=realm.allObjects(Car.class);
        String[] names=new String[cars.size()];
        for(int i=0; i<names.length; i++){
            names[i]=cars.get(i).getName();
        }
        ListView listCars= (ListView)findViewById(R.id.ListView);
        //For the list view to be display the names.
       // "this" means where ami?
       // convert from string to thing that the list view understands.
       // List View needs adapter if there is a list of strings to display.
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,names);
        listCars.setAdapter(adapter);

        // Now the code for dealing with any selected item from the list view.
        listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShowAll.this,EditCar.class);
                intent.putExtra("id",position); // id is an identification of the intent. A name for the intent if we can say that.
                startActivity(intent);
            }
        });
    }
}
