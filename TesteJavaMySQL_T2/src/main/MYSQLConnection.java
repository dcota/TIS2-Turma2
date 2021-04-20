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
    //construtor que cria a ligação à base de dados
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
    //método para consultar uma tabela na base de dados
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
    //inserir uma linha na tabela city
    public void insertLineCity(String nome, String codigo, String distrito, int popul){
        try {
            //preprar a inserção da nova linha
            String sql = "INSERT INTO city (Name, CountryCode, District, Population) VALUES (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,nome);
            statement.setString(2,codigo);
            statement.setString(3,distrito);
            statement.setInt(4,popul);
            //executar a inserção
            int linhas = statement.executeUpdate();
            if(linhas == 1){
                System.out.println("Linha adicionada com sucesso!");
            }

        } catch (SQLException e){
            System.out.println("Ocorreu um erro ao inserir a linha...");
        }
    }
    //eliminar uma linha da tabela city
    public void deleteFromCity(int ID){
        try {
            String sql = "DELETE FROM city WHERE ID=" + ID;
            Statement statement = connection.createStatement();
            int linhas = statement.executeUpdate(sql);
            if(linhas == 1){
                System.out.println("Linha eliminada com sucesso");
            } else if (linhas == 0) {
                System.out.println("ID não encontrado");
            }
        } catch (SQLException e){
            System.out.println("Ocorreu um erro na eliminação da linha...");
        }
    }

    //calcular a média de população de um país
    public void mediaPopul() throws SQLException {
        String sql = "SELECT Name, Population FROM city WHERE CountryCode = 'PRT'";
        ResultSet result = querySELECT(sql);
        int somaPop = 0;
        int cont = 0;
        while(result.next()){
            somaPop += result.getInt(2);
            cont++;
        }
        System.out.println(somaPop);
        System.out.println(cont);
        System.out.println("Média da população: " + (somaPop / cont));


    }


}
