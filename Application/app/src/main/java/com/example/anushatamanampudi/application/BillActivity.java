package com.example.anushatamanampudi.application;

/**
 * Created by anushatamanampudi on 12/7/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class BillActivity extends Activity{
  // Context context = getApplicationContext();
  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();


    String endPoint = "https://api.mastercard.com/payments/v1/purchase?Format=XML";

    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);
        EditText cardNumberInput = (EditText) findViewById(R.id.cardNumberInput);
        EditText expirationMonthInput = (EditText) findViewById(R.id.expirationMonthInput);
        EditText expirationYearInput = (EditText) findViewById(R.id.expirationYearInput);
        EditText cvvInput = (EditText) findViewById(R.id.cvvInput);
        EditText cardHolderNameInput = (EditText) findViewById(R.id.cardHolderNameInput);
        EditText amountInput = (EditText) findViewById(R.id.amountInput);

        final String amount = amountInput.getText().toString();
        final String currency = "USD";
        final String companyId = "your-company-id-here";
        final String companyPassword = "your-company-password-here";
        final String messageId = "your-message-id-here";
        final String settlementId = "your-settlement-id-here";
        final String cardHolderName = cardHolderNameInput.getText().toString();
        final String accountNumber = cardNumberInput.getText().toString();
        final String expiryMonth = expirationMonthInput.getText().toString();
        final String expiryYear = expirationYearInput.getText().toString();
        final String securityCode = cvvInput.getText().toString();

        try {
            Log.v("hello","hello");
            // Send data
            URL url = new URL(endPoint);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            OutputStreamWriter request = new OutputStreamWriter(conn.getOutputStream());
            // Create the XML to post
            request.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            request.append("<PurchaseRequest>");
            request.append("<MerchantIdentity>");
            request.append("<CompanyId>");
            request.append(companyId);
            request.append("</CompanyId>");
            request.append("<CompanyPassword>");
            request.append(companyPassword);
            request.append("</CompanyPassword>");
            request.append("</MerchantIdentity>");
            request.append("<Reference>");
            request.append("<MessageId>");
            request.append(messageId);
            request.append("</MessageId>");
            request.append("<SettlementId>");
            request.append(settlementId);
            request.append("</SettlementId>");
            request.append("</Reference>");
            request.append("<Amount>");
            request.append("<Currency>");
            request.append(currency);
            request.append("</Currency>");
            request.append("<Value>");
            request.append(amount);
            request.append("</Value>");
            request.append("</Amount>");
            request.append("<FundingCard>");
            request.append("<CardholderName>");
            request.append(cardHolderName);
            request.append("</CardholderName>");
            request.append("<AccountNumber>");
            request.append(accountNumber);
            request.append("</AccountNumber>");
            request.append("<ExpiryMonth>");
            request.append(expiryMonth);
            request.append("</ExpiryMonth>");
            request.append("<ExpiryYear>");
            request.append(expiryYear);
            request.append("</ExpiryYear>");
            request.append("<SecurityCode>");
            request.append(securityCode);
            request.append("</SecurityCode>");
            request.append("</FundingCard>");
            request.append("</PurchaseRequest>");

            request.flush();

            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(conn.getInputStream());

           // Node transactionResponseNode = doc.getElementsByTagName("TransactionResponse").item(0);
           // Log.v("response", String.valueOf(doc.getNodeValue()));
           // Log.v("response1", String.valueOf(conn.getInputStream()));
            // Test for approval.
            //String response = transactionResponseNode.getNodeValue().trim().toUpperCase();

           // if (response.equals("APPROVED") == true) {
               // request.close();
                String text = "The payment processed successfully.  $" + amountInput.getText().toString()
                        + " was charged to the account.";
                Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                toast.show();

                Intent inte=new Intent(BillActivity.this,MainActivity.class);
                startActivity(inte);

            //} else if (response.equals("DECLINED")) {
              //  String text = "The payment was declined.";
               // Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                //toast.show();
           // } else if (response.equals("ERROR")) {
             //   String text = "Error Processing Payment.";
               // Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
                //toast.show();
            //}
        } catch (MalformedURLException e) {
            String text = "Error Processing Payment.";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        } catch (IOException e) {
            String text = "Error Processing Payment.";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        } catch (ParserConfigurationException e) {
            String text = "Error Processing Payment.";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        } catch (SAXException e) {
            String text = "Error Processing Payment.";
            Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG);
            toast.show();
        }
    };


}


