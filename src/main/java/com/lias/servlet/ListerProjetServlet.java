package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseProjets;
import com.lias.data.SampleData;
import com.lias.model.Projet;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerProjets")
public class ListerProjetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerProjetServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Projet> projets = BaseProjets.getProjets();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Projets de Recherche", "projets"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Projets de Recherche &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Programmes nationaux, collaborations industrielles et budgets alloués.</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterProjet" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Nouveau Projet
                    </a>
                </div>
            </div>

            <div class="row g-4">
        """);

        if (projets.isEmpty()) {
            html.append("<div class='col-12 text-center py-5 text-muted'>Aucun projet enregistré.</div>");
        } else {
            for (Projet p : projets) {
                html.append(String.format("""
                    <div class="col-lg-6">
                        <div class="custom-card p-4 h-100 d-flex flex-column">
                            <div class="d-flex justify-content-between align-items-start mb-3">
                                <h5 class="fw-bold text-dark mb-0">%s</h5>
                                <span class="badge bg-success bg-opacity-10 text-success border border-success border-opacity-25">En cours</span>
                            </div>
                            <p class="text-secondary small mb-3 flex-grow-1">Projet de recherche appliquée mené au sein du laboratoire LIAS à la FSBM.</p>
                            <div class="p-3 bg-light rounded-3 border mb-3">
                                <div class="d-flex justify-content-between mb-1">
                                    <small class="text-muted">Responsable Scientifique :</small>
                                    <strong class="text-dark">%s</strong>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <small class="text-muted">Financement / Budget :</small>
                                    <span class="badge bg-primary">%s</span>
                                </div>
                            </div>
                        </div>
                    </div>
                """, p.getTitre(), p.getResponsable(), p.getBudget()));
            }
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
