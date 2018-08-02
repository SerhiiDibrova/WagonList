package ua.training.webController;


import ua.training.model.Manager;
import ua.training.model.Wagon;
import ua.training.model.WagonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * created by Dibrova Serhii
 */
@WebServlet("/wagonRandomList")
public class WagonRandomServlet extends HttpServlet {

    private Manager manager = new Manager();
    private WagonBuilder builder = new WagonBuilder();
    private List sortedByType;
    List resultTotalOfPassengers;
    List resultTotalOfLuggage;

    @Override
    public void init() {
        initialization();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {
        httpServletResponse.setContentType("text/html; charset=UTF-8");

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("transportList", sortedByType);
        session.setAttribute("TotalOfLuggage", resultTotalOfLuggage.get(0));
        session.setAttribute("TotalOfPassengers", resultTotalOfPassengers.get(0));
        getServletConfig().getServletContext()
                .getRequestDispatcher("/jsp/fromArrayListRandom/wagonList.jsp")
                .forward(httpServletRequest, httpServletResponse);
    }

    /**
     * for initialization
     * added random wagon list
     * and sorted by type comfort
     */
    private void initialization() {
        List<Wagon> wagons = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int id = i + 1;
            setRandomType(wagons, manager, builder, id);
        }
        resultTotalOfPassengers = Collections.singletonList(wagons.stream().mapToInt(o -> o.getNumberOfPassengers()).sum());
        resultTotalOfLuggage = Collections.singletonList(wagons.stream().mapToInt(o -> o.getAmountOfLuggage()).sum());
        sortedByType = wagons.stream()
                .sorted((s1, s2) -> {
                    return s1.getType().compareTo(s2.getType());
                }).collect(Collectors.toList());
    }

    /**
     * Choose Random Type
     * Manager gets the concrete builder object from the client
     * (application code). That's because application knows better which
     * builder to use to get a specific product.
     * The final product is often retrieved from a builder object, since
     * Manager is not aware and not dependent on concrete builders and products.
     *
     * @param wagons
     * @param manager
     * @param builder
     * @param id
     */
    private void setRandomType(List<Wagon> wagons, Manager manager, WagonBuilder builder, int id) {
        switch (new Random().nextInt(4)) {
            case 0:
                manager.constructRandomCommonWagon(builder, id);
                wagons.add(builder.getBuild());
                break;
            case 1:
                manager.constructRandomCouchetteWagon(builder, id);
                wagons.add(builder.getBuild());
                break;
            case 2:
                manager.constructRandomCompartmentWagon(builder, id);
                wagons.add(builder.getBuild());
                break;
            case 3:
                manager.constructRandomBusinessWagon(builder, id);
                wagons.add(builder.getBuild());
                break;
        }
    }
}