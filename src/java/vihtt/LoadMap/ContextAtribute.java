/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vihtt.LoadMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Thanh Vi
 */
public class ContextAtribute implements ServletContextListener{
         String filename = "IndexPage.txt";
         @Override
         public void contextInitialized(ServletContextEvent sce) {
                  try {
                           servletName dto =  new servletName();
                           String path = sce.getServletContext().getRealPath("/");
                           FileReader f = new FileReader(path+"WEB-INF\\"+filename);
                           BufferedReader br = new BufferedReader(f);
                           String line;
                           while((line=br.readLine()) != null){
                                    line = line.trim();
                                    if(line.length() > 0){
                                             StringTokenizer stk = new StringTokenizer(line,"=");
                                             String key= stk.nextToken();
                                             String value = stk.nextToken();
                                             dto.addDTO(key, value);
                                    }
                                    sce.getServletContext().setAttribute("SERVLETNAME", dto);  
                           }
                  } catch (IOException e) {
                           e.printStackTrace();
                  }catch (Exception ex) {
                           ex.printStackTrace();
                  }
         }

         @Override
         public void contextDestroyed(ServletContextEvent sce) {
                  
         }

  
}
