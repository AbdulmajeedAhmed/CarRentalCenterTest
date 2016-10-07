package alyafei.abdulmajid.mystore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // for the error.. come back later.
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext())
                .deleteRealmIfMigrationNeeded()
                .build();
        final EditText username=(EditText) findViewById(R.id.JUsernameEditText);
        final EditText password=(EditText) findViewById(R.id.JPasswordEditText);
        final Button login=(Button) findViewById(R.id.JLoginButton);
        final Button register=(Button) findViewById(R.id.JRegisterationMainButton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Authentication..

                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "لايمكن ترك الخانات فاضية", Toast.LENGTH_SHORT).show();

                } else {
                    Realm realm = Realm.getInstance(getApplicationContext());
                    RealmQuery<User> query = realm.where(User.class); // Search for every object from user.
                    // Conditions
                    query.equalTo("username", username.getText().toString());
                    query.equalTo("password", password.getText().toString());
                    // Need to store the new data after searching and getting results.
                    RealmResults<User> results = query.findAll();//I gave you the conditions so now to search about them and getting them and store them in results.
                    if (results.size() > 0) {
                        Intent intent = new Intent(MainActivity.this, ShowAll.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "إسم المستخدم أو كلمة المرور خاطئة", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }); // End of Login Button.


        // Register Button
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Registeration.class);
                startActivity(intent);

            }
        });
    }
}
