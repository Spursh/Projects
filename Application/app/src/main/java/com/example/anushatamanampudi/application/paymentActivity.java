package com.example.anushatamanampudi.application;

/**
 * Created by anushatamanampudi on 12/7/15.
 */


        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.Menu;
        import android.app.Activity;
        import android.view.View;

public class paymentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public void showbill(View v1) {
        Log.v("inbill", "ibill");
        Intent intent = new Intent(this, BillActivity.class);
        startActivity(intent);
    }



}

