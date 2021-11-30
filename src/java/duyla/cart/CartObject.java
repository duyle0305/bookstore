/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ANH DUY
 */
public class CartObject implements Serializable {
    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }
    
    public void addIteamToCart(String item){
        //1. Check items has existed
        if(item == null){
            return;
        }
        if (item.trim().isEmpty()){
            return;
        }
        if(this.items == null){
            this.items = new HashMap<>();
        }//end id items dose not existed
        //2. check item had existed in items
        int quantity =1;
        if(this.items.containsKey(item)){
            quantity = this.items.get(item) +1;
        }
        //3.drop item to items
        this.items.put(item, quantity); 
    }
    public void removeItemFromCart(String item){
        //1. check items has exited
        if(this.items == null){
            return;
        }
        //2. check item had been existed in items
        if(this.items.containsKey(item)){
            this.items.remove(item);
            if(this.items.isEmpty()){
                this.items = null;
            }
        }
    }
}
