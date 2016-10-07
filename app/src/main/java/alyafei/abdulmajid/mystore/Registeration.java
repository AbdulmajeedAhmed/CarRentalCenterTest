package alyafei.abdulmajid.mystore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmObject;

public class Registeration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        final EditText username=(EditText) findViewById(R.id.JUsernameRegEditText);
        final EditText password=(EditText) findViewById(R.id.JPasswordRegEditText);
        final Button register=(Button) findViewById(R.id.JRegisterButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "لايمكن ترك الخانات فاضية", Toast.LENGTH_SHORT).show();

                } else {
                    user.setUsername(username.getText().toString());
                    user.setPassword(password.getText().toString());
                    Realm realm = Realm.getInstance(getApplicationContext());
                    realm.beginTransaction();
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    finish();
                    Toast.makeText(getApplicationContext(), "تم التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
