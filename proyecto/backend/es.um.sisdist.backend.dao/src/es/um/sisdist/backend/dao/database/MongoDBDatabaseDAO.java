package es.um.sisdist.backend.dao.database;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static java.util.Arrays.*;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.Conventions;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import es.um.sisdist.backend.dao.models.DataBase;
import es.um.sisdist.backend.dao.models.User;
import es.um.sisdist.backend.dao.utils.Lazy;

public class MongoDBDatabaseDAO implements IDatabaseDAO{
	

    private Supplier<MongoCollection<DataBase>> collection;

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

	    collection = Lazy.lazily(() -> 
        {
        	MongoClient mongoClient = MongoClients.create(uri);
        	MongoDatabase database = mongoClient
        		.getDatabase(Optional.ofNullable(System.getenv("DB_NAME")).orElse("ssdd"))
        		.withCodecRegistry(pojoCodecRegistry);
        	return database.getCollection("databases", DataBase.class);
        });
	}


	 @Override
	 public boolean deleteDatabase(String databaseId) {
		 try {
	            collection.get().deleteOne(eq("_id", databaseId));
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	 }
	 
	 // retorna db insertada con anterioridad
	 @Override
	 public DataBase getDatabase(String databaseId) {
	     try {
	         MongoCollection<DataBase> mongoCollection = collection.get(); // Obtener la colección de la base de datos
	         DataBase result = mongoCollection.find(eq("_id", databaseId)).first(); // Obtener el primer documento que coincida con el filtro

	         return result;
	     } catch (Exception e) {
	     }

	     return null;
	 }


	@Override
	public void addClaveValor(String db, HashMap<String, String> pares) {
		// Añade un par Clave,Valor a la base de datos 
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteClaveValor(String dbId, String clave) {
		// Elimina un par Clave,Valor de la base de datos 
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getValues() {
		// Obtiene lista de todos los pares Clave,Valor de la base de datos 

		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean insertDatabase(String db, String idUser) {
	    try {
	        DataBase database = new DataBase(db); // Crear objeto DataBase con el nombre proporcionado
	        database.setId(UUID.randomUUID().toString()); // Generar un ID único para la base de datos
	        database.setIdUser(idUser); // Asignar el ID del usuario propietario

	        collection.get().insertOne(database);

	        return true;
	    } catch (Exception e) {
	        // Manejo de excepciones: capturar y manejar la excepción según sea necesario
	        return false;
	    }
	}


}
