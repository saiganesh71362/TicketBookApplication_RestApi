package com.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Ticket_Details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Ticket_Id")
	private Integer id;
     @Column(name ="Person_Name")
	private String name;
     @Column(name = "Person_Gender")
	private String gender;
     @Column(name = "Ticket_categorys")
	private String category;
     @Column(name = "Ticket_Price")
    private Double price;
}
