package ua.training.webController;

import ua.training.model.Wagon;
import ua.training.model.WagonComfortType;
import ua.training.util.WagonDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Dibrova Serhii
 */
@WebServlet({"/wagonJDBCList", "/newWagon", "/listWagons",
        "/edit", "/delete", "/insert", "/update", "/searchWagon" , "/search"})
public class WagonJDBCServlet extends HttpServlet {

    private WagonDAO wagonDAO;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        wagonDAO = new WagonDAO(jdbcURL, jdbcUsername, jdbcPassword);

    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        try {
            switch (action) {
                case "/newWagon":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertWagon(request, response);
                    break;
                case "/delete":
                    deleteWagon(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateWagon(request, response);
                    break;
                case "/search":
                    showSearchWagon(request, response);
                    break;
                case "/searchWagon":
                    searchWagon(request, response);
                    break;
                default:
                    listWagon(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }




    /**
     * @see WagonDAO#listAllWagons()
     */
    private void listWagon(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Wagon> listWagon = wagonDAO.listAllWagons();
        List resultTotalOfPassengers = Collections.singletonList(listWagon.stream().mapToInt(o -> o.getNumberOfPassengers()).sum());
        List resultTotalOfLuggage = Collections.singletonList(listWagon.stream().mapToInt(o -> o.getAmountOfLuggage()).sum());
        request.setAttribute("TotalOfLuggage", resultTotalOfLuggage.get(0));
        request.setAttribute("TotalOfPassengers", resultTotalOfPassengers.get(0));
        request.setAttribute("listWagon", listWagon);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromJDBC/wagonList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromJDBC/wagonForm.jsp");
        dispatcher.forward(request, response);
    }
    private void showSearchWagon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromJDBC/searchForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Wagon existingWagon = wagonDAO.getWagon(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromJDBC/wagonForm.jsp");
        request.setAttribute("wagon", existingWagon);
        dispatcher.forward(request, response);

    }
    /**
     * @see WagonDAO#insertWagon(Wagon)
     */
    private void insertWagon(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        WagonComfortType type = WagonComfortType.valueOf(request.getParameter("type"));
        int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
        int amountOfLuggage = Integer.parseInt(request.getParameter("amountOfLuggage"));
        Wagon newWagon = new Wagon(id, type, numberOfPassengers, amountOfLuggage);
        wagonDAO.insertWagon(newWagon);
        response.sendRedirect("/listWagons");

    }
    /**
     * @see WagonDAO#updateWagon(Wagon)
     */
    private void updateWagon(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        WagonComfortType type = WagonComfortType.valueOf(request.getParameter("type"));
        int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
        int amountOfLuggage = Integer.parseInt(request.getParameter("amountOfLuggage"));
        Wagon wagon = new Wagon(id, type, numberOfPassengers, amountOfLuggage);
        wagonDAO.updateWagon(wagon);
        response.sendRedirect("/listWagons");
    }
    /**
     * @see WagonDAO#deleteWagon(Wagon)
     */
    private void deleteWagon(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Wagon wagon = new Wagon(id);
        wagonDAO.deleteWagon(wagon);
        response.sendRedirect("/listWagons");

    }

    /**
     * search by free places
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     */
    private void searchWagon(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        List<Wagon> listWagon = wagonDAO.listAllWagons();
        List<Wagon> searchedWagon = new ArrayList<>();
        for (int i = 0; i < listWagon.size(); i++) {
            int freePlaces=listWagon.get(i).getMaxPassengers()-listWagon.get(i).getNumberOfPassengers();
            if (id < freePlaces) {
                searchedWagon.add(listWagon.get(i));
            }
        }
        List sortedByType = searchedWagon.stream()
                .sorted((s1, s2) -> {
                    return s1.getType().compareTo(s2.getType());
                }).collect(Collectors.toList());
        request.setAttribute("listWagon", sortedByType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromJDBC/wagonListSearch.jsp");
        dispatcher.forward(request, response);

    }

}
