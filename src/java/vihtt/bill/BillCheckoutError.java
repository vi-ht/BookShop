package vihtt.bill;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thanh Vi
 */
public class BillCheckoutError {
         private String nameLengthERROR;
         private String adressLengthERROR;
         private String phoneLengthERROR;
         private String phoneIsNotNumberEROR;

         public BillCheckoutError() {
         }

         public BillCheckoutError(String nameLengthERROR, String adressLengthERROR, String phoneLengthERROR, String phoneIsNotNumberEROR) {
                  this.nameLengthERROR = nameLengthERROR;
                  this.adressLengthERROR = adressLengthERROR;
                  this.phoneLengthERROR = phoneLengthERROR;
                  this.phoneIsNotNumberEROR = phoneIsNotNumberEROR;
         }

         public String getNameLengthERROR() {
                  return nameLengthERROR;
         }

         public void setNameLengthERROR(String nameLengthERROR) {
                  this.nameLengthERROR = nameLengthERROR;
         }

         public String getAdressLengthERROR() {
                  return adressLengthERROR;
         }

         public void setAdressLengthERROR(String adressLengthERROR) {
                  this.adressLengthERROR = adressLengthERROR;
         }

         public String getPhoneLengthERROR() {
                  return phoneLengthERROR;
         }

         public void setPhoneLengthERROR(String phoneLengthERROR) {
                  this.phoneLengthERROR = phoneLengthERROR;
         }

         public String getPhoneIsNotNumberEROR() {
                  return phoneIsNotNumberEROR;
         }

         public void setPhoneIsNotNumberEROR(String phoneIsNotNumberEROR) {
                  this.phoneIsNotNumberEROR = phoneIsNotNumberEROR;
         }
         
                 
                 
}
