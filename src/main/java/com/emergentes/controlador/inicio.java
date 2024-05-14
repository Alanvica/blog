package com.emergentes.controlador;

import com.emergentes.dao.PostDAO;
import com.emergentes.dao.PostDAOImpl;
import com.emergentes.modelo.post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Villalba
 */
@WebServlet(name = "inicio", urlPatterns = {"/inicio"})
public class inicio extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PostDAO dao = new PostDAOImpl();
            int id;
            post po = new post();
            String action = (request.getParameter("action") != null) ? request.getParameter("action"): "view";
            switch (action) {
                case "add":
                    request.setAttribute("aviso", po);
                    request.getRequestDispatcher("frmpost.jsp").forward(request, response);
                    break;
                case "edit":
                    id = Integer.parseInt(request.getParameter("id"));
                    po = dao.getById(id);
                    System.out.println(po);
                    request.setAttribute("post", po);
                    request.getRequestDispatcher("frmpost.jsp").forward(request, response);
                    break;
                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/inicio");
                    break;
                case "view":
                    List<post> lista = dao.getAll();
                    request.setAttribute("post", lista);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error "+e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
