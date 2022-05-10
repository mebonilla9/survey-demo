package co.edu.umb.surveydemo.business.controller;

import co.edu.umb.surveydemo.business.lasting.ERoute;
import co.edu.umb.surveydemo.domain.entity.SurveyData;
import co.edu.umb.surveydemo.domain.entity.SurveyResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "SurveyServlet",
  urlPatterns = {
    ERoute.Survey.BASE,
    ERoute.Survey.SAVE,
    ERoute.Survey.PREFERENCES,
    ERoute.Survey.SHOW
  }
)
public class SurveyServlet extends HttpServlet {

  private final List<SurveyData> data = new ArrayList<>();

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request,
                                HttpServletResponse response) {
    response.setContentType("text/html;charset=UTF-8");
    try {
      var path = switch (request.getServletPath()) {
        case ERoute.Survey.BASE -> initPage(request);
        case ERoute.Survey.SAVE -> saveSurvey(request);
        case ERoute.Survey.SHOW -> showData(request);
        case ERoute.Survey.PREFERENCES -> loadPreferences(request, response);
        default -> throw new AssertionError();
      };
        request.getRequestDispatcher(path).forward(request, response);

    } catch (IOException | ServletException e) {
      e.printStackTrace(System.err);
    }
  }

  private String initPage(HttpServletRequest request) {
    var cookies = request.getCookies();
    if (cookies == null){
      request.getSession().setAttribute("theme", "claro");
      return "/index.jsp";
    }
    var cookiesList = Arrays.asList(cookies);
    var theme = cookiesList.stream()
      .filter(c -> c.getName().equals("theme"))
      .findFirst();
    if (theme.isEmpty()){
      request.getSession().setAttribute("theme", "claro");
      return "/index.jsp";
    }
    request.getSession().setAttribute("theme", theme.get().getValue());
    return "/index.jsp";
  }

  private String loadPreferences(HttpServletRequest request, HttpServletResponse response) {
    var themeValue = request.getParameter("cbxTheme");
    if (themeValue != "none"){
      var cookieTheme = new Cookie("theme",themeValue);
      cookieTheme.setMaxAge(60*60*24);
      response.addCookie(cookieTheme);
    }
    return "/survey";
  }

  private String showData(HttpServletRequest request) {
    var results = SurveyResult.builder()
      .yearsData(data.stream().collect(Collectors.groupingBy(SurveyData::expYears, Collectors.counting())))
      .professionData(data.stream().collect(Collectors.groupingBy(SurveyData::profession, Collectors.counting())))
      .frontEndData(data.stream().collect(Collectors.groupingBy(SurveyData::frontEnd, Collectors.counting())))
      .backEndData(data.stream().collect(Collectors.groupingBy(SurveyData::backEnd, Collectors.counting())))
      .dataBaseData(data.stream().collect(Collectors.groupingBy(SurveyData::dataBase, Collectors.counting())))
      .build();
    request.setAttribute("years", results.yearsData());
    request.setAttribute("professions", results.professionData());
    request.setAttribute("frontEnds", results.frontEndData());
    request.setAttribute("backEnds", results.backEndData());
    request.setAttribute("dataBases", results.dataBaseData());
    return "/results.jsp";
  }

  private String saveSurvey(HttpServletRequest request) {
    var surveyData = SurveyData.builder()
      .name(request.getParameter("txtName"))
      .document(request.getParameter("txtDocument"))
      .profession(request.getParameter("txtProfession"))
      .expYears(request.getParameter("txtYears"))
      .frontEnd(request.getParameter("cbxFront"))
      .backEnd(request.getParameter("cbxBack"))
      .dataBase(request.getParameter("cbxDb"));
    data.add(surveyData.build());
    return "/index.jsp";
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request  servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException      if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}