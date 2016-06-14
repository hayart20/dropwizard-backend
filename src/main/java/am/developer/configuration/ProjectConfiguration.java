/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package am.developer.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author haykh
 */
public class ProjectConfiguration extends Configuration {

    @NotEmpty
    private String mongoDbHost;

    @NotEmpty
    private String mongoDBname;

    @NotEmpty
    private String mongoDBport;

    @JsonProperty
    public String getMongoDbHost() {
        return mongoDbHost;
    }

    @JsonProperty
    public void setMongoDbHost(String mongoDbHost) {
        this.mongoDbHost = mongoDbHost;
    }

    @JsonProperty
    public String getMongoDBname() {
        return mongoDBname;
    }

    @JsonProperty
    public void setMongoDBname(String mongoDBname) {
        this.mongoDBname = mongoDBname;
    }

    @JsonProperty
    public String getMongoDBport() {
        return mongoDBport;
    }

    @JsonProperty
    public void setMongoDBport(String mongoDBport) {
        this.mongoDBport = mongoDBport;
    }

}
