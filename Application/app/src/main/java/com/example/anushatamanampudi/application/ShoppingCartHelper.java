package com.example.anushatamanampudi.application;

import android.content.res.Resources;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class ShoppingCartHelper {

    public static final String PRODUCT_INDEX = "PRODUCT_INDEX";

    private static List<Product> catalog= new Vector<Product>();;
    private static Map<Product, ShoppingCartEntry> cartMap = new HashMap<Product, ShoppingCartEntry>();
    Resources res;

    private static String description;
    private static double price;




    public static void addcatalog(String productname,String description,double price){



        catalog.add(new Product(productname, description, price));

        //getCatalog(productname,description,price,res);
    }

    public static List<Product> getCatalog(Resources res) {




        return catalog;
    }

    public static double getTotalprice(){
        double totalprice=0;
        for(int i=0;i<catalog.size();i++){
            Log.v("products", String.valueOf(catalog.get(0).price));
            int quantity=getProductQuantity(catalog.get(i));
            Log.v("quantity",String.valueOf(quantity));
            totalprice=totalprice+((catalog.get(i).price)*quantity);
        }
        return totalprice;
    }
    public static String getProductname(){
        List<String> productname = new ArrayList<String>();
        String product="";
        for(int i=0;i<catalog.size();i++){

            Log.v("products", String.valueOf(catalog.get(0).title));
            productname.add(String.valueOf(catalog.get(0).title));
            product=product+","+String.valueOf(catalog.get(0).title);


        }
        return product;
    }


    public static void setQuantity(Product product, int quantity) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        // If the quantity is zero or less, remove the products
        if(quantity <= 0) {
            if(curEntry != null)
                removeProduct(product);
            return;
        }

        // If a current cart entry doesn't exist, create one
        if(curEntry == null) {
            curEntry = new ShoppingCartEntry(product, quantity);
            cartMap.put(product, curEntry);
            return;
        }

        // Update the quantity
        curEntry.setQuantity(quantity);
    }

    public static int getProductQuantity(Product product) {
        // Get the current cart entry
        ShoppingCartEntry curEntry = cartMap.get(product);

        if(curEntry != null)
            return curEntry.getQuantity();

        return 0;
    }

    public static void removeProduct(Product product) {
        cartMap.remove(product);
    }

    public static List<Product> getCartList() {
        List<Product> cartList = new Vector<Product>(cartMap.keySet().size());
        for(Product p : cartMap.keySet()) {
            cartList.add(p);
        }

        return cartList;
    }


}
