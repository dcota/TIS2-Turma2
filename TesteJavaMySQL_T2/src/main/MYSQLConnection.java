package main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MYSQLConnection {
    //atributos
    private Properties p;
    private Connection connection;
    //construtor
    public MYSQLConnection() throws IOException {
        p = new Properties();
        try {
            InputStream inputStream = new FileInputStream("dadosBD.properties");
            p.load(inputStream);
            connection = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
            System.out.println("Ligação feita com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException | SQLException e) {
            System.out.println("Ocorreu um erro...");
        }
    }

    public ResultSet querySELECT (String sql) {
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
