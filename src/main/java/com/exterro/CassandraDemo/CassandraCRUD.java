package com.exterro.CassandraDemo;

import java.nio.file.Paths;
import java.util.List;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;

public class CassandraCRUD {

    public static void main(String[] args) {

        try (CqlSession session = CqlSession.builder()
                // make sure you change the path to the secure connect bundle below
                .withCloudSecureConnectBundle(Paths.get("C:\\Users\\mrangasamy\\Downloads\\secure-connect-mirdu.zip"))
                .withAuthCredentials("jTkXcUKZUoSCTXnnemtslZEB","P+,5c4uxkoFaRH1u5w.ILST.IPxG6_iKEzOO6ZfND8rdSRnYj96Ra3u9Zxem7nxft2dCRMXyWQyG9+iIKW-OFrr-UYmaWZPw2Gu+SvJfmOQP8bomFdzdiP1q_y2wlD.M")
                .withKeyspace("demo")
                .build()) {

            /*session.execute("CREATE TABLE IF NOT EXISTS demo.person ("
                    + " lastname text PRIMARY KEY,"
                    + " age int,"
                    + " city text,"
                    + " email text,"
                    + " firstname text)");
            System.out.println("Table created Successfully");*/

           //setUser(session, "Cruise", 36, "US", "tom@example.com", "Tom");
            
          //System.out.println("Inserted Row Successfully");
            
            //getUser(session, "Parker");

           // System.out.println("Fetched Row Successfully");
            
        // updateUser(session, 55, "Parker");

          //System.out.println("Updated Row Successfully");
            
            //getUser(session, "Jones");

            //System.out.println("Fetched Row Successfully");
            
            deleteUser(session, "Parker");
            
           System.out.println("Deleted Row Successfully");
            
			
			
			  ResultSet rs = session.execute("select * from person"); 
			  List<Row> rows = rs.all(); 
			  for(Row row:rows) {
				  System.out.println(row.getString("firstname")+" "+row.getString("lastname"));
			  }
			 
            
           /*ResultSet rs  = session.execute("select * from users");
            for(Row row:rs.all()) {
            	System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
           }*/
        }
    }

    private static void setUser(CqlSession cqlSession, String lastname, int age, String city, String email, String firstname) {

        //TO DO: execute SimpleStatement that inserts one user into the table
        cqlSession.execute(
                SimpleStatement.builder( "INSERT INTO person (lastname, age, city, email, firstname) "
                        +  "VALUES (?,?,?,?,?)")
                        .addPositionalValues(lastname, age, city, email, firstname)
                        .build());
    }

    private static void getUser(CqlSession session, String lastname) {

        //TO DO: execute SimpleStatement that retrieves one user from the table
        //TO DO: print firstname and age of user
        ResultSet rs = session.execute(
                SimpleStatement.builder("SELECT * FROM person WHERE lastname=:n")
                        .addPositionalValue(lastname)
                        .build());

        Row row = rs.one();
        System.out.format("%s %d\n", row.getString("firstname"), row.getInt("age"));
    }


    private static void updateUser(CqlSession session, int age, String lastname) {

        //TO DO: execute a BoundStatement that updates the age of one user
        PreparedStatement prepared = session.prepare(
                "UPDATE person SET age =?  WHERE lastname =?");

        BoundStatement bound = prepared.bind(age, lastname);

        session.execute(bound);

    }

    private static void deleteUser(CqlSession session, String lastname) {

        //TO DO: execute BoundStatement that deletes one user from the table
        PreparedStatement prepared = session.prepare(
                "DELETE FROM person WHERE lastname=?");
        BoundStatement bound = prepared.bind(lastname);
        session.execute(bound);

    }
    
    

}

