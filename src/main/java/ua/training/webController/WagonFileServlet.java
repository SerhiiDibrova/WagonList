package ua.training.webController;

import ua.training.model.Wagon;
import ua.training.model.WagonComfortType;
import ua.training.util.FileUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Dibrova Serhii
 */
@WebServlet({"/wagonFileList", "/editCSV", "/deleteCSV", "/updateCSV",
        "/newWagonCSV", "/insertCSV", "/searchWagonCSV", "/searchCSV"})
public class WagonFileServlet extends HttpServlet {
    private FileUtil fileUtil = new FileUtil();
    private final String FILE_PATH = "F:\\wagon.csv";
    //private final String FILE_PATH = getServletContext().getRealPath("/file/wagon.csv");

    private ArrayList<Wagon> wagons;

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


        switch (action) {
            case "/newWagonCSV":
                showNewForm(request, response);
                break;
            case "/insertCSV":
                insertWagon(request, response);
                break;
            case "/deleteCSV":
                deleteWagon(request, response);
                break;
            case "/editCSV":
                showEditForm(request, response);
                break;
            case "/updateCSV":
                updateWagon(request, response);
                break;
            case "/searchCSV":
                showSearchWagon(request, response);
                break;
            case "/searchWagonCSV":
                searchWagon(request, response);
                break;
            default:
                listWagon(request, response);
                break;
        }

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromFile/wagonForm.jsp");
        dispatcher.forward(request, response);
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Wagon wagon = null;
        for (int i = 0; i < wagons.size(); i++) {
            if (wagons.get(i).getId() == id) {
                WagonComfortType type = wagons.get(i).getType();
                int numberOfPassengers = wagons.get(i).getNumberOfPassengers();
                int amountOfLuggage = wagons.get(i).getAmountOfLuggage();
                wagon = new Wagon(id, type, numberOfPassengers, amountOfLuggage);
                System.out.println(wagon.toString());
            }

        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromFile/wagonForm.jsp");
        request.setAttribute("wagon", wagon);
        dispatcher.forward(request, response);

    }

    private void showSearchWagon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromFile/searchForm.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * show all list from file csv
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void listWagon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        wagons = (ArrayList<Wagon>) fileUtil.readFile(FILE_PATH);
        List sortedByType = wagons.stream()
                .sorted((s1, s2) -> {
                    return s1.getType().compareTo(s2.getType());
                }).collect(Collectors.toList());
        List resultTotalOfPassengers = Collections.singletonList(wagons.stream().mapToInt(o -> o.getNumberOfPassengers()).sum());
        List resultTotalOfLuggage = Collections.singletonList(wagons.stream().mapToInt(o -> o.getAmountOfLuggage()).sum());

        request.setAttribute("TotalOfLuggage", resultTotalOfLuggage.get(0));
        request.setAttribute("TotalOfPassengers", resultTotalOfPassengers.get(0));
        request.setAttribute("listWagon", sortedByType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromFile/wagonList.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * delete from file csv
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void deleteWagon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        for (int i = 0; i < wagons.size(); i++) {
            if (wagons.get(i).getId() == id) {
                wagons.remove(i);
            }
        }
        fileUtil.writeFile(FILE_PATH, wagons);
        response.sendRedirect("/wagonFileList");
    }

    /**
     * insert into file csv
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void insertWagon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        WagonComfortType type = WagonComfortType.valueOf(request.getParameter("type"));
        int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
        int amountOfLuggage = Integer.parseInt(request.getParameter("amountOfLuggage"));
        Wagon newWagon = new Wagon(id, type, numberOfPassengers, amountOfLuggage);
        wagons.add(newWagon);
        fileUtil.writeFile(FILE_PATH, wagons);
        response.sendRedirect("/wagonFileList");
    }

    /**
     * update from file
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void updateWagon(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        WagonComfortType type = WagonComfortType.valueOf(request.getParameter("type"));
        int numberOfPassengers = Integer.parseInt(request.getParameter("numberOfPassengers"));
        int amountOfLuggage = Integer.parseInt(request.getParameter("amountOfLuggage"));
        Wagon wagon = new Wagon(id, type, numberOfPassengers, amountOfLuggage);
        for (int i = 0; i < wagons.size(); i++) {
            if (id == wagons.get(i).getId()) {
                wagons.set(i, wagon);
            }
        }
        fileUtil.writeFile(FILE_PATH, wagons);
        response.sendRedirect("/wagonFileList");

    }

    /**
     * search from file csv by free places
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    private void searchWagon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Wagon> searchedWagon = new ArrayList<Wagon>();
        for (int i = 0; i < wagons.size(); i++) {
            int freePlaces = wagons.get(i).getMaxPassengers() - wagons.get(i).getNumberOfPassengers();
            if (id < freePlaces) {
                searchedWagon.add(wagons.get(i));
            }
        }
        List sortedByType = searchedWagon.stream()
                .sorted((s1, s2) -> {
                    return s1.getType().compareTo(s2.getType());
                }).collect(Collectors.toList());
        request.setAttribute("listWagon", sortedByType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/fromFile/wagonListSearch.jsp");
        dispatcher.forward(request, response);

    }
}
