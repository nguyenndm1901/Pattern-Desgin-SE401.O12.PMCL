import Database.Connection;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        boolean check = check();
        System.out.println(check);
    }

    private static boolean check() {
        MongoCollection<Document> collection = Connection.getDatabase().getCollection("laptop");
        String check = "laptop-10";
        Document document = new Document("code", check);
        try (MongoCursor<Document> cursor = collection.find(document).iterator()) {
            return cursor.hasNext();
        }
    }
}