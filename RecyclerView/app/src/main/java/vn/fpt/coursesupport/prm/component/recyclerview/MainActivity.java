package vn.fpt.coursesupport.prm.component.recyclerview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// MainActivity.java
// MainActivity.java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the ProductFragment into the activity
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ProductFragment())
                    .commit();
        }
    }

    // Method called from Fragment when product is clicked
    public void showProductDetails(Product product) {
        // You can show a dialog, navigate to another fragment, update UI, etc.
        Toast.makeText(this, "Product Details: " + product.getName(), Toast.LENGTH_LONG).show();

        // Example: Update Activity toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Product: " + product.getName());
        }
    }

    // Method called from Fragment when purchase is made
    public void showPurchaseConfirmation(Product product) {
        // Handle purchase confirmation at Activity level
        new AlertDialog.Builder(this)
                .setTitle("Purchase Successful")
                .setMessage("You bought: " + product.getName() + " for $" + product.getPrice())
                .setPositiveButton("OK", null)
                .show();
    }
}