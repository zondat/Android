package vn.fpt.coursesupport.prm.mvvm.gamedama;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import vn.fpt.coursesupport.prm.mvvm.gamedama.databinding.ActivityMainBinding;
import vn.fpt.coursesupport.prm.mvvm.gamedama.viewmodel.BoardViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BoardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        viewModel = new BoardViewModel(
                R.drawable.damablack_48x48,
                R.drawable.damawhite_48x48,
                R.drawable.damablack_dame_48x48,
                R.drawable.damawhite_dame_48x48,
                android.R.drawable.screen_background_dark_transparent,
                android.R.drawable.screen_background_light_transparent
        );
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

}