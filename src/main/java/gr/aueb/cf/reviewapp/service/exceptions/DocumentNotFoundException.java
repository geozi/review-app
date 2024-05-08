package gr.aueb.cf.reviewapp.service.exceptions;

import java.io.Serial;

/**
 * Custom exception class indicating that a document with a given ID or username does not exist.
 * This exception is typically thrown when attempting to retrieve or manipulate a document
 * that is expected to be present but is not found in the database.
 * @author geozi
 * @version 1
 */
public class DocumentNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public DocumentNotFoundException(Class<?> entityClass, String username) {
        super("Document " + entityClass.getSimpleName() + " with username" + username + " does not exist");
    }

    public DocumentNotFoundException(Class<?> entityClass, Long id) {
        super("Document " + entityClass.getSimpleName() + " with id " + id + " does not exist");
    }

}