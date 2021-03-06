package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class MySQLConnection {
    //atributos
    private Properties p;
    private Connection connection;
    //construtor
    public MySQLConnection(){
        setConnection();
    }
    //método para fazer a ligação
    public void setConnection(){
        p = new Properties();
        try{
            InputStream input = new FileInputStream("dbconnect.properties");
            p.load(input);
            connection = DriverManager.getConnection
                    (p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
            System.out.println("Ligado à BD.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("ocorreu um problema...");
        }
    }

    public ResultSet getCidades(){
        ResultSet result = null;
        String sql = "SELECT city.ID, city.Name, country.Name\n" +
                "FROM city, country\n" +
                "WHERE city.CountryCode = country.Code\n" +
                "ORDER BY country.name ASC";
        try{
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getDetalheCidade(int ID){
        ResultSet result = null;
        String sql = "SELECT city.Name, country.Name, city.District, city.Population\n" +
                "FROM city, country\n" +
                "WHERE city.CountryCode = country.Code AND city.ID = " + ID;
        try{
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public ResultSet getPaises(){
        ResultSet result = null;
        String sql = "SELECT Code, Name\n" +
                "FROM country\n" +
                "ORDER BY Name ASC";
        try{
            Statement s = connection.createStatement();
            result = s.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean inserirCidade (Cidade c){
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,c.getNomeCidade());
            statement.setString(2,c.getCodPais());
            statement.setString(3,c.getDistrito());
            statement.setInt(4,c.getPop());
            //executar a inserção
            int linhas = statement.executeUpdate();
            if(linhas == 1){
                return true;
            }
            else return false;

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }



}
