package com.example.hp.epuschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    getHTML();// getting html from dkmh.epu.edu.vn to process
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void getHTML() throws IOException {
        Document doc = Jsoup.connect("http://dkmh.epu.edu.vn/default.aspx?page=thoikhoabieu&sta=1&id=1481310029")
                .maxBodySize(0)
                .timeout(0)
                .get();
//        System.out.print(doc);
        Elements elements = doc.select("table.body-table td");
        //
        Log.d("bug", "initializing loop: ");
        for (Element element:elements) {
            String value=element.select("td:eq(0)").text();
            String value01=element.select("td:eq(1)").text();
//            Log.d("bug",value);
//            Log.d("bug",value01);
            System.out.println(value);
            System.out.println(value01);


        }

    }
}

