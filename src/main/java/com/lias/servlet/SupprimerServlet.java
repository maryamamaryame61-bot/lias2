package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lias.data.BaseMembres;
import com.lias.data.ConnexionSQLite;
import com.lias.model.Membre;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/supprimer")
public class SupprimerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SupprimerServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        if (email != null && !email.isBlank()) {
            supprimerMembre(email);
            response.sendRedirect("lister");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Supprimer un Membre", "membres"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-6">
                    <div class="custom-card p-4 p-md-5">
                        <h4 class="fw-bold text-danger mb-3"><i class="bi bi-trash me-2"></i> Supprimer un Membre</h4>
                        <form method="post" action="supprimer">
                            <div class="mb-4">
                                <label class="form-label-custom">Email du membre à supprimer</label>
                                <input type="email" name="email" class="form-control-custom" placeholder="Ex: email@univ-fsbm.ma" required>
                            </div>
                            <div class="d-flex justify-content-between align-items-center">
                                <a href="lister" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-danger px-4">Confirmer la suppression</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        supprimerMembre(email);
        response.sendRedirect("lister");
    }

    private void supprimerMembre(String email) {
        if (email == null) return;

        try {
            Connection con = ConnexionSQLite.getConnection();
            if (con != null) {
                PreparedStatement ps = con.prepareStatement("DELETE FROM membre WHERE email = ?");
                ps.setString(1, email);
                ps.executeUpdate();
                ps.close();
                con.close();
            }
        } catch (Exception ignored) {}

        BaseMembres.getMembres().removeIf(m -> m.getEmail().equalsIgnoreCase(email));
    }
}
