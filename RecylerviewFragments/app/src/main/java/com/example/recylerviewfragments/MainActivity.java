package com.example.recylerviewfragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {
    TextView tvName, tvphone;
    Button Btnadd;
    EditText Etname,Etphone;
//    ListFragment listFragment;
    List listfrag;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName = findViewById(R.id.tvName);
        tvphone = findViewById(R.id.tvPhone);
        Btnadd = findViewById(R.id.Btadd);
        Etname = findViewById(R.id.Etname);
        Etphone = findViewById(R.id.Etphone);
        fragmentManager = this.getSupportFragmentManager();
        listfrag = (List) fragmentManager.findFragmentById(R.id.listfrag);


        Btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Etname.getText().toString().isEmpty()|| Etphone.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter All Fileds", Toast.LENGTH_SHORT).show();
                }
                else {
                    Applicationclass.people.add(new Person(Etname.getText().toString().trim(),Etphone.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "Person Added Successfully", Toast.LENGTH_SHORT).show();
                    Etname.setText(null);
                    Etphone.setText(null);
                    listfrag.notifyDataSetChanged();
                }

            }
        });

    }

    @Override
    public void ItemClicked(int index) {
        tvName.setText(Applicationclass.people.get(index).getName());
        tvphone.setText(Applicationclass.people.get(index).getPhone());

    }
}