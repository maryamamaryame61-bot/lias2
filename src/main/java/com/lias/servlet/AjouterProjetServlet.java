package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseProjets;
import com.lias.data.SampleData;
import com.lias.model.Projet;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterProjet")
public class AjouterProjetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterProjetServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Ajouter un Projet", "projets"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Enregistrer un Projet de Recherche</h2>
                            <p class="text-secondary small mb-0">Programmes nationaux et internationaux de recherche.</p>
                        </div>
                        <a href="listerProjets" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Projets</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterProjet">
                            <div class="mb-3">
                                <label class="form-label-custom">Titre du Projet Scientifique <span class="text-danger">*</span></label>
                                <input type="text" name="titre" class="form-control-custom" placeholder="Ex: Plateforme IA &amp; Big Data pour la Santé" required>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Responsable / Porteur du Projet <span class="text-danger">*</span></label>
                                    <input type="text" name="responsable" class="form-control-custom" placeholder="Ex: Pr. Mohamed Alami" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Budget / Financement Alloué <span class="text-danger">*</span></label>
                                    <input type="text" name="budget" class="form-control-custom" placeholder="Ex: 350 000 MAD" required>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerProjets" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-kanban me-1"></i> Enregistrer le projet
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String titre = request.getParameter("titre");
        String responsable = request.getParameter("responsable");
        String budget = request.getParameter("budget");

        if (titre != null && responsable != null) {
            BaseProjets.ajouter(new Projet(titre, responsable, budget));
        }

        response.sendRedirect("listerProjets");
    }
}
