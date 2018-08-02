package ua.training.Filter;

import ua.training.model.WagonComfortType;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * created by Dibrova Serhii
 */
@WebFilter(filterName = "ValidationFilter", urlPatterns = {"/insert", "/update", "/insertCSV", "/updateCSV"})
public class ValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String errorPage = "/jsp/error/validationError.jsp";
        int numberOfPassengers = Integer.parseInt(servletRequest.getParameter("numberOfPassengers"));
        int amountOfLuggage = Integer.parseInt(servletRequest.getParameter("amountOfLuggage"));
        WagonComfortType type = WagonComfortType.valueOf(servletRequest.getParameter("type"));
        if (servletRequest.getParameter("id").equals("")
                || servletRequest.getParameter("numberOfPassengers").equals("")
                || servletRequest.getParameter("amountOfLuggage").equals("")) {

            servletRequest.setAttribute("errMsg", "Field is empty");

            RequestDispatcher rd = servletRequest.getRequestDispatcher(errorPage);
            rd.include(servletRequest, servletResponse);

        } else if (numberOfPassengers > type.getMaxPassenger() || numberOfPassengers < 0) {
            servletRequest.setAttribute("errMsg", "Min - 0 and Max passenger - " + type.getMaxPassenger());

            RequestDispatcher rd = servletRequest.getRequestDispatcher(errorPage);
            rd.include(servletRequest, servletResponse);

        } else if (amountOfLuggage > type.getMaxPassenger() || amountOfLuggage < 0) {
            servletRequest.setAttribute("errMsg", "Min - 0 and Max luggage - " + type.getMaxPassenger());

            RequestDispatcher rd = servletRequest.getRequestDispatcher(errorPage);
            rd.include(servletRequest, servletResponse);
        } else {

            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
