package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.lias.data.BasePublications;
import com.lias.data.ConnexionSQLite;
import com.lias.data.InitialisationBD;
import com.lias.data.SampleData;
import com.lias.model.Publication;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterPublication")
public class AjouterPublicationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Ajouter une Publication", "publications"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Enregistrer une Publication</h2>
                            <p class="text-secondary small mb-0">Production scientifique des membres et doctorants (Cahier des Charges &sect; 12).</p>
                        </div>
                        <a href="listerPublications" class="btn btn-outline-secondary btn-sm">
                            <i class="bi bi-arrow-left me-1"></i> Retour aux publications
                        </a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterPublication">
                            <div class="mb-3">
                                <label class="form-label-custom">Titre de l'Article ou Ouvrage <span class="text-danger">*</span></label>
                                <input type="text" name="titre" class="form-control-custom" placeholder="Ex: Deep Learning for Big Data Analytics in Smart Cities" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Auteur(s) <span class="text-danger">*</span></label>
                                <input type="text" name="auteur" class="form-control-custom" placeholder="Ex: Pr. Mohamed Alami, Amina Mansouri" required>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Année de Publication</label>
                                    <input type="number" name="annee" class="form-control-custom" value="2026" min="2000" max="2030">
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Équipe de Recherche de Rattachement</label>
                                    <select name="equipe" class="form-select-custom">
                                        <option value="SDIA">Science des Données &amp; IA (SDIA)</option>
                                        <option value="GLSIA">Génie Logiciel &amp; Cloud (GLSIA)</option>
                                        <option value="SEIS">Systèmes Embarqués &amp; IoT (SEIS)</option>
                                    </select>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerPublications" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-check2-circle me-1"></i> Indexer la publication
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InitialisationBD.creerTables();
        response.setContentType("text/html;charset=UTF-8");

        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String anneeStr = request.getParameter("annee");
        String equipe = request.getParameter("equipe");

        int annee = 2026;
        try {
            if (anneeStr != null) annee = Integer.parseInt(anneeStr.trim());
        } catch (Exception ignored) {}

        if (equipe == null || equipe.isBlank()) equipe = "SDIA";

        if (titre != null && auteur != null) {
            // Sauvegarde SQLite
            try {
                Connection con = ConnexionSQLite.getConnection();
                if (con != null) {
                    PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO publication(titre, auteur, date) VALUES (?, ?, ?)"
                    );
                    ps.setString(1, titre);
                    ps.setString(2, auteur);
                    ps.setString(3, String.valueOf(annee));
                    ps.executeUpdate();
                    ps.close();
                    con.close();
                }
            } catch (Exception ignored) {}

            // Sauvegarde dans la liste mémoire
            BasePublications.ajouter(new Publication(titre, auteur, annee, equipe));
        }

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Publication Ajoutée", "publications"));

        html.append(String.format("""
            <div class="row justify-content-center">
                <div class="col-lg-6 text-center py-5">
                    <div class="custom-card p-5">
                        <i class="bi bi-check-circle-fill text-success display-3 mb-3"></i>
                        <h3 class="fw-bold text-dark mb-2">Publication Indexée avec Succès !</h3>
                        <p class="text-secondary small mb-4">L'article <strong>%s</strong> a été ajouté au catalogue scientifique du LIAS.</p>
                        <div class="d-flex justify-content-center gap-3">
                            <a href="ajouterPublication" class="btn btn-outline-primary btn-sm"><i class="bi bi-plus-lg me-1"></i> Ajouter une autre</a>
                            <a href="listerPublications" class="btn btn-primary-lias btn-sm"><i class="bi bi-list me-1"></i> Voir la liste</a>
                        </div>
                    </div>
                </div>
            </div>
        """, titre));

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }
}
