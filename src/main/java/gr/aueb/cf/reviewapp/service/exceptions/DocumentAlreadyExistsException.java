package gr.aueb.cf.reviewapp.service.exceptions;

import java.io.Serial;

/**
 * Custom exception class indicating that a document with a given ID already exists.
 * This exception is typically thrown when attempting to create a resource that is already present.
 * @author geozi
 * @version 1
 */
public class DocumentAlreadyExistsException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public DocumentAlreadyExistsException(Class<?> entityClass, String id) {
        super("Entity " + entityClass.getSimpleName() + " with id " + id + " is already inserted");
    }
}