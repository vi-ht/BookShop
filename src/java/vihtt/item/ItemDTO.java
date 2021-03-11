/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.item;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class ItemDTO implements Serializable{
        private String ID;
        private String title;
        private int price;
        private int num;

         public ItemDTO(String ID, String title, int price, int num) {
                  this.ID = ID;
                  this.title = title;
                  this.price = price;
                  this.num = num;
         }

         public int getNum() {
                  return num;
         }

         public void setNum(int num) {
                  this.num = num;
         }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
        
}
