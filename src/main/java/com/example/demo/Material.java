package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.springframework.data.annotation.Id;


/*!
	@brief Класс объекта материала, используемый при работе с базой данных
*/
@Entity
@Table(name = "materials")
public class Material {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //! Идентификатор для первиного ключа
    public Integer id;
    //! название материала
    public String name;
    //! количество материала
    public int quantity;
    //! сеттер для идентификатора
    public void setId(Integer id) {
        this.id = id;
    }
    //! сеттер для имени
    public void setName(String name) {
        this.name = name;
    }
    //! геттер для идентификатора
    public Integer getId() {
        return id;
    }
    //! геттер для идентификатора
    public String getName() {
        return name;
    }
    //! сеттер для количества
    public void setQuantity(int i) {
        this.quantity = i;
    }
    //! геттер для количества
    public Integer getQuantity() {
        return quantity;
    }
    //! перегруженный метод вывода в консоль
    @Override
    public String toString() {
        return this.name + " + " + this.quantity + "";
    }
}