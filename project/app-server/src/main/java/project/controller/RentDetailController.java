package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.RentService;
import project.vo.Rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/rent/detail")
public class RentDetailController implements PageController {
    @Autowired
    RentService rentService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int no = Integer.parseInt(request.getParameter("no"));
            Rent rent = rentService.get(no);

            if(rent != null){
                request.setAttribute("rent", rent);
            }
            return "/WEB-INF/jsp/rent/detail.jsp";

        } catch (Exception e) {
            request.setAttribute("refresh", "5;url=/rent/list");
            throw e;
        }
    }
}