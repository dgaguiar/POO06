
package br.com.fatecpg.supermarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DatabaseConnector {
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";//Copiada la de serviços-->drivers-->JavaDB(networking)-->(botão direito -> propriedades --> copia a classe do driver
    private static final String URL = "jdbc:derby://localhost:1527/supermarket";//Copiada la de serviços-->jdbc [...](do banco que quer conectar)-->(botão direito -> propriedades --> copia nome para exibição
    private static final String USER = "supermarket";
    private static final String PASS = "supermarket";
    
 
    //método para receber comando SQL 
    public static ArrayList<Object[]> getQuery(String SQL,Object[] parameters)
    throws Exception{
        
        ArrayList<Object[]> list = new ArrayList<>();
        //fazer conexão com banco 
        //primeiro invocar a classe do drive
        Class.forName(DRIVER);
        //criar uma "connection" com parametro a URL, URES, PASS
        Connection con = DriverManager.getConnection(URL,USER,PASS);
        PreparedStatement stmt = con.prepareStatement(SQL);
        //informar parametros do statement
        //set object , pede indice e o objeto 
        //este laco garante que todos os parametros serão colocados no pepared statement
        for (int i = 0; i < parameters.length; i++){
          stmt.setObject(i+1, parameters[i]);  
        }
        ResultSet rs  = stmt.executeQuery();
        while(rs.next()){
            Object row[] = new Object[rs.getMetaData().getColumnCount()];
            for (int i = 0; i <= rs.getMetaData().getColumnCount(); i++){
                row[i] = rs.getObject(i+1);
            }
            list.add(row);
        }
        rs.close();
        stmt.close();
        con.close();
        return list;
    }
}
