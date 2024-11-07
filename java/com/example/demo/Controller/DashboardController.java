package com.example.demo.Controller;

import com.example.demo.dao.LawyerDAO;
import com.example.demo.dao.MatrimonialCaseDAO;
import com.example.demo.dao.ParalegalDAO;
import com.example.demo.dao.CivilCaseDAO;
import com.example.demo.dao.ClientDAO;
import com.example.demo.dao.CorporateCaseDAO;
import com.example.demo.dao.CourtDAO;
import com.example.demo.dao.CriminalCaseDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private LawyerDAO lawyerDAO;

    @Autowired
    private ParalegalDAO paralegalDAO;

    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private CorporateCaseDAO CcaseDAO;
    @Autowired
    private CriminalCaseDAO ccaseDAO;
    @Autowired
    private MatrimonialCaseDAO matcaseDAO;
    @Autowired
    private CivilCaseDAO civcaseDAO;

    @Autowired
    private CourtDAO CourtDAO;

    @GetMapping("/dd")
    public String dashboard(Model model) {
        long lawyerCount = lawyerDAO.countLawyers();
        long paralegalCount = paralegalDAO.countParalegals();
        long clientCount = clientDAO.countClients();
        long caseCount = ccaseDAO.countcc()+CcaseDAO.countCc()+matcaseDAO.countmat()+civcaseDAO.countciv();
        int courtCount = CourtDAO.countCourts(); 

        model.addAttribute("lawyerCount", lawyerCount);
        model.addAttribute("paralegalCount", paralegalCount);
        model.addAttribute("clientCount", clientCount);
        model.addAttribute("caseCount", caseCount);
        model.addAttribute("courtCount", courtCount); 

        return "dashboard";
    }
}
