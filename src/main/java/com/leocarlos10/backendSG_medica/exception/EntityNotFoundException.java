package com.leocarlos10.backendSG_medica.exception;

public class EntityNotFoundException extends RuntimeException {

    private String code;
    private String entityName;
    private String identifier;

    public EntityNotFoundException(String entityName, String identifier) {
        super(String.format("%s no encontrado(a): %s", entityName, identifier));
        this.code = "ENTITY_NOT_FOUND";
        this.entityName = entityName;
        this.identifier = identifier;
    }

    public EntityNotFoundException(String message) {
        super(message);
        this.code = "ENTITY_NOT_FOUND";
    }

    public String getCode() {
        return code;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getIdentifier() {
        return identifier;
    }
}
