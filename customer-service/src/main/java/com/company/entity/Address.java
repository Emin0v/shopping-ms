package com.company.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.EAGER)
    private List<Customer> customers;

//    @JoinColumn(name = "customer_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Customer customer;

    @Basic(optional = false)
    @Column(nullable = false)
    private BigDecimal latitude;

    @Basic(optional = false)
    @Column(nullable = false)
    private BigDecimal longitude;

}
