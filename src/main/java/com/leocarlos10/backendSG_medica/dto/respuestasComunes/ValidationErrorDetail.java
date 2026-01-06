package com.leocarlos10.backendSG_medica.dto.respuestasComunes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorDetail {

    private String field;
    private String message;
}
