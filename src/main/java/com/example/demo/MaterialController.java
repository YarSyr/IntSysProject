package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.format.DateTimeFormatter;

@Controller
public class MaterialController {
    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @GetMapping("/")
    public String listMaterials(Model model) {
        List<Material> materials = materialRepository.findAll();
        model.addAttribute("materials", materials);
        return "materials";
    }

    @PostMapping("/adjust")
    public String adjustMaterial(@RequestParam Long materialId, @RequestParam int quantityChange, @RequestParam String action, @RequestParam String invoiceNumber, Model model) {
        if (invoiceNumber.isEmpty()) {
            model.addAttribute("error", "Номер накладной обязателен.");
            return listMaterials(model);
        }

        Material material = materialRepository.findById(materialId).orElseThrow(() -> new IllegalArgumentException("Invalid material ID"));

        if ("withdraw".equals(action)) {
            quantityChange = -quantityChange;
            if (material.getQuantity() < -quantityChange) {
                model.addAttribute("error", "Недостаточное количество материала на складе для списания.");
                return listMaterials(model);
            }
        }

        material.setQuantity(material.getQuantity() + quantityChange);
        materialRepository.save(material);

        // Сохранение накладной
        System.out.println(invoiceNumber);
        Invoice invoice = new Invoice(invoiceNumber);
        invoiceRepository.save(invoice);

        return "redirect:/";
    }

    @GetMapping("/add")
    public String showAddMaterialForm(Model model) {
        model.addAttribute("material", new Material());
        return "add_material";
    }

    @PostMapping("/add")
    public String addMaterial(@ModelAttribute Material material) {
        materialRepository.save(material);
        return "redirect:/";
    }

    @PostMapping("/remove")
    public String removeMaterial(@RequestParam Long materialId, Model model) {
        Material material = materialRepository.findById(materialId).orElseThrow(() -> new IllegalArgumentException("Invalid material ID"));
        if (material.getQuantity() != 0) {
            model.addAttribute("error", "Количество материала должно быть равно нулю для удаления.");
            return listMaterials(model);
        }
        materialRepository.delete(material);
        return "redirect:/";
    }



    // Ваши другие методы контроллера

    @GetMapping("/admin")
    public String adminPage(Model model) {
        List<Material> materials = materialRepository.findAll();
        List<Invoice> invoices = invoiceRepository.findAll();
        model.addAttribute("materials", materials);
        model.addAttribute("invoices", invoices);
        return "admin";
    }

    @Controller
    public class AdminController {

        private ConfigurableApplicationContext context;

        public AdminController(ConfigurableApplicationContext context) {
            this.context = context;
        }

        @GetMapping("/shutdown")
        public void shutdown() {
            SpringApplication.exit(context, () -> 0);
        }
    }
}
