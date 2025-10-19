package vn.fpt.coursesupport.prm.mvi.grab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import vn.fpt.coursesupport.prm.mvi.grab.statemachine.Idle;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.txtUserName);
        btnLogin = findViewById(R.id.btnLogin);

        Intent login = new Intent(this, Idle.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.putExtra("user", txtUser.getText());
                startActivity(login);
            }
        });

    }
}