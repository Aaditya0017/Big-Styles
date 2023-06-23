package com.ecom.apis.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOtp {

    @Id
    @SequenceGenerator(name = "otp_seq",sequenceName = "otp_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "otp_seq")
    private Long otpId;
    @NotBlank(message = "Please provide the email of the user")
    private String email;
    @NotBlank(message = "Provide the otp")
    private String code;
}
