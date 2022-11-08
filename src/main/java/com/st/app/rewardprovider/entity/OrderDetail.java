package com.st.app.rewardprovider.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The Customer Entity
 */
@Entity
@Table(name = "ORDER_DETAIL")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long orderId;

    @Column
    protected Date created;

    @Column
    private String orderStatus; //TODO: Should be Enum

    @Column(scale = 2)
    private BigDecimal totalPurchase;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private Customer customer; //UNIDirectional

}
