package es.um.sisdist.backend.dao.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Optional;
import java.util.function.Supplier;

import es.um.sisdist.backend.dao.models.DataBase;
import es.um.sisdist.backend.dao.utils.Lazy;

public class SQLDatabaseDAO implements IDatabaseDAO{
    Supplier<Connection> conn;

	public SQLDatabaseDAO() {
	    conn = Lazy.lazily(() -> {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver").getConstructor().newInstance();

	            // Si el nombre del host se pasa por environment, se usa aquí.
	            // Si no, se usa localhost. 
	            String sqlServerName = Optional.ofNullable(System.getenv("SQL_SERVER")).orElse("localhost");
	            String dbName = Optional.ofNullable(System.getenv("DB_NAME")).orElse("ssdd");
	            return DriverManager.getConnection(
	                "jdbc:mysql://" + sqlServerName + "/" + dbName + "?user=root&password=root");
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	    });
	}

	 @Override
	 public void createDatabase(DataBase database) {
	 // Utiliza la instancia de MongoDB y el objeto Database para crear la base de datos
	 }
	 @Override
	 public void deleteDatabase(String databaseId) {
	 // Utiliza la instancia de MongoDB y el ID de la base de datos para eliminarla
	 }
	 @Override
	 public DataBase getDatabase(String databaseId) {
	 // Utiliza la instancia de MongoDB y el ID de la base de datos para obtenerla
	 // Devuelve el objeto Database con la información de la base de datos
	 return null;
	 }
	@Override
	public void addClaveValor() {
		// Añade un par Clave,Valor a la base de datos 
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteClaveValor() {
		// Elimina un par Clave,Valor de la base de datos 
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getValues() {
		// Obtiene lista de todos los pares Clave,Valor de la base de datos 

		// TODO Auto-generated method stub
		
	}


}
