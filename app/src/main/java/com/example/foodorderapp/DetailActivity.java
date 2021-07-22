package com.example.foodorderapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

import java.net.HttpURLConnection;
import java.util.concurrent.atomic.AtomicInteger;

public class DetailActivity extends AppCompatActivity {

    int counter=1;
    int price;
    @NonNull ActivityDetailBinding binding;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final DBHelper helper= new DBHelper(this);
        price =getIntent().getIntExtra("price",5);

        binding.add.setOnClickListener(v -> {
            counter++;
            price = getIntent().getIntExtra("price",5)*counter;
            binding.quantitydetail.setText(counter+"");
            binding.quantitydetail.getText().toString();
            binding.pricelabel.setText(String.format(String.format("%d",price)));
            binding.pricelabel.getText().toString();
        });

        binding.subb.setOnClickListener(v -> {
            if(counter>1){
                counter--;
            }
            else counter=1;
            price = getIntent().getIntExtra("price",5)*counter;
            binding.quantitydetail.setText(counter+"");
            binding.quantitydetail.getText().toString();
            binding.pricelabel.setText(String.format(String.format("%d",price)));
            binding.pricelabel.getText().toString();
        });

        if(getIntent().getIntExtra("type",0)==1){
        final int image =getIntent().getIntExtra("image",0);
        final String name =getIntent().getStringExtra("name");
        final String description =getIntent().getStringExtra("desc");
        SMSHelper helpem= new SMSHelper();
        binding.detailimage.setImageResource(image);
        binding.detailname.setText(name);
        binding.descriptionFood.setText(description);
        binding.pricelabel.setText((String.format("%d",price)));
        binding.quantitydetail.setText(counter+"");
        binding.insertButton.setOnClickListener(v -> {
            boolean isInserted = helper.insertOrder(
                    binding.nameBox.getText().toString(),
                    binding.phoneBox.getText().toString(),
                    description,
                    name,
                    image,
                    price,
                    counter
                    );


                try {
                    // Construct data
                    String apiKey = "apikey=" + "NzI3MjM1NWEzMTRhNjk0NzZiNzAzNjU4Nzk3MTY3Nzk=";
                    String message = "&message=" + "This is your message";
                    String sender = "&sender=" + "TXTLCL";
                    String numbers = "&numbers=" + "917978638416";

                    // Send data
                    HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                    String data = apiKey + numbers + message + sender;
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                    conn.getOutputStream().write(data.getBytes("UTF-8"));
                    final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    final StringBuffer stringBuffer = new StringBuffer();
                    String line;
                    while ((line = rd.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    rd.close();

                } catch (Exception e) {
                    System.out.println("Error in SMS service "+e);
                }


            if(isInserted) {Toast.makeText(DetailActivity.this,"Check your orders on MY ORDERS menu" , Toast.LENGTH_SHORT).show();helpem.sendSms();}
            else Toast.makeText(DetailActivity.this,"Error occurred", Toast.LENGTH_SHORT).show();
            finish();
        });

    }
    else {
            int id =getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            final int image = cursor.getInt(4 );
            binding.detailimage.setImageResource(image);
            binding.detailname.setText(cursor.getString(6));
            binding.descriptionFood.setText(cursor.getString(5));
            binding.pricelabel.setText(String.format(String.valueOf(cursor.getInt(3))));
            binding.quantitydetail.setText(String.format(String.valueOf(cursor.getInt(7))));
            binding.nameBox.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertButton.setText("Update Now");
            counter=Integer.parseInt(binding.quantitydetail.getText().toString());
            binding.add.setOnClickListener(v -> {
                price =Integer.parseInt(binding.pricelabel.getText().toString())/Integer.parseInt(binding.quantitydetail.getText().toString());
                counter++;
                price *= counter;
                binding.quantitydetail.setText(counter+"");
                binding.quantitydetail.getText().toString();
                binding.pricelabel.setText(String.format(String.format("%d",price)));
                binding.pricelabel.getText().toString();
            });

            binding.subb.setOnClickListener(v -> {
                if(counter>1){
                    counter--;
                }
                else counter=1;
                price = Integer.parseInt(binding.pricelabel.getText().toString())/Integer.parseInt(binding.quantitydetail.getText().toString());
                price *= counter;
                binding.quantitydetail.setText(counter+"");
                binding.quantitydetail.getText().toString();
                binding.pricelabel.setText(String.format(String.format("%d",price)));
                binding.pricelabel.getText().toString();
            });

            binding.insertButton.setOnClickListener(v -> {
                boolean isUpdated = helper.updateOrder(
                        binding.nameBox.getText().toString(),
                        binding.phoneBox.getText().toString(),
                        binding.descriptionFood.getText().toString(),
                        binding.detailname.getText().toString(),
                        image,
                        Integer.parseInt(binding.pricelabel.getText().toString()),
                        Integer.parseInt(binding.quantitydetail.getText().toString()),
                        id
                        );
                if(isUpdated)
                    Toast.makeText(DetailActivity.this, "Update Successful.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(DetailActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
                finish();
            });
        }

    }

}