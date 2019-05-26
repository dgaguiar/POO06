
package br.com.fatecpg.supermarket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class User {
    private long id;
    private String name;
    private String login;
    private long passwordhash;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(long passwordhash) {
        this.passwordhash = passwordhash;
    }

    public User(long id, String name, String login, long passwordhash) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.passwordhash = passwordhash;
    }
    
    public static User getUser(String login, String pass) 
            throws Exception{
        String SQL = "SELECT * FROM USERS WHERE login = ? AND passwordhash = ?";
        Object parameters[] = {login, pass.hashCode()};
        ArrayList<Object[]> list = DatabaseConnector.getQuery(SQL, parameters);
        if (list.isEmpty()){
            return null;
        }else{
            Object row[] = list.get(0);
            User u = new User(
                    (long)row[0]
                    ,(String) row[1]
                    ,(String) row[2]
                    ,(long) row[3]);
            return u;
        }
    }   
}
