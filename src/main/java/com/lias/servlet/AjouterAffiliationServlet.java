package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseAffiliations;
import com.lias.data.SampleData;
import com.lias.model.Affiliation;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterAffiliation")
public class AjouterAffiliationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Ajouter une Affiliation", "affiliations"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Historiser une Période d'Affiliation</h2>
                            <p class="text-secondary small mb-0">Gestion temporelle des arrivées, départs et retours (Cahier des Charges &sect; 4.3).</p>
                        </div>
                        <a href="listerAffiliations" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Affiliations</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterAffiliation">
                            <div class="mb-3">
                                <label class="form-label-custom">Membre Concerné <span class="text-danger">*</span></label>
                                <input type="text" name="membre" class="form-control-custom" placeholder="Ex: Dr. Youssef Tazi" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Équipe d'Attachement <span class="text-danger">*</span></label>
                                <input type="text" name="equipe" class="form-control-custom" placeholder="Ex: SDIA ou GLSIA" required>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date de Début d'Affiliation <span class="text-danger">*</span></label>
                                    <input type="date" name="dateDebut" class="form-control-custom" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date de Fin (optionnel si en cours)</label>
                                    <input type="text" name="dateFin" class="form-control-custom" placeholder="Ex: En cours ou AAAA-MM-JJ">
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerAffiliations" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-clock-history me-1"></i> Enregistrer la période
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String membre = request.getParameter("membre");
        String equipe = request.getParameter("equipe");
        String dateDebut = request.getParameter("dateDebut");
        String dateFin = request.getParameter("dateFin");
        if (dateFin == null || dateFin.isBlank()) dateFin = "En cours";

        if (membre != null && equipe != null) {
            BaseAffiliations.ajouter(new Affiliation(membre, equipe, dateDebut, dateFin));
        }

        response.sendRedirect("listerAffiliations");
    }
}
