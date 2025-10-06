package com.devsDoAgi.SAFeR.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExcepiontHandler {

    //TRANSAÇÃO
    @ExceptionHandler(TransactionNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleTransactionNotFound (TransactionNotFound transac, HttpServletRequest request){
        ErrorResponseDTO error = new ErrorResponseDTO(
                transac.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //DISPOSITIVO
    @ExceptionHandler(DeviceNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleDeviceNotFound (DeviceNotFound device, HttpServletRequest request){
        ErrorResponseDTO error = new ErrorResponseDTO(
                device.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //CONTA
    @ExceptionHandler(AccounNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleAccounNotFound(AccounNotFound acc, HttpServletRequest request){

        ErrorResponseDTO error = new ErrorResponseDTO(
                acc.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //CLIENTE
    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleClientNotFound (ClientNotFound cli, HttpServletRequest request){

        ErrorResponseDTO error = new ErrorResponseDTO(
                cli.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //RESTRIÇÃO
    @ExceptionHandler(RestrictionNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleRestrictionNotFound (RestrictionNotFound restri, HttpServletRequest request){

        ErrorResponseDTO error = new ErrorResponseDTO(
                restri.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(EmptyNightLimit.class)
    public ResponseEntity<ErrorResponseDTO> handleEmptyNightLimit (EmptyNightLimit exe, HttpServletRequest request){
        ErrorResponseDTO error = new ErrorResponseDTO(
                exe.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
