/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duyla.listener;

import duyla.util.PropertiesFileHelper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author ANH DUY
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying....");
        ServletContext context = sce.getServletContext();
        String siteMapLocation = context.getInitParameter("SITEMAP_PROPERTIES_FILE_LOCATION");
        Properties siteMapProperty = PropertiesFileHelper.getProperties(context, siteMapLocation);
        context.setAttribute("SITE_MAP",siteMapLocation);
        InputStream is = null;
        Properties properties = null;
        try {
            is = context.getResourceAsStream(siteMapLocation);
            properties = new Properties();
            properties.load(is);

            context.setAttribute("SITEMAP", properties);
        } catch (IOException ex) {
            context.log("MyContextServletListener IO" + ex.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    context.log("MyContextServletListener IO" + ex.getMessage());
                }
            }
        }
    }
    

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroying...");
    }
}
