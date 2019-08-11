package com.example.news_retofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtData;
    String choose_country_news ;
    EditText country_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtData =  findViewById(R.id.txtData);
        country_name = findViewById(R.id.editText);
        Button search = findViewById(R.id.btn_search);



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(country_name.getText().toString().trim().equals(""))
                {
                    choose_country_news = "eg" ;
                    txtData.setText("");
                    retofit_Start();
                }
                else{
                    choose_country_news = country_name.getText().toString().trim();
                    txtData.setText("");
                    retofit_Start();
                }
            }
        });
    }

    private  void  retofit_Start(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JasonPlaceHolderApi jasonPlaceHolderApi =retrofit.create(JasonPlaceHolderApi.class);
        Call<Root> call = jasonPlaceHolderApi.getNews(choose_country_news,"ccd2b108c53c498385135bad0b83694d");
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if(response.isSuccessful())
                {
                    Root root =response.body();
                    List<Item> items = root.getArticles();
                    if(items.isEmpty()){
                        txtData.setText("No Data Found from Server");
                        return;
                    }
                    for(Item item:items)
                    {
                        String content = "";
                        content += "id: " + item.getSource().getId() + "\n";
                        content += "Name: " + item.getSource().getName() + "\n";
                        content += "author: " + item.getAuthor() + "\n";
                        content += "Title: " + item.getTitle()+ "\n \n";
                        txtData.append(content);
                    }
                }
            }
            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                txtData.append("Failed");
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
