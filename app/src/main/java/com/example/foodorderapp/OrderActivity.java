package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorderapp.Adapters.OrderAdapter;
import com.example.foodorderapp.Models.OrderModel;
import com.example.foodorderapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //ArrayList<OrderModel> list = new ArrayList<>();
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.burger,"Aina nu burger khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pepsii,"Aina nu pepsi pilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.cocacola,"Aina nu cola pilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
//        list.add(new OrderModel(R.drawable.pizza,"Aina nu pizza khilao", "12","256984786"));
        DBHelper helper =new DBHelper(this);
        ArrayList<OrderModel> list = helper.getOrders();

        OrderAdapter adapter = new OrderAdapter(list,this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);

    }
}