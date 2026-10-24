package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseEquipes;
import com.lias.data.SampleData;
import com.lias.model.Equipe;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerEquipes")
public class ListerEquipesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerEquipesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Equipe> equipes = BaseEquipes.getEquipes();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Équipes de Recherche", "equipes"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Équipes de Recherche &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Structuration thématique des axes de recherche du laboratoire.</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterEquipe" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Créer une équipe
                    </a>
                </div>
            </div>

            <div class="row g-4">
        """);

        for (Equipe eq : equipes) {
            html.append(String.format("""
                <div class="col-lg-4 col-md-6">
                    <div class="feature-card">
                        <div class="feature-icon">
                            <i class="bi bi-diagram-3-fill"></i>
                        </div>
                        <h5 class="fw-bold text-dark mb-2">%s</h5>
                        <p class="text-secondary small mb-3 flex-grow-1">%s</p>
                        <div class="pt-3 border-top d-flex justify-content-between align-items-center">
                            <small class="text-muted"><i class="bi bi-person-fill text-primary me-1"></i> Resp : <strong>%s</strong></small>
                            <span class="badge bg-success bg-opacity-10 text-success">Active</span>
                        </div>
                    </div>
                </div>
            """, eq.getNom(), eq.getDomaine(), eq.getResponsable()));
        }

        html.append("""
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
