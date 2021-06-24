package com.example.foodorderapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

        ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list =new ArrayList<>();
        list.add(new MainModel(R.drawable.bara, "Medu Vada", 1, "Delicious doughnut shaped Medu vada with a crispy exterior and soft interior with  Black gram, dried yellow peas, or dried white peas are cooked with gravy in the traditional style"));
        list.add(new MainModel(R.drawable.aaloo_bara, "Aaloo Vada", 2, "Mouthwatering Medu vada stuffed with mashed potato and parmesan cheese, served hot with cheese mayonnaise sauce"));
        list.add(new MainModel(R.drawable.masala_bara, "Masala Vada", 2, "Appetizing Vada with enrichment of Indian traditional spices along with nutrients of "));
        list.add(new MainModel(R.drawable.bara_chat, "Vada Chaat", 3, "Chaat is an umbrella term for ingredients like chickpeas, boiled potatoes, yogurt sauce, and tamarind and coriander \n" +
                "chutneys with toppings of pomegranate seeds and sev  that usually feature some fried dough with various ingredients that typically create a spicy, tangy and sweet-salty flavour"));
        list.add(new MainModel(R.drawable.bara_cholial, "Vada Masalaam", 4, "Flavoursome Vada is cooked with a spicy thick gravy. The specialty of this recipe is that the Vada is cooked\n" +
                "with it's own juice. With the addition of a bare minimum amount of water"));
        list.add(new MainModel(R.drawable.bada_wtih_shezwan, "Schezwan Vada", 2, "Delicious doughnut shaped Medu vada with a crispy exterior and soft interior with spicy and pungent sauce made with dry red chillies, garlic, shallots and spices- a fusion Indo Chinese recipe of a hot chili sauce."));
        list.add(new MainModel(R.drawable.rava_bada, "Rava Vada", 1, "Luscious deep fried snack made of Semolina, yogurt, onions, grated ginger, chopped curry leaves and roughly torn coriander leaves, served with pulpy tomato and peanut savory"));
        list.add(new MainModel(R.drawable.dahi_vadas, "Dahi Vada", 2, "Fluffy, tender, tangy and sweet Dahi Vada are a combination of all your favorite flavors and textures in one tasty snack. They consist of homemade fried lentil dumpling fritters, dunked in creamy whipped yogurt and topped with both spicy and sweet savories."));
        list.add(new MainModel(R.drawable.peps, "Pepsi", 1, "Refreshing Pepsi"));
        list.add(new MainModel(R.drawable.cocacola, "Coca Cola", 1, "Refreshing Coke"));

        MainAdapter adapter = new MainAdapter(list,this);
        binding.recylerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recylerview.setLayoutManager(layoutManager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){

    }
}