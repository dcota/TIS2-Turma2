package main;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        //1º criar a conexão
        MYSQLConnection connection = new MYSQLConnection();

        //2º adicionar uma linha
        //connection.insertLineCity("Angra do Heroísmo", "PRT", "Açores", 25000);

        //2º eliminar a linha 4083
        //connection.deleteFromCity(4083);
        //3º passo mostra os nomes das cidades
        //ResultSet result = connection.querySELECT("SELECT ID, Name FROM city");
        //mostrar os nomes de todas as cidades
        /*while(result.next()) {
            int id = result.getInt("ID");
            System.out.print(id + " ; ");
            String area = result.getString("Name");
            System.out.println(area);
        }*/

        connection.mediaPopul();
    }

}
