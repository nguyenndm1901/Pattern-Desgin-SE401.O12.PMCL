import Database.Connection;
import Entities.Laptop;
import Factory.LaptopFactory;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import Enum.LaptopOS;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println(randomCode());
    }
    private static String randomCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

        int length = 10;

        StringBuilder randomCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i< length; i++) {
            int index = random.nextInt(characters.length());
            randomCode.append(characters.charAt(index));
        }

        return randomCode.toString();
    }
}