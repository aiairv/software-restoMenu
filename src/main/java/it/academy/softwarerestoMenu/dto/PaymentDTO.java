package it.academy.softwarerestoMenu.dto;

import jdk.jshell.Snippet;
import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private BigDecimal amount;
    private String status;
}
