package es.um.sisdist.backend.dao.database;

import es.um.sisdist.backend.dao.models.DataBase;

/**
 * A partir de la URL de la base de datos, se pueden añadir y eliminar
pares clave/valor a la base de datos, así como obtener listados de
valores y lanzar procesamientos map-reduce *
 */
public interface IDatabaseDAO {
	 public void createDatabase(DataBase database);
	 public void deleteDatabase(String databaseId);
	 public DataBase getDatabase(String databaseId);
	 public void addClaveValor();
	 public void deleteClaveValor();
	 public void getValues();

}
