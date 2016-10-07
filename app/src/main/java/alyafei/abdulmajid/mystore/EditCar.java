package alyafei.abdulmajid.mystore;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;

public class EditCar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_car);

        // Note: the position of the item in list view is the same position in the database.
        int positionOfItem=getIntent().getIntExtra("id",0);// Get the intent of the name or indentification 'id' .

        final EditText name =(EditText) findViewById(R.id.JNameEditText);
        final EditText model =(EditText) findViewById(R.id.JModelEditText);
        final EditText id =(EditText) findViewById(R.id.JCarIDEditText);
        final CheckBox status = (CheckBox) findViewById(R.id.JStatisCheckbox);
        final Button delete =(Button) findViewById(R.id.JDeleteButton);
        final Button update =(Button) findViewById(R.id.JUpdateButton);
        final Realm realm= Realm.getInstance(getApplicationContext());

       // realm.beginTransaction();  No need for this line because there is no writing or updating the db.
        List<Car> cars= realm.allObjects(Car.class);
        final Car car = cars.get(positionOfItem);
        if (cars.get(positionOfItem).isValid()) {
            // Filling the info of the object in the widgets.
            name.setText(car.getName());
            model.setText(car.getModel());
            id.setText(car.getId());
            status.setChecked(car.isStatus()); // setChecked us different.
        }
       // realm.commitTransaction();// Necessary to commit.

        // Delete button:
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating the confirming message.
                new AlertDialog.Builder(EditCar.this)
                        .setTitle("تنبيه")
                        .setMessage("هل انت متاكد من حذف السيارة؟")
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                realm.beginTransaction();
                                car.removeFromRealm();
                                realm.commitTransaction();
                                Toast.makeText(getApplicationContext(),"تم حذف السيارة",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }});

        // Update Button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(EditCar.this)
                        .setTitle("تنبيه")
                        .setMessage("هل انت متاكد من تعديل البيانات؟")
                        .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // My code..
                                realm.beginTransaction();
                                car.setName(name.getText().toString());
                                car.setModel(model.getText().toString());
                                car.setId(id.getText().toString());
                                car.setStatus(status.isChecked());
                                realm.copyToRealmOrUpdate(car);
                                realm.commitTransaction();
                                Toast.makeText(getApplicationContext(),"تم تعديل البيانات",Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        })
                        .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();
            }
        });
    }
}
