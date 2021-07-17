package com.company.entity;

import com.company.dto.CustomerCreateReqDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString(exclude = {"password"})
@Builder
public class Customer implements Serializable {

    @Id
    @Basic(optional = false)
    private Long id;

    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Min(0)
    @Column(nullable = false)
    private BigDecimal balance;

    @JoinColumn(name = "address_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Address address;


    @PrePersist
    public void prePersist(){
        setBalance(BigDecimal.valueOf(0));
    }


    public Customer(CustomerCreateReqDto dto){
        this.id = dto.getId();
        this.name = dto.getName();
        this.surname = dto.getSurname();
        this.username = dto.getUsername();
        this.password = dto.getPassword();
        this.balance = dto.getBalance();
        this.address = new Address(dto.getAddressCreateReqDto());
    }

}
