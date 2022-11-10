package com.st.app.rewardprovider.dto;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RewardResponseDTO {
    int totalPoint;
    BigDecimal totalPurchase;
}
