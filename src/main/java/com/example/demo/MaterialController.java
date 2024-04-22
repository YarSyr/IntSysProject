package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/*!
	@brief Класс контроллера для обработки HTTP запросов
*/

@Controller
public class MaterialController {
    @Autowired
    private MaterialRepository materialRepository;
    /*!
        @brief Метод, используемый при обработке запросов
        @param Принимает на вход объект модели
        @return Возвращает название MVC шаблона
    */
    @GetMapping("/")
    public String listMaterials(Model model) {
        List<Material> materials = materialRepository.findAll();
        model.addAttribute("materials", materials);
        return "materials";
    }
    /*!
        @brief Метод, используемый при обработке запросов на настройку
        @param Принимает на вход объект модели а также параметры Post запроса
        @return Возвращает указатель перенаправления на гравную страницу
    */
    @PostMapping("/adjust")
    public String adjustMaterials(Model model, @RequestParam Long materialId, @RequestParam int quantityChange) {
        Material material = materialRepository.findById(materialId).orElseThrow(() -> new IllegalArgumentException("Invalid material ID"));
        material.setQuantity(material.getQuantity() + quantityChange);
        System.out.println(material.getQuantity() + quantityChange);
        System.out.println(material);
        materialRepository.save(material);
        return "redirect:/";
    }

    /*!
            @brief Метод, используемый при обработке запросов на добавление
            @param Принимает на вход объект модели а также параметры Post запроса
            @return Возвращает название MVC шаблона
        */
    @GetMapping("/add")
    public String showAddMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        return "add_material";
    }
    /*!
        @brief Метод, используемый при обработке запроса на добавление
        @param Принимает на вход объект модели а также параметры Post запроса
        @return Возвращает указатель перенаправления на гравную страницу
    */
    @PostMapping("/add")
    public String addMaterial(@ModelAttribute Material material) {
        materialRepository.save(material);
        return "redirect:/";
    }



}

