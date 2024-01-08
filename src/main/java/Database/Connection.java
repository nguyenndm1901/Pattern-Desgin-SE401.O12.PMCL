package Database;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Connection {

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private Connection() {

    }

    public static MongoDatabase getDatabase() {
        if (database == null) {
            String mongoURI = "mongodb+srv://test:test@testingspace.akveg7t.mongodb.net/?retryWrites=true&w=majority";
            String databaseName = "dplaptop";

            MongoClientURI uri = new MongoClientURI(mongoURI);
            mongoClient = new MongoClient(uri);
            database = mongoClient.getDatabase(databaseName);
        }
        return database;
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }

}
