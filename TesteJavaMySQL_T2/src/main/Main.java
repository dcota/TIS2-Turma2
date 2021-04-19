package main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        MYSQLConnection connection = new MYSQLConnection();

        ResultSet result = connection.querySELECT("SELECT * FROM city");
        //mostrar os nomes de todas as cidades
        while(result.next()) {
            String countryCode = result.getString(2);
            System.out.println(countryCode);
        }

    }

}
