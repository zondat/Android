package vn.fpt.coursesupport.prm.diallist;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.fpt.coursesupport.prm.diallist.adapter.DialAdapter;
import vn.fpt.coursesupport.prm.diallist.adapter.IDialListener;
import vn.fpt.coursesupport.prm.diallist.model.DialUser;

public class MainActivity extends AppCompatActivity implements IDialListener {

    private RecyclerView viewContact;
    private DialAdapter dialAdapter;
    private List<DialUser> allContacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        allContacts = new ArrayList<>();

        viewContact = findViewById(R.id.viewContact);
        viewContact.setLayoutManager(new LinearLayoutManager(this));

        createSampleData();
        dialAdapter = new DialAdapter(allContacts);
        dialAdapter.addObserver(this);
        viewContact.setAdapter(dialAdapter);
    }

    private void createSampleData() {
        allContacts.add(new DialUser("zondat1", "user"));
        allContacts.add(new DialUser("zondat2", "user"));
        allContacts.add(new DialUser("zondat3", "user"));
        allContacts.add(new DialUser("zondat4", "user"));
        allContacts.add(new DialUser("zondat5", "user"));
        allContacts.add(new DialUser("zondat6", "user"));
        allContacts.add(new DialUser("zondat7", "user"));
        allContacts.add(new DialUser("zondat8", "user"));
        allContacts.add(new DialUser("zondat9", "user"));
        allContacts.add(new DialUser("zondat10", "user"));
        allContacts.add(new DialUser("zondat11", "user"));
        allContacts.add(new DialUser("zondat12", "user"));
        allContacts.add(new DialUser("zondat13", "user"));
        allContacts.add(new DialUser("zondat14", "user"));
        allContacts.add(new DialUser("zondat15", "user"));
        allContacts.add(new DialUser("zondat16", "user"));
        allContacts.add(new DialUser("zondat17", "user"));
        allContacts.add(new DialUser("zondat18", "user"));
        allContacts.add(new DialUser("zondat19", "user"));
    }

    @Override
    public void update(int userIndex) {
        Log.d("Dial", allContacts.get(userIndex).getName());
    }
}