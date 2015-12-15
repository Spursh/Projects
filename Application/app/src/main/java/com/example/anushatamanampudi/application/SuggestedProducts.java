
package com.example.anushatamanampudi.application;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class SuggestedProducts extends Activity {


    private TextView suggestprod;
    private WebView web;

    //String value = getIntent().getExtras().get("setdata").toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestedproducts);
        //suggestprod = (TextView)findViewById(R.id.suggprod);
        web=(WebView)findViewById(R.id.webView);
        Log.v("in suggested producst",ShoppingCartActivity.getsuggestedproducts());
        //Document doc = Jsoup.parse("your html");
        //Elements elements = doc.select("strong");
       String htmlTextStr = Html.fromHtml(ShoppingCartActivity.getsuggestedproducts()).toString();
        (web).loadData(ShoppingCartActivity.getsuggestedproducts(), "text/html", "utf-8");
      //  suggestprod.setText(htmlTextStr);
       // suggestprod.setText(value);


    }
    public void home(View v){
        Intent inte=new Intent(SuggestedProducts.this,MainActivity.class);
        startActivity(inte);
    }
}