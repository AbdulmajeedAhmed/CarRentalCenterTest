package alyafei.abdulmajid.mystore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmObject;

public class AddCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        // To print ....  //  Log.d("Name","Test");

       final EditText carID=(EditText) findViewById(R.id.JCarIDEditText);
       final EditText carName=(EditText) findViewById(R.id.JNameEditText);
       final EditText model=(EditText) findViewById(R.id.JModelEditText);
       final CheckBox isNew=(CheckBox) findViewById(R.id.JisCarNewCheckBox);

        Button addCar=(Button) findViewById(R.id.JAddCarButton);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Car car = new Car();
                car.setId(carID.getText().toString());
                car.setName(carName.getText().toString());
                car.setModel(model.getText().toString());
                car.setStatus(isNew.isChecked());

                Realm realm=Realm.getInstance(getApplicationContext());
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(car);
                realm.commitTransaction();
                Toast.makeText(AddCar.this,"تم إضافة السيارة",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }
}
