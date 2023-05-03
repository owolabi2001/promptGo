package com.tem.TransportApp.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class GenericResponse {


    private String code;
    private String message;
    private Object data;
    private Object metaData;
}
