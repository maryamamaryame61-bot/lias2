package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseDemandes;
import com.lias.data.SampleData;
import com.lias.model.DemandeAdhesion;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterDemande")
public class AjouterDemandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterDemandeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Déposer une Candidature", "demandes"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Soumettre une Demande d'Adhésion</h2>
                            <p class="text-secondary small mb-0">Candidature en ligne pour intégrer le laboratoire LIAS (Cahier des Charges &sect; 6).</p>
                        </div>
                        <a href="listerDemandes" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Liste</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterDemande">
                            <div class="mb-3">
                                <label class="form-label-custom">Nom complet &amp; Prénom <span class="text-danger">*</span></label>
                                <input type="text" name="nom" class="form-control-custom" placeholder="Ex: Salma Ouazzani" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Adresse Email <span class="text-danger">*</span></label>
                                <input type="email" name="email" class="form-control-custom" placeholder="Ex: salma@gmail.com" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Lettre de motivation &amp; Thématiques scientifiques <span class="text-danger">*</span></label>
                                <textarea name="motivation" rows="5" class="form-control-custom" placeholder="Détaillez votre parcours académique, diplômes (ex: Master Data Science) et votre projet de thèse..." required></textarea>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerDemandes" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-send me-1"></i> Soumettre la demande
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
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motivation = request.getParameter("motivation");

        if (nom != null && email != null) {
            DemandeAdhesion d = new DemandeAdhesion(nom, email, motivation);
            BaseDemandes.ajouter(d);
        }

        response.setContentType("text/html;charset=UTF-8");
        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Demande Enregistrée", "demandes"));

        html.append(String.format("""
            <div class="row justify-content-center">
                <div class="col-lg-6 text-center py-5">
                    <div class="custom-card p-5">
                        <i class="bi bi-check-circle-fill text-success display-3 mb-3"></i>
                        <h3 class="fw-bold text-dark mb-2">Demande Transmise au Directeur !</h3>
                        <p class="text-secondary small mb-4">La candidature de <strong>%s</strong> a été enregistrée avec le statut <strong>En attente</strong>. Elle sera arbitrée lors du prochain conseil de gouvernance.</p>
                        <div class="d-flex justify-content-center gap-3">
                            <a href="ajouterDemande" class="btn btn-outline-primary btn-sm"><i class="bi bi-plus-lg me-1"></i> Nouvelle demande</a>
                            <a href="listerDemandes" class="btn btn-primary-lias btn-sm"><i class="bi bi-list me-1"></i> Voir toutes les demandes</a>
                        </div>
                    </div>
                </div>
            </div>
        """, nom));

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }
}
