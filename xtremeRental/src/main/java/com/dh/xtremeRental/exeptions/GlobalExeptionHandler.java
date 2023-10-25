package com.dh.xtremeRental.exeptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.TransientPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExeptionHandler.class);

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<String> generarIlegarArgumentException(IllegalArgumentException e){
        e.printStackTrace();
        logger.error("mensaje de error ilegal ---> " + e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler({TransientPropertyValueException.class})
        public ResponseEntity<String> generarTransientPropertyValueException(TransientPropertyValueException e){
        e.printStackTrace();
        logger.error("mensaje de error ilegal ---> " + e.getMessage());
        String mensajeError="------------------- Revisa por favor la estructura de tu Json ------------------";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensajeError + e.getMessage());
    }
}
