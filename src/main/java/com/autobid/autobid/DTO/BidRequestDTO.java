// File: src/main/java/com/autobid/autobid/DTO/BidRequestDTO.java
package com.autobid.autobid.DTO;

import lombok.Data;

@Data
public class BidRequestDTO {
    private Integer user_id;
    private double bid_amount;
}