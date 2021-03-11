package vihtt.cart;


//import com.sun.xml.internal.ws.developer.Serialization;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanh Vi
 */
public class cartObject implements Serializable{
        Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
        
    public void addItemToCard(String ID){
        if(this.items == null){
            this.items = new HashMap<>();
        }
        int quantity = 1;
                 if (this.items.containsKey(ID)) {
                          quantity = this.items.get(ID) + 1;
                          this.items.replace(ID, quantity);
                 }else{this.items.put(ID, quantity);}   
    }
    
    public void removeItemFormCard(String ID){
        if(this.items==null){
            return;
        }
        if(this.items.containsKey(ID)){
                 this.items.remove(ID);
                 if(this.items.isEmpty()){
                        this.items = null;
                  }
        }
}
}
