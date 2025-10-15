package vn.fpt.coursesupport.prm.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnName;
    private TextView txtUserName;
    private String fileName = "temps";
    private String userKey = "user";
    private String userValue = "Zondat";
    private String passwordKey = "password";
    private String passwordValue = "123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getSharedPreferences(fileName, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(userKey, userValue);
        editor.putString(passwordKey, passwordValue);
        editor.commit();

        txtUserName = findViewById(R.id.txtUserName);
        btnName = findViewById(R.id.btnName);
        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = preferences.getString(userKey, "not found");
                txtUserName.setText(name);
            }
        });
    }
}