package com.st.app.rewardprovider.entity;

import lombok.*;

import javax.persistence.*;

/**
 * The Customer Entity
 */
@Entity
@Table(name = "customer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long customerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String emailId;

}
