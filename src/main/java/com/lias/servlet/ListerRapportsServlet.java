package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.*;
import com.lias.model.*;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerRapports")
public class ListerRapportsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<RapportAnnuel> rapports = BaseRapports.getRapports();
        int nbMembres = BaseMembres.getMembres().size();
        int nbPublications = BasePublications.getPublications().size();
        int nbEvenements = BaseEvenements.getEvenements().size();
        int nbMateriels = BaseMateriels.getMateriels().size();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Rapport d'Activité Annuel", "rapports"));

        html.append(String.format("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Rapport d'Activité Annuel &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Production annuelle obligatoire conditionnant l'octroi de la dotation budgétaire (Cahier des Charges &sect; 11).</p>
                </div>
                <div class="d-flex gap-2">
                    <button onclick="window.print()" class="btn btn-outline-dark btn-sm">
                        <i class="bi bi-printer me-1"></i> Imprimer / Exporter PDF
                    </button>
                    <a href="ajouterRapport" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Nouvelle Synthèse
                    </a>
                </div>
            </div>

            <!-- RAPPORT OFFICIEL IMPRIMABLE -->
            <div class="custom-card p-4 p-md-5 mb-4 border shadow-sm">
                <div class="text-center pb-4 mb-4 border-bottom">
                    <div class="d-flex justify-content-center align-items-center gap-3 mb-3">
                        <img src="images/lias-logo.svg" alt="LIAS Logo" height="55">
                    </div>
                    <h4 class="fw-bold text-dark text-uppercase mb-1">Université Hassan II de Casablanca &bull; FSBM</h4>
                    <h5 class="text-primary fw-bold mb-1">Rapport d'Activité Scientifique &amp; Bilan Annuel</h5>
                    <div class="badge bg-primary px-3 py-2 fs-6">Année Universitaire 2025-2026</div>
                </div>

                <!-- INDICATEURS CLÉS DU RAPPORT -->
                <h5 class="fw-bold text-dark mb-3"><i class="bi bi-check2-circle text-success me-2"></i> 1. Synthèse Automatique des Indicateurs Clés</h5>
                <div class="row g-3 mb-4">
                    <div class="col-sm-3 col-6">
                        <div class="border rounded-3 p-3 text-center bg-light">
                            <h3 class="fw-bold text-primary mb-0">%d</h3>
                            <small class="text-muted text-uppercase fw-semibold">Membres Actifs</small>
                        </div>
                    </div>
                    <div class="col-sm-3 col-6">
                        <div class="border rounded-3 p-3 text-center bg-light">
                            <h3 class="fw-bold text-success mb-0">%d</h3>
                            <small class="text-muted text-uppercase fw-semibold">Publications Indexées</small>
                        </div>
                    </div>
                    <div class="col-sm-3 col-6">
                        <div class="border rounded-3 p-3 text-center bg-light">
                            <h3 class="fw-bold text-warning mb-0">%d</h3>
                            <small class="text-muted text-uppercase fw-semibold">Événements & Congrès</small>
                        </div>
                    </div>
                    <div class="col-sm-3 col-6">
                        <div class="border rounded-3 p-3 text-center bg-light">
                            <h3 class="fw-bold text-info mb-0">%d</h3>
                            <small class="text-muted text-uppercase fw-semibold">Équipements Reçus</small>
                        </div>
                    </div>
                </div>

                <!-- BILAN DES ÉDITIONS PRÉCÉDENTES -->
                <h5 class="fw-bold text-dark mb-3"><i class="bi bi-journal-bookmark text-primary me-2"></i> 2. Historique des Rapports Annuels Enregistrés</h5>
                <div class="list-group list-group-flush mb-4">
        """, nbMembres, nbPublications, nbEvenements, nbMateriels));

        if (rapports.isEmpty()) {
            html.append("<div class='text-muted small py-3'>Aucun rapport annuel archivé.</div>");
        } else {
            for (RapportAnnuel r : rapports) {
                html.append(String.format("""
                    <div class="list-group-item px-0 py-3">
                        <div class="d-flex justify-content-between align-items-center mb-2">
                            <span class="badge bg-primary fs-6 px-3 py-1">Édition Annuelle %d</span>
                            <span class="badge bg-success bg-opacity-10 text-success border">Rapport Validé par le Directeur</span>
                        </div>
                        <p class="text-secondary small mb-0">%s</p>
                    </div>
                """, r.getAnnee(), r.getResume()));
            }
        }

        html.append("""
                </div>

                <div class="pt-4 border-top text-muted small d-flex justify-content-between align-items-center">
                    <div>
                        <strong>Signé par :</strong> Direction du Laboratoire LIAS<br>
                        <em>Pr. Hassan El Idrissi &bull; Directeur en Mandat Actif</em>
                    </div>
                    <div class="text-end">
                        <span class="badge bg-light text-dark border p-2">Document certifié conforme pour le budget annuel</span>
                    </div>
                </div>
            </div>
        """);

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }
}
