package com.example.demo;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "invoices")
public class Invoice {

    @ModelAttribute("dateFormat")
    public DateTimeFormatter dateFormat() {
        return DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    }


    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number")
    public String invoiceNumber;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    public Invoice() {}

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        this.createdAt = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    // геттеры и сеттеры
}