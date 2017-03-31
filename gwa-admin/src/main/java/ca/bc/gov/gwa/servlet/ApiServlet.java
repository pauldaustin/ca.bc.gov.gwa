package ca.bc.gov.gwa.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/rest/apis/*", loadOnStartup = 1)
public class ApiServlet extends BaseAdminServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    final List<String> paths = splitPathInfo(request);
    switch (paths.size()) {
      case 0:
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      break;

      case 1: { // API
        final String apiName = paths.get(0);
        final String path = "/apis/" + apiName;
        this.apiService.handleDelete(request, response, path);
        this.apiService.clearCachedObject("api", apiName);
      }
      break;

      case 2:
        if ("plugins".equals(paths.get(1))) { // Plugin
          response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
      break;

      case 3:
        final String apiName = paths.get(0);
        if ("plugins".equals(paths.get(1))) {
          final String pluginName = paths.get(2);
          final String path = "/apis/" + apiName + "/plugins/" + pluginName;
          this.apiService.handleDelete(request, response, path);
          this.apiService.clearCachedObject("api", apiName);
        } else {
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
      break;

      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      break;
    }
  }

  @Override
  protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    final List<String> paths = splitPathInfo(request);
    switch (paths.size()) {
      case 0: { // API List
        this.apiService.handleList(request, response, "/apis");
      }
      break;

      case 1: { // API Get
        final String apiId = paths.get(0);
        this.apiService.apiGet(request, response, apiId);

      }
      break;

      case 2: {
        final String apiId = paths.get(0);
        if ("plugins".equals(paths.get(1))) { // Plugin list
          this.apiService.pluginList(request, response, "/plugins?api_id=" + apiId, (row) -> {
            return row.get("consumer_id") == null;
          });
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
      }
      break;
    }
  }

  @Override
  protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    final List<String> paths = splitPathInfo(request);
    switch (paths.size()) {
      case 0: // API
        this.apiService.apiAdd(request, response);
      break;

      case 1:
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      break;

      case 2: {
        final String apiName = paths.get(0);
        if ("plugins".equals(paths.get(1))) { // Plugin
          final String pluginAddPath = "/apis/" + apiName + "/plugins";
          this.apiService.handleAdd(request, response, pluginAddPath);
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
      }
      break;

      case 3:
        if ("plugins".equals(paths.get(1))) {
          response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
      break;

      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      break;
    }
  }

  @Override
  protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
    throws ServletException, IOException {
    final List<String> paths = splitPathInfo(request);
    switch (paths.size()) {
      case 0:
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
      break;

      case 1: { // API
        final String apiName = paths.get(0);
        this.apiService.apiUpdate(request, response, apiName);
      }
      break;

      case 2: {
        if ("plugins".equals(paths.get(1))) { // Plugin
          response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
      }
      break;

      case 3: {
        final String apiName = paths.get(0);
        if ("plugins".equals(paths.get(1))) { // Plugin
          final String pluginName = paths.get(2);
          final String updatePath = "/apis/" + apiName + "/plugins/" + pluginName;
          this.apiService.handleUpdatePatch(request, response, updatePath,
            ApiService.PLUGIN_FIELD_NAMES);
          this.apiService.clearCachedObject("api", apiName);
        } else {
          response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
      }
      break;
      default:
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
      break;
    }
  }
}
