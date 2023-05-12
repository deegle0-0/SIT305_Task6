package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.task6.Adapters.TrucksViewModel;
import com.example.task6.R;
import com.example.task6.data.Trucks;

public class UpdateActivity extends AppCompatActivity {

    EditText model, date;

    Button updateButton;

    TrucksViewModel trucksViewModel;
    Trucks trucks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        model = findViewById(R.id.editTextTextPersonName);
        date = findViewById(R.id.editTextTextEmailAddress);
        updateButton = findViewById(R.id.button);

        trucks = (Trucks) getIntent().getSerializableExtra("model");
        trucksViewModel = new ViewModelProvider(this).get(TrucksViewModel.class);

        model.setText(trucks.getModel());
        date.setText((CharSequence) trucks.getDates());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();

                int id = trucks.getId();
                String nameValue = model.getText().toString();
                String descValue = date.getText().toString();
                intent.putExtra("id", String.valueOf(id));
                intent.putExtra("update_value1", nameValue);
                intent.putExtra("update_value2", descValue);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}