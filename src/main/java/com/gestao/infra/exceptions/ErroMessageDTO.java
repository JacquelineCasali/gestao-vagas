package com.gestao.infra.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroMessageDTO {
    String field;
    String message;
}
