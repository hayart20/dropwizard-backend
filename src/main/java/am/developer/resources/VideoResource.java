/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package am.developer.resources;

import am.developer.model.VideoEntity;
import com.codahale.metrics.annotation.Timed;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.ws.rs.Consumes;

/**
 *
 * @author haykh
 */

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

@Path("/videos")
@Produces(MediaType.APPLICATION_JSON)
public class VideoResource {
    private final String host;
    private final String port;
    private final String database;
    private MongoCollection<Document> collection;
        
    public VideoResource(String host, String port, String database) {
        this.host = host;
        this.port = port;
        this.database = database;
    }

    void initMongoDb(){
        MongoClient client = new MongoClient(this.host, Integer.valueOf(this.port));
        MongoDatabase myMongDB = client.getDatabase(this.database);
        //select collection
        this.collection = myMongDB.getCollection("video");
    }
    
    @Path("/save")
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Timed
    public Response publishArticleData(@Valid VideoEntity data) {
        final Map<String,Object> personMap = new HashMap<String, Object>();

        personMap.put("id", data.getId());
        personMap.put("title", data.getTitle());
        personMap.put("status", data.getStatus());
        personMap.put("description", data.getDescription());    
        personMap.put("link", data.getLink());    
        personMap.put("code", data.getCode());    
        
        Document doc = new Document(personMap);
        collection.insertOne(doc);
        
        return Response.ok().entity("\"Success\"").type(MediaType.APPLICATION_JSON).build();
    }
    
    @Path("/videolist")
    @GET
    @Timed
    public List<VideoEntity> getVideos() {
        return doAdvancedSearch("some text");
    }
    
    public List<VideoEntity> doAdvancedSearch(String searchString) {
        List<VideoEntity> list = new ArrayList<VideoEntity>();

        //DBCursor cursor = collection.find(new BasicDBObject("$text", new BasicDBObject("$search", searchString)));
        //while (cursor.hasNext()) {
          //  DBObject document = cursor.next();
            VideoEntity data = new VideoEntity();
            data.setTitle("filename1");
            data.setDescription("file descripotions"); //(String) document.get("title"));
            list.add(data);
            
            data = new VideoEntity();
            data.setTitle("filename2");
            data.setDescription("file descripotions2"); //(String) document.get("title"));
            list.add(data);
        //}

        return list;
    }
}
