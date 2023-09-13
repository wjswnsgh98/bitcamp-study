package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.ReserveService;
import project.vo.Reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/reserve/detail")
public class ReserveDetailController implements PageController {
    @Autowired
    ReserveService reserveService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int reserveNo = Integer.parseInt(request.getParameter("no"));
            Reserve reserve = reserveService.get(reserveNo);

            if(reserve != null){
                request.setAttribute("reserve", reserve);
            }
            return "/WEB-INF/jsp/reserve/detail.jsp";

        } catch (Exception e) {
            request.setAttribute("refresh", "5;url=/reserve/list");
            throw e;
        }
    }
}