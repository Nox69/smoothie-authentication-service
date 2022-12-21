package com.nox.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "customer_id", insertable = false, updatable = false, nullable = false)
    private UUID customerId;
    @Column(name = "customer_email_id")
    private String customerEmailId;
    @Column(name = "customer_password")
    private String customerPassword;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_role")
    private String customerRole;
    @Column(name = "customer_added_date")
    private Date customerAddedDate;

}
