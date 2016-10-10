/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Abdelrhman Ahmed
 */
@Named(value = "bean")
@RequestScoped
public class JSFManagedBean {
     
    private String str1="",str2="",txt="",rep="";
    int i=0;
    PreparedStatement ps = null;
    Connection con = null;
    /**
     * Creates a new instance of NewJSFManagedBean
     */
    public JSFManagedBean() {
        System.err.println("kdk");
    }
    public void add(){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aaa","root","");
            String sql = "INSERT INTO user(name) VALUES(?)";
            ps= con.prepareStatement(sql); 
            ps.setString(1, str1);
            i = ps.executeUpdate();
            rep=("Data Added Successfully");
        }
        catch(Exception e)
        {
            System.out.println(e); 
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
   }
    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getStr1() {
        return str1;
    }
    
    public void reply(){
        if(str2.equals(""))
        {
            txt="Hello Stranger";
        }
        else
        {
            txt = "Hello "+str2;
        }
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, txt, null );
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public String getStr2() {
        return str2;
    }

    public void setStr2(String str2) {
        this.str2 = str2;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public String getRep() {
        return rep;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    
}
