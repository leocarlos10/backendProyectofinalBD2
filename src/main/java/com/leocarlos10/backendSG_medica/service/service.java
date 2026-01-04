package com.leocarlos10.backendSG_medica.service;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public abstract class service {

     protected static final Logger logger = LoggerFactory.getLogger(service.class);

    Map<String, List<?>> responseList;
    Map<String, String> response;

     /**
     * Este metodo se encarga de retornar 
     * Respuestas Http diferentes a ok
     * @param Httpstatus - este es el tipo de respuesta 404, 500 etc
     * @param respuesta - este es un map<String, String>, esto para poder mandar texto y que spring
     * lo leea luego como un json
     * @return - retornamos un objeto de tipo ResponseEntity que contiene el codigo de error
     * y una descripcion.
     */
    protected ResponseEntity<?> ResponseHttp(HttpStatus httpStatus, Map<String, String> respuesta  ){
        return ResponseEntity.status(httpStatus).body(respuesta);
    }

    protected ResponseEntity<?> ResponseHttpOfObject(HttpStatus httpStatus, Map<String, List<?>> responseList  ){
        return ResponseEntity.status(httpStatus).body(responseList);
    }

    /**
     * Este metodo es el encargado de crear 
     * el Map con el nombre y la descripcion
     */
    protected Map<String, String> respuesta(String clave, String valor){
        response = new HashMap<>();
        response.put(clave, valor);
        return response; 
    }
}
