package com.company.entity;

import com.company.dto.AddressCreateReqDto;
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
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(nullable = false)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.EAGER)
    private List<Customer> customers;

    @Basic(optional = false)
    @Column(nullable = false)
    private BigDecimal latitude;

    @Basic(optional = false)
    @Column(nullable = false)
    private BigDecimal longitude;

    public Address(AddressCreateReqDto dto){
        this.id = dto.getId();
        this.title=dto.getTitle();
        this.latitude=dto.getLatitude();
        this.longitude=dto.getLongitude();
    }
}
