package com.leocarlos10.backendSG_medica.dto.respuestasComunes;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int responseCode;
    private String responseMessage;
    private T data;
    private List<?> errorList;
}
