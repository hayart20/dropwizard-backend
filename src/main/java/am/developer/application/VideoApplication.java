/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package am.developer.application;

import am.developer.configuration.ProjectConfiguration;
import am.developer.resources.VideoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 *
 * @author haykh
 */
public class VideoApplication  extends Application<ProjectConfiguration> {

    public static void main(String[] args) throws Exception {
        new VideoApplication().run(new String[] { "server", "src/main/resources/mongo-db.yml" });
    }

    @Override
    public void run(ProjectConfiguration configuration,
                    Environment environment) {
        final VideoResource resource = new VideoResource(
            configuration.getMongoDbHost(),
            configuration.getMongoDBport(),
            configuration.getMongoDBname()
        );

        environment.jersey().register(resource);
    }

    
}
