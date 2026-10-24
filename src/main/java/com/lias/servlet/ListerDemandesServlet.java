package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseDemandes;
import com.lias.data.SampleData;
import com.lias.model.DemandeAdhesion;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerDemandes")
public class ListerDemandesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerDemandesServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<DemandeAdhesion> demandes = BaseDemandes.getDemandes();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Gestion des Demandes d'Adhésion", "demandes"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Demandes d'Adhésion au Laboratoire LIAS</h2>
                    <p class="text-secondary small mb-0">Validation des candidatures de doctorants et chercheurs (Cahier des Charges &sect; 6).</p>
                </div>
                <a href="ajouterDemande" class="btn btn-primary-lias btn-sm">
                    <i class="bi bi-plus-lg me-1"></i> Soumettre une candidature
                </a>
            </div>

            <div class="alert alert-info border-0 shadow-sm d-flex align-items-center gap-3 mb-4">
                <i class="bi bi-shield-lock-fill fs-3 text-primary"></i>
                <div class="small">
                    <strong>Règle de Gouvernance (&sect;5.2) :</strong> Les demandes d'adhésion sont arbitrées et validées <em>exclusivement par le Directeur du laboratoire en mandat actif</em>. Une fois acceptée, le candidat intègre le système officiel du LIAS.
                </div>
            </div>

            <div class="custom-card">
                <div class="custom-card-header">
                    <div class="position-relative flex-grow-1 me-3" style="max-width: 400px;">
                        <i class="bi bi-search position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                        <input type="text" class="form-control form-control-sm ps-5 table-search-input" data-table="demandesTable" placeholder="Recherche par nom, email, statut...">
                    </div>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(demandes.size()).append(" candidatures au total</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0" id="demandesTable">
                        <thead>
                            <tr>
                                <th style="width: 25%;">Candidat</th>
                                <th style="width: 45%;">Motivation & Projet Scientifique</th>
                                <th style="width: 15%;">Statut Décisionnel</th>
                                <th style="width: 15%;" class="text-end">Décision Direction</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (demandes.isEmpty()) {
            html.append("<tr><td colspan='4' class='text-center text-muted py-5'>Aucune demande d'adhésion enregistrée.</td></tr>");
        } else {
            for (int i = 0; i < demandes.size(); i++) {
                DemandeAdhesion d = demandes.get(i);
                String statut = d.getStatut() != null ? d.getStatut() : "En attente";
                String badgeClass = "badge-attente";

                if ("Acceptée".equalsIgnoreCase(statut) || "Acceptee".equalsIgnoreCase(statut)) {
                    badgeClass = "badge-accepte";
                } else if ("Refusée".equalsIgnoreCase(statut) || "Refusee".equalsIgnoreCase(statut)) {
                    badgeClass = "badge-refuse";
                }

                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark">%s</strong><br>
                            <a href="mailto:%s" class="small text-primary text-decoration-none"><i class="bi bi-envelope me-1"></i>%s</a>
                        </td>
                        <td>
                            <div class="small text-secondary">%s</div>
                        </td>
                        <td>
                            <span class="badge-status %s">%s</span>
                        </td>
                        <td class="text-end">
                            <div class="btn-group btn-group-sm">
                                <a href="validerDemande?id=%d&action=accept" class="btn btn-success" title="Valider & Accepter la candidature">
                                    <i class="bi bi-check-lg me-1"></i> Accepter
                                </a>
                                <a href="validerDemande?id=%d&action=refuse" class="btn btn-outline-danger" title="Refuser">
                                    <i class="bi bi-x-lg me-1"></i> Refuser
                                </a>
                            </div>
                        </td>
                    </tr>
                """, d.getNom(), d.getEmail(), d.getEmail(), d.getMotivation(), badgeClass, statut, i, i));
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
