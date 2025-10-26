package vn.fpt.coursesupport.prm.contentprovider.libraryservice;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private UserContentProvider contentResolver;
    private Button btnGet;
    private TextView txtUser;
    private EditText txtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        contentResolver = new UserContentProvider();
        txtId = findViewById(R.id.txtId);
        txtUser = findViewById(R.id.txtUser);
        btnGet = findViewById(R.id.btnAction);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(String.valueOf(txtId.getText()));
                Cursor result = contentResolver.query(
                                Uri.parse("content://" + "vn.fpt.library.services/users/" + id),
                                new String[]{"name"}, null, null, null
                            );
                if (result.moveToFirst())
                    txtUser.setText(result.getString(1));
                else
                    txtUser.setText("Not found");
            }
        });
    }
}