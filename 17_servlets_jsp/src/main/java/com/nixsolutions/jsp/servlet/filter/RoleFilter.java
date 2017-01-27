package com.nixsolutions.jsp.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nixsolutions.jdbc.bean.PersonType;
import com.nixsolutions.jdbc.bean.Role;

@WebFilter(urlPatterns = { "/admin", "/admin/*" })
public class RoleFilter implements Filter {

  @Override
  public void destroy() {
    // TODO Auto-generated method stub

  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpReq = (HttpServletRequest) req;

    HttpSession ses = httpReq.getSession(false);
    String role = ses == null ? null : (String) ses.getAttribute("role");

    if (role == null || !"Dean".equals(role)) {
      req.getRequestDispatcher("/WEB-INF/jsp/permission_denied.jsp").forward(req, resp);
      return;
    }

    if (chain != null) {
      chain.doFilter(req, resp);
    }
  }

  @Override
  public void init(FilterConfig arg0) throws ServletException {
    // TODO Auto-generated method stub

  }

}
