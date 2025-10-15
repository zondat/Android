package vn.fpt.coursesupport.prm.filestorage;

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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private EditText txtFileName;
    private EditText txtContent;
    private TextView txtLoadedContent;
    private Button btnSave;

    private void saveFile(String fileName, String content) {
        File file = new File(this.getFilesDir(), fileName);
        FileOutputStream fos = null;
        try {
            fos = this.openFileOutput(fileName, MODE_PRIVATE);
            fos.write(content.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readFile(String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        FileInputStream fis = null;
        InputStreamReader inputStreamReader = null;
        try {
            fis = openFileInput(fileName);
            inputStreamReader = new InputStreamReader(fis, StandardCharsets.UTF_8);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append("\n");
                line = reader.readLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         finally {
            return stringBuilder.toString();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtFileName = findViewById(R.id.txtFileName);
        txtContent = findViewById(R.id.txtContent);
        txtLoadedContent = findViewById(R.id.txtLoadedContent);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = String.valueOf(txtFileName.getText());
                String content = String.valueOf(txtContent.getText());
                saveFile(fileName, content);

                String loadedContent = readFile(fileName);
                txtLoadedContent.setText(loadedContent);
            }
        });

    }
}