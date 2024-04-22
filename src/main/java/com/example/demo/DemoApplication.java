package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*!
	@brief main класс, используемый при запуске приложения
*/
@SpringBootApplication
public class DemoApplication {
	/*!
        @brief Главный метод вызываемый при запуске приложения
        @param Принимает на вход строковые параметры аргументов запуска
        @return Ничо не возвращает
    */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
