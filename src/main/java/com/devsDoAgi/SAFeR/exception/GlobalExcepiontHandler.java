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
    public ResponseEntity<ErroResponseDTO> handleTransactionNotFound (TransactionNotFound transac, HttpServletRequest request){
        ErroResponseDTO error = new ErroResponseDTO(
                transac.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //DISPOSITIVO
    @ExceptionHandler(DeviceNotFound.class)
    public ResponseEntity<ErroResponseDTO> handleDeviceNotFound (DeviceNotFound device, HttpServletRequest request){
        ErroResponseDTO error = new ErroResponseDTO(
                device.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //CONTA
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<ErroResponseDTO> handleAccounNotFound(AccountNotFound acc, HttpServletRequest request){

        ErroResponseDTO error = new ErroResponseDTO(
                acc.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //CLIENTE
    @ExceptionHandler(ClientNotFound.class)
    public ResponseEntity<ErroResponseDTO> handleClientNotFound (ClientNotFound cli, HttpServletRequest request){

        ErroResponseDTO error = new ErroResponseDTO(
                cli.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    //RESTRIÇÃO
    @ExceptionHandler(RestrictionNotFound.class)
    public ResponseEntity<ErroResponseDTO> handleRestrictionNotFound (RestrictionNotFound restri, HttpServletRequest request){

        ErroResponseDTO error = new ErroResponseDTO(
                restri.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
