package etu1776.framework.servlet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import etu1776.framework.*;
import etu1776.framework.Utile;
import etu1776.framework.Mapping;
public class FrontServlet extends HttpServlet 
{
    HashMap<String,Mapping> mappingUrls = new HashMap<>();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                try {
                    response.setContentType("text/plain");
                    PrintWriter out = response.getWriter();
                    Utile u = new Utile();
                    String path= u.getPath(request); //Premiere methode
                    System.out.println(path);
                    String meth = u.getMethode(request); // Deuxieme methode  
                    System.out.println(meth);
                    out.println(path);
                    out.println(meth);
                    String paths = "F:/L2/Reseaux/apache-tomcat-8/webapps/Sprint3/WEB-INF/classes/etu1776/framework/modele";
                    
                    mappingUrls= u.getHashmap( mappingUrls, paths);
                 //   u.printHM(mappingUrls);
                    for( String key : mappingUrls.keySet()){
                       
                            Mapping map = mappingUrls.get(key);
                            out.println( "Key : "+key );
                            out.println( map.getClassName() + " | " + map.getMethod());
                       
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    // TODO: handle exception
                }
               

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        processRequest(request,response);
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

    }
}
