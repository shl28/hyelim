package com.example.roomfit.web;

import com.example.roomfit.domain.ReportStatus;
import com.example.roomfit.service.AdminService;
import com.example.roomfit.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")

public class AdminController {
    private final AdminService adminService;
    private final ReportService reportService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("stats", adminService.dashboard());
        model.addAttribute("reports", reportService.pendingReports());

        return "admin/dashboard";
    }

    @PostMapping("/reports/{id}")
    public String processReport(
            @PathVariable("id") Long id,
            @RequestParam("status")ReportStatus status,
            @RequestParam(name = "adminNote", required = false) String adminNote
            ) {
        reportService.process(id, status, adminNote);
        return "redirect:/admin";
    }
}
