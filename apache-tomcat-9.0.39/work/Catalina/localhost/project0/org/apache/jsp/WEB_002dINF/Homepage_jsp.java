/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.39
 * Generated at: 2020-12-11 15:42:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE HTML>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<title>Revature Project 0 Home Page</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>Guitar Store</h1>\r\n");
      out.write("\t<p>Guitar Store is an API created to manage the product side of\r\n");
      out.write("\t\tyour guitar store. If your are looking for an \"all included API\" for\r\n");
      out.write("\t\tyour small shop, look no more, with this API you will get all you need\r\n");
      out.write("\t\tto start your front end development.</p>\r\n");
      out.write("\r\n");
      out.write("\t<h2>End-points</h2>\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li><strong>This is a GET request only endpoint</strong></li>\r\n");
      out.write("\t\t<li>This is the starting \"home endpoint\" which shows all the\r\n");
      out.write("\t\t\tendpoints implemented by the API. use that end point for a fast\r\n");
      out.write("\t\t\treference of the endpoints</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/userlogin</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li><strong>This is a POST request only endpoint</strong></li>\r\n");
      out.write("\t\t<li>Use this endpoint to log in users.</li>\r\n");
      out.write("\t\t<li>Need to provide the following key value pairs in the body of\r\n");
      out.write("\t\t\tyour request:</li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li><code>username</code></li>\r\n");
      out.write("\t\t\t<li><code>password</code></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<li>Body example</li>\r\n");
      out.write("\t\t<code> { \"username\":\"john.doe\", \"password\":\"password\" } </code>\r\n");
      out.write("\t\t<li>password field is automatically converted to an MD5\r\n");
      out.write("\t\t\tencryption format to provide a piece of mind in security.</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/userlogout</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li><strong>This is a POST request only endpoint</strong></li>\r\n");
      out.write("\t\t<li>Use this endpoint to log out users</li>\r\n");
      out.write("\t\t<li>No request-body necessary, if user was not logged in, the\r\n");
      out.write("\t\t\tserver will just re-new the session.</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr>\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/user</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li><strong>GET REQUEST</strong></li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>Returns all active users.</li>\r\n");
      out.write("\t\t\t<li>User must be logged in as Administrator in order to get\r\n");
      out.write("\t\t\t\taccess to this endpoint.</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t\t<li><strong>POST REQUEST</strong></li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>Creates a new Employee user</li>\r\n");
      out.write("\t\t\t<li>User must be logged in as Administrator in order to get\r\n");
      out.write("\t\t\t\taccess to this endpoint.</li>\r\n");
      out.write("\t\t\t<li>Need the following key value pairs in the body of your\r\n");
      out.write("\t\t\t\trequest:</li>\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li><code>uniqueID</code></li>\r\n");
      out.write("\t\t\t\t<li><code>username</code></li>\r\n");
      out.write("\t\t\t\t<li><code>username</code></li>\r\n");
      out.write("\t\t\t\t<li><code>email</code></li>\r\n");
      out.write("\t\t\t\t<li><code>password</code></li>\r\n");
      out.write("\t\t\t\t<li><code>userType_UID</code></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t\t\t<li>Body example</li>\r\n");
      out.write("\t\t\t<code> { \"uniqueID\": 0, \"username\":\"alexis.dominguez\",\r\n");
      out.write("\t\t\t\t\"email\":\"alexis.dominguez@yahoo.comm\" \"password\":\"alexisdominguez\",\r\n");
      out.write("\t\t\t\t\"userType_UID\": 2 } </code>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/user/*</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li><strong>GET REQUEST</strong></li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>Returns user by id</li>\r\n");
      out.write("\t\t\t<li>User must be logged in as Administrator in order to get\r\n");
      out.write("\t\t\t\taccess to this endpoint.</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<li><strong>PUT REQUEST</strong></li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>Updates the current user</li>\r\n");
      out.write("\t\t\t<li>User must be logged in as Administrator in order to get\r\n");
      out.write("\t\t\t\taccess to this endpoint.</li>\r\n");
      out.write("\t\t\t<li>Need the following key value pairs in the body of your\r\n");
      out.write("\t\t\t\trequest:</li>\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li><code>uniqueID</code></li>\r\n");
      out.write("\t\t\t\t<li><code>username</code></li>\r\n");
      out.write("\t\t\t\t<li><code>email</code></li>\r\n");
      out.write("\t\t\t\t<li><code>password</code></li>\r\n");
      out.write("\t\t\t\t<li><code>userType_UID</code></li>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("\t\t\t<li>Body example:</li>\r\n");
      out.write("\t\t\t<code> { \"uniqueID\": 0, \"username\":\"alexis.dominguez\",\r\n");
      out.write("\t\t\t\t\"email\":\"alexis.dominguez@yahoo.comm\" \"password\":\"alexisdominguez\",\r\n");
      out.write("\t\t\t\t\"userType_UID\": 2 } </code>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t\t<li><strong>DELETE REQUEST</strong></li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li>Deletes an user by id</li>\r\n");
      out.write("\t\t\t<li>User must be logged in as Administrator in order to get\r\n");
      out.write("\t\t\t\taccess to this endpoint.</li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/sign/up</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Use this endpoint to create customers. These users have no\r\n");
      out.write("\t\t\taccess to any administrative endpoint.</li>\r\n");
      out.write("\t\t<li>This is a POST request only endpoint</li>\r\n");
      out.write("\t\t<li>Need the following key value pairs in the body of your\r\n");
      out.write("\t\t\trequest:</li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li><code>username</code></li>\r\n");
      out.write("\t\t\t<li><code>email</code></li>\r\n");
      out.write("\t\t\t<li><code>password</code></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<li>Body example</li>\r\n");
      out.write("\t\t<code> { \"username\":\"alexis.dominguez\",\r\n");
      out.write("\t\t\t\"email\":\"alexis.dominguez@yahoo.comm\" \"password\":\"alexisdominguez\", }\r\n");
      out.write("\t\t</code>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/sign/out</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Use this enpoint to allow customers to opt-out of the system.</li>\r\n");
      out.write("\t\t<li>No request-body necessary.</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/sign/recover</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Use this enpoint to 'recover' access to the system for a\r\n");
      out.write("\t\t\tpreviously opted-out customer.</li>\r\n");
      out.write("\t\t<li>Need the following key value pairs in the body of your\r\n");
      out.write("\t\t\trequest:</li>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li><code>username</code></li>\r\n");
      out.write("\t\t\t<li><code>password</code></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t\t<li>Body example</li>\r\n");
      out.write("\t\t<code> { \"email\":\"alexis.dominguez@yahoo.comm\"\r\n");
      out.write("\t\t\t\"password\":\"alexisdominguez\", } </code>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/product</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Retrieves all products from database.</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/product/*</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Regrieves on specific product by id</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/product/brand/*</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Retrieves products with matching Brand Id</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<strong><code>/product/department/*</code></strong>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Retrieves products with matching Department Id</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\r\n");
      out.write("\t<h2>Actually working on</h2>\r\n");
      out.write("\t<p>For our next version we've planned to fully implement the\r\n");
      out.write("\t\tfollowing</p>\r\n");
      out.write("\t<ul>\r\n");
      out.write("\t\t<li>Review Section with average</li>\r\n");
      out.write("\t\t<li>CRUD functionalities for Brand, Category, Condition,\r\n");
      out.write("\t\t\tDepartment, Features, PremiumGear, Specifications and Style</li>\r\n");
      out.write("\t</ul>\r\n");
      out.write("\r\n");
      out.write("\t<hr />\r\n");
      out.write("\t<h2>Contributors</h2>\r\n");
      out.write("\t<h4>Revature 2011 Java/React team</h4>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}