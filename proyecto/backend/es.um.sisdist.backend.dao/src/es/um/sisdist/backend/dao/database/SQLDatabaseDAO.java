package es.um.sisdist.backend.dao.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Supplier;

import com.mysql.cj.xdevapi.Result;

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

	// crea la base de datos
	// falta modificar
	 private Optional<DataBase> createDatabase(Result result) {
			    try {
			        return Optional.of(new DataBase(result.getString(1)));
			    } catch (SQLException e) {
			        return Optional.empty();
			    }
	 }
	 
	 @Override
	 public void deleteDatabase(String databaseId) {
	 }
	 @Override
	 public DataBase getDatabase(String databaseId) {
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

	@Override
	public void insertDatabase(DataBase database) {
		// TODO Auto-generated method stub
		
	}


}
