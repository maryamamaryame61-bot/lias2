package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lias.data.BaseEvenements;
import com.lias.data.ConnexionSQLite;
import com.lias.data.InitialisationBD;
import com.lias.data.SampleData;
import com.lias.model.Evenement;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterEvenement")
public class AjouterEvenementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterEvenementServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Planifier un Événement", "evenements"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Planifier une Manifestation Scientifique</h2>
                            <p class="text-secondary small mb-0">Conférences, séminaires doctoraux et workshops (Cahier des Charges &sect; 9).</p>
                        </div>
                        <a href="listerEvenements" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Liste</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterEvenement">
                            <div class="mb-3">
                                <label class="form-label-custom">Intitulé de l'Événement <span class="text-danger">*</span></label>
                                <input type="text" name="titre" class="form-control-custom" placeholder="Ex: Conférence Internationale Big Data 2026" required>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Typologie de la Manifestation <span class="text-danger">*</span></label>
                                    <select name="type" class="form-select-custom">
                                        <option value="Conférence">Conférence Internationale / Nationale</option>
                                        <option value="Séminaire">Séminaire Doctoral d'Équipe</option>
                                        <option value="Workshop">Workshop Technique &amp; Tutoriel</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date Programmée <span class="text-danger">*</span></label>
                                    <input type="date" name="date" class="form-control-custom" required>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerEvenements" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-calendar-check me-1"></i> Enregistrer l'événement
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InitialisationBD.creerTables();
        response.setContentType("text/html;charset=UTF-8");

        String titre = request.getParameter("titre");
        String type = request.getParameter("type");
        String date = request.getParameter("date");

        if (titre != null && date != null) {
            try {
                Connection con = ConnexionSQLite.getConnection();
                if (con != null) {
                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO evenement(titre, type, date, lieu) VALUES (?, ?, ?, ?)"
                    );
                    ps.setString(1, titre);
                    ps.setString(2, type);
                    ps.setString(3, date);
                    ps.setString(4, "Faculté des Sciences Ben M'Sik");
                    ps.executeUpdate();
                    ps.close();
                    con.close();
                }
            } catch (Exception ignored) {}

            BaseEvenements.ajouter(new Evenement(titre, type, date));
        }

        response.sendRedirect("listerEvenements");
    }
}
