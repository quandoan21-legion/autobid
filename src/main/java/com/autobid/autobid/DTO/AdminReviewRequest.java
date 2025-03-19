package com.autobid.autobid.DTO;

import com.autobid.autobid.Entity.CarStatus;
import lombok.Data;

@Data
public class AdminReviewRequest {
    private Integer admin_id; // ID of the admin performing the review
    private CarStatus status; // New status (approved or rejected)
    private String admin_message; // Admin message
}
