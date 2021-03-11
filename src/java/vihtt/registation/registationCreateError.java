/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.registation;

import java.io.Serializable;

/**
 *
 * @author Thanh Vi
 */
public class registationCreateError implements Serializable{
         private String usernameLenghERROR;
         private String passLenghERROR;
         private String fullnameLenghERROR;
         private String confirmNotMatchERROR;
         private String usernameIsExistERROR;

         public registationCreateError() {
         }

         public registationCreateError(String usernameLenghERROR, String passLenghERROR, String fullnameLenghERROR, String confirmNotMatchERROR, String usernameIsExistERROR) {
                  this.usernameLenghERROR = usernameLenghERROR;
                  this.passLenghERROR = passLenghERROR;
                  this.fullnameLenghERROR = fullnameLenghERROR;
                  this.confirmNotMatchERROR = confirmNotMatchERROR;
                  this.usernameIsExistERROR = usernameIsExistERROR;
         }

         public String getUsernameLenghERROR() {
                  return usernameLenghERROR;
         }

         public void setUsernameLenghERROR(String usernameLenghERROR) {
                  this.usernameLenghERROR = usernameLenghERROR;
         }

         public String getPassLenghERROR() {
                  return passLenghERROR;
         }

         public void setPassLenghERROR(String passLenghERROR) {
                  this.passLenghERROR = passLenghERROR;
         }

         public String getFullnameLenghERROR() {
                  return fullnameLenghERROR;
         }

         public void setFullnameLenghERROR(String fullnameLenghERROR) {
                  this.fullnameLenghERROR = fullnameLenghERROR;
         }

         public String getConfirmNotMatchERROR() {
                  return confirmNotMatchERROR;
         }

         public void setConfirmNotMatchERROR(String confirmNotMatchERROR) {
                  this.confirmNotMatchERROR = confirmNotMatchERROR;
         }

         public String getUsernameIsExistERROR() {
                  return usernameIsExistERROR;
         }

         public void setUsernameIsExistERROR(String usernameIsExistERROR) {
                  this.usernameIsExistERROR = usernameIsExistERROR;
         }

         
              
              
}
