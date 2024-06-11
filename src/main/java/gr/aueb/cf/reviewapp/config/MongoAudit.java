package gr.aueb.cf.reviewapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * The {@link MongoAudit} class enables the activation
 * of the @CreatedDate and @LastModifiedDate annotations
 * used in domain model classes.
 * @author geozi
 * @version 1
 */
@Configuration
@EnableMongoAuditing
public class MongoAudit {
}
