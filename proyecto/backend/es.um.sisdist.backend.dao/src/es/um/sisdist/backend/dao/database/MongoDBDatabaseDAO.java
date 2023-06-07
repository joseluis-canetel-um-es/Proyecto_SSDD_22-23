package es.um.sisdist.backend.dao.database;

import java.util.Optional;
import java.util.function.Supplier;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static java.util.Arrays.*;

import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import es.um.sisdist.backend.dao.models.DataBase;
import es.um.sisdist.backend.dao.utils.Lazy;

public class MongoDBDatabaseDAO implements IDatabaseDAO{
	

	public MongoDBDatabaseDAO() {
	    CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
	        .conventions(asList(Conventions.ANNOTATION_CONVENTION))
	        .automatic(true)
	        .build();
	    CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

	    // falta mirar URL !!!!!!!!!!!!!!!!!!!!!!!!!!!!
	    String uri = "mongodb://root:root@" 
	            + Optional.ofNullable(System.getenv("MONGO_SERVER")).orElse("localhost")
	            + ":27017/ssdd?authSource=admin";

	   /* collection = Lazy.lazily(() -> {
	        MongoClient mongoClient = MongoClients.create(uri);
	        MongoDatabase database = mongoClient
	            .getDatabase(Optional.ofNullable(System.getenv("DB_NAME")).orElse("ssdd"))
	            .withCodecRegistry(pojoCodecRegistry);
	        return database;
	    });*/
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
