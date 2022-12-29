package com.mutlucankarinca.productmanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String productName;

    @NotNull
    private Double unitPrice;

    @NotEmpty
    private String currency;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private LocalDate expirationDate;

    private Boolean status = false;
}
