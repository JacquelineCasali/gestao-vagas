package com.gestao.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroMessageDTO {
    String message;
    String field;
}
