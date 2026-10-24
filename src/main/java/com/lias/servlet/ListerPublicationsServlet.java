package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.lias.data.BasePublications;
import com.lias.data.ConnexionSQLite;
import com.lias.data.SampleData;
import com.lias.model.Publication;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerPublications")
public class ListerPublicationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Publication> liste = new ArrayList<>();

        // Lire depuis SQLite
        try {
            Connection con = ConnexionSQLite.getConnection();
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM publication");
                while (rs.next()) {
                    String dateStr = rs.getString("date");
                    int annee = 2025;
                    try {
                        annee = Integer.parseInt(dateStr.trim());
                    } catch (Exception ignored) {}

                    liste.add(new Publication(
                        rs.getString("titre"),
                        rs.getString("auteur"),
                        annee,
                        "LIAS"
                    ));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception ignored) {}

        // Fallback si la table SQLite est vide
        if (liste.isEmpty()) {
            liste.addAll(BasePublications.getPublications());
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Gestion des Publications", "publications"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Production Scientifique &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Indexation, classement par auteur, année et équipe (Cahier des Charges &sect; 12).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterPublication" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Ajouter une publication
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <div class="position-relative flex-grow-1 me-3" style="max-width: 400px;">
                        <i class="bi bi-search position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                        <input type="text" class="form-control form-control-sm ps-5 table-search-input" data-table="pubTable" placeholder="Rechercher par titre, auteur, équipe, année...">
                    </div>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(liste.size()).append(" publications indexées</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle" id="pubTable">
                        <thead>
                            <tr>
                                <th style="width: 45%;">Titre de l'Article / Revue</th>
                                <th style="width: 25%;">Auteurs</th>
                                <th>Année</th>
                                <th>Équipe</th>
                                <th class="text-end">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (liste.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucune publication enregistrée.</td></tr>");
        } else {
            for (Publication p : liste) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <div class="fw-bold text-dark"><i class="bi bi-file-earmark-text text-primary me-2"></i>%s</div>
                            <small class="text-muted">Laboratoire LIAS &bull; FSBM Casablanca</small>
                        </td>
                        <td>
                            <span class="text-secondary"><i class="bi bi-person me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border">%d</span>
                        </td>
                        <td>
                            <span class="badge bg-info bg-opacity-10 text-info">%s</span>
                        </td>
                        <td class="text-end">
                            <button class="btn btn-sm btn-outline-primary" onclick="alert('Téléchargement du document');">
                                <i class="bi bi-download me-1"></i> PDF
                            </button>
                        </td>
                    </tr>
                """, p.getTitre(), p.getAuteur(), p.getAnnee(), p.getEquipe()));
            }
        }

        html.append("""
                        </tbody>
                    </table>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
