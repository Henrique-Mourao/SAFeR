package com.devsDoAgi.SAFeR.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroResponseDTO {
    private String menssage;
    private int status;
    private String timeStamp;
    private String path;
}
