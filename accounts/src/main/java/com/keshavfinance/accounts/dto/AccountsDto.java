package com.keshavfinance.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
public class AccountsDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    @Schema(
            description = "Account Number of KeshavFinance account", example = "3454433243"
    )
    private Long accountNumber;


    @NotEmpty(message = "Account type cannot be null or empty")
    @Schema(
            description = "Account type of KeshavFinance account", example = "Savings"
    )
    private String accountType;

    @NotEmpty(message = "Branch address cannot be null or empty")
    @Schema(
            description = "KeshavFinance branch address", example = "123 NewYork"
    )
    private String branchAddress;
}
