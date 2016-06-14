package am.developer.example;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;

/**
 * Simple Mongo Java client
 *	From java main method – save, update , get and delete collection/document  
 * like  PersonEntiity /id, username, mail, password, first name, last name, age/
 */
public class FirstMongoClient {

    /**
     * Main method for the First Mongo Client. Here we shall be connecting to a mongo
     * instance running on localhost and port 27017.
     *
     * @param args
     */
    public static final void main(String[] args) throws UnknownHostException {
        //create clien
        MongoClient client = new MongoClient("localhost", 27017);
        //connect to database
        MongoDatabase myMongDB = client.getDatabase("mongtesthayk");//this is new way
        //select collection
        MongoCollection<Document> collection = myMongDB.getCollection("person");
        //save data
        save(collection);
        //
        updateCase3(collection);
        //show all
        showAllDocuments(collection);
    }
    
    private static void save(MongoCollection<Document> collection){
        final Map<String,Object> personMap = new HashMap<String, Object>();

        personMap.put("id", "1");
        personMap.put("username", "hayart");
        personMap.put("mail", "hayk@developer.am");
        personMap.put("password", "123456");    
        personMap.put("firstname", "Hayk");    
        personMap.put("lastname", "Harutyunyan");    
        personMap.put("age", 20);    
        Document doc = new Document(personMap);
        collection.insertOne(doc);
    }
    
    public static void showAllDocuments( 
                        final MongoCollection<Document> collection) {
           // TODO retrevie only    person   collection          
        System.out.println("----[Retrieve All Documents in Collection]----");
        for (Document doc: collection.find()) {
          System.out.println(doc.toJson());
        }
    }
    
    private static void updateCase1(MongoCollection<Document> collection){
       final Map<String,Object> personMap = new HashMap<String, Object>();

        personMap.put("id", "1");
        personMap.put("username", "hayart");
        personMap.put("mail", "hayk@developer.am");
        personMap.put("password", "123456");    
        personMap.put("firstname", "Hayk");    
        personMap.put("lastname", "Harutyunyan");    
        personMap.put("age", 30);    
        Document doc1 = new Document(personMap);
        personMap.put("age", 80);    
        Document doc2 = new Document(personMap);
        collection.findOneAndReplace(doc1, doc2);
    }  
    
    
    private static void updateCase2(MongoCollection<Document> collection){
        collection.updateOne(eq("username", "hayart"), new Document("$set", new Document("age", 33)));
        //result { "_id" : { "$oid" : "573eaacc5e482f2b748565c4" }, "password" : "123456", "firstname" : "HAYK", "mail" : "hayk@developer.am", "id" : "1", "age" : 33, "username" : "hayart", "lastname" : "Harutyunyan" }
    }
    
    private static void updateCase3(MongoCollection<Document> collection){
        collection.replaceOne(eq("age", 20), new Document("lastname", "HARUTYUNYAN"));
        //as resault { "_id" : { "$oid" : "573eab6d5e482f11e0227c65" }, "lastname" : "HARUTYUNYAN" }
    }
}
