package ca.bc.gov.gwa.developerkey.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.bc.gov.gwa.servlet.BaseServlet;

@WebServlet(urlPatterns = "/rest/apiKeys", loadOnStartup = 1)
public class ApiKeyServlet extends BaseServlet {
  private static final long serialVersionUID = 1L;

  // @Override
  // protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
  // throws ServletException, IOException {
  // final String userId = request.getRemoteUser();
  // final String pathInfo = request.getPathInfo();
  // if (isPathEmpty(pathInfo)) {
  // response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
  // } else {
  // final String deletePath = "/consumers/" + userId + "/key-auth" + pathInfo;
  // this.apiService.handleDelete(request, response, deletePath);
  // }
  // }

  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    this.apiService.developerApiKeyGet(request, response);
  }

  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    this.apiService.developerApiKeyAdd(request, response);
  }
}