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

    //DISPOSITIVO NÃO AUTORIZADO
    @ExceptionHandler(DeviceNotAuthorizedException.class)
    public ResponseEntity<ErroResponseDTO> handleDeviceNotAuthorized (DeviceNotAuthorizedException ex, HttpServletRequest request){
        ErroResponseDTO error = new ErroResponseDTO(
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    //BAD REQUEST
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResponseDTO> handleBadRequest(BadRequestException ex, HttpServletRequest request) {
        ErroResponseDTO error = new ErroResponseDTO(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    //CONTA
    @ExceptionHandler(AccounNotFound.class)
    public ResponseEntity<ErroResponseDTO> handleAccounNotFound(AccounNotFound acc, HttpServletRequest request){

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
    @ExceptionHandler(EmptyNightLimit.class)
    public ResponseEntity<ErroResponseDTO> handleEmptyLimit(EmptyNightLimit ex, HttpServletRequest request){
        ErroResponseDTO error = new ErroResponseDTO(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                request.getRequestURI()
                );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
