package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
/*!
	@brief Репозиторий для работы с базой данных
*/
public interface MaterialRepository extends JpaRepository<Material, Long> {
}

