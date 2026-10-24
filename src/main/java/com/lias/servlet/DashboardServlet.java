package com.lias.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.lias.data.BaseDemandes;
import com.lias.data.BaseDocuments;
import com.lias.data.BaseEquipes;
import com.lias.data.BaseEvenements;
import com.lias.data.BaseMateriels;
import com.lias.data.BaseMembres;
import com.lias.data.BaseNotifications;
import com.lias.data.BasePublications;
import com.lias.data.ConnexionSQLite;
import com.lias.data.SampleData;
import com.lias.model.DemandeAdhesion;
import com.lias.model.Membre;
import com.lias.model.Publication;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private int countTable(Connection con, String tableName) {
        int total = 0;
        try {
            if (con != null) {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + tableName);
                if (rs.next()) {
                    total = rs.getInt(1);
                }
                rs.close();
                st.close();
            }
        } catch (Exception ignored) {}
        return total;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        int nbMembres = BaseMembres.getMembres().size();
        int nbDocuments = BaseDocuments.getDocuments().size();
        int nbPublications = BasePublications.getPublications().size();
        int nbEvenements = BaseEvenements.getEvenements().size();
        int nbNotifications = BaseNotifications.getNotifications().size();
        int nbDemandes = BaseDemandes.getDemandes().size();
        int nbMateriels = BaseMateriels.getMateriels().size();
        int nbEquipes = BaseEquipes.getEquipes().size();

        try {
            Connection con = ConnexionSQLite.getConnection();
            if (con != null) {
                int dbMembres = countTable(con, "membre");
                if (dbMembres > nbMembres) nbMembres = dbMembres;

                int dbDocs = countTable(con, "document");
                if (dbDocs > nbDocuments) nbDocuments = dbDocs;

                int dbEvents = countTable(con, "evenement");
                if (dbEvents > nbEvenements) nbEvenements = dbEvents;

                int dbPubs = countTable(con, "publication");
                if (dbPubs > nbPublications) nbPublications = dbPubs;

                con.close();
            }
        } catch (Exception ignored) {}

        // Répartition des membres pour le graphique
        int nbPermanents = 0;
        int nbAssocies = 0;
        int nbDoctorants = 0;
        int nbRetraites = 0;
        for (Membre m : BaseMembres.getMembres()) {
            String s = m.getStatut() != null ? m.getStatut().toLowerCase() : "";
            if (s.contains("permanent")) nbPermanents++;
            else if (s.contains("associe") || s.contains("associé")) nbAssocies++;
            else if (s.contains("doctorant")) nbDoctorants++;
            else if (s.contains("retrait")) nbRetraites++;
            else nbPermanents++;
        }

        ArrayList<DemandeAdhesion> demandes = BaseDemandes.getDemandes();
        ArrayList<Publication> dernieresPublis = BasePublications.getPublications();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Tableau de Bord", "dashboard"));

        // EN-TÊTE DU DASHBOARD
        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Tableau de Bord &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Vue synthétique et pilotage des activités scientifiques, des membres et des moyens du laboratoire.</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="listerRapports" class="btn btn-outline-primary btn-sm">
                        <i class="bi bi-file-earmark-bar-graph me-1"></i> Rapport Annuel
                    </a>
                    <a href="ajouter" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-person-plus-fill me-1"></i> Nouveau Membre
                    </a>
                </div>
            </div>
        """);

        // STATS CARDS (KPIS)
        html.append(String.format("""
            <div class="row g-3 mb-4">
                <div class="col-xl-3 col-sm-6">
                    <div class="stat-card">
                        <div class="icon-box bg-primary bg-opacity-10 text-primary"><i class="bi bi-people-fill"></i></div>
                        <div class="stat-number">%d</div>
                        <div class="stat-label">Membres du Labo</div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6">
                    <div class="stat-card">
                        <div class="icon-box bg-success bg-opacity-10 text-success"><i class="bi bi-journal-text"></i></div>
                        <div class="stat-number">%d</div>
                        <div class="stat-label">Publications</div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6">
                    <div class="stat-card">
                        <div class="icon-box bg-warning bg-opacity-10 text-warning"><i class="bi bi-calendar-event"></i></div>
                        <div class="stat-number">%d</div>
                        <div class="stat-label">Événements & Séminaires</div>
                    </div>
                </div>
                <div class="col-xl-3 col-sm-6">
                    <div class="stat-card">
                        <div class="icon-box bg-info bg-opacity-10 text-info"><i class="bi bi-person-check-fill"></i></div>
                        <div class="stat-number">%d</div>
                        <div class="stat-label">Demandes d'Adhésion</div>
                    </div>
                </div>
            </div>
        """, nbMembres, nbPublications, nbEvenements, nbDemandes));

        // GRAPHIQUES VISUELS CHART.JS
        html.append(String.format("""
            <div class="row g-4 mb-4">
                <div class="col-lg-6">
                    <div class="custom-card p-4 h-100">
                        <h5 class="fw-bold text-dark mb-3"><i class="bi bi-pie-chart text-primary me-2"></i> Répartition des Membres par Statut</h5>
                        <div style="height: 250px; position: relative;">
                            <canvas id="chartStatuts"></canvas>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="custom-card p-4 h-100">
                        <h5 class="fw-bold text-dark mb-3"><i class="bi bi-bar-chart text-success me-2"></i> Production Scientifique & Activités</h5>
                        <div style="height: 250px; position: relative;">
                            <canvas id="chartActivites"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        """));

        // SECTION DEMANDES D'ADHÉSION EN ATTENTE & DERNIÈRES PUBLICATIONS
        html.append("""
            <div class="row g-4">
                <div class="col-lg-7">
                    <div class="custom-card">
                        <div class="custom-card-header">
                            <h5><i class="bi bi-person-check text-warning me-2"></i> Demandes d'adhésion récentes (Cahier des Charges &sect; 6)</h5>
                            <a href="listerDemandes" class="btn btn-sm btn-outline-secondary">Gérer tout</a>
                        </div>
                        <div class="table-responsive">
                            <table class="table table-custom align-middle mb-0">
                                <thead>
                                    <tr>
                                        <th>Candidat</th>
                                        <th>Motivation & Profil</th>
                                        <th>Statut</th>
                                        <th>Action Direction</th>
                                    </tr>
                                </thead>
                                <tbody>
        """);

        if (demandes.isEmpty()) {
            html.append("<tr><td colspan='4' class='text-center text-muted py-3'>Aucune demande reçue pour le moment.</td></tr>");
        } else {
            for (int i = 0; i < Math.min(3, demandes.size()); i++) {
                DemandeAdhesion d = demandes.get(i);
                String badgeClass = "badge-attente";
                if ("Acceptée".equalsIgnoreCase(d.getStatut())) badgeClass = "badge-accepte";
                else if ("Refusée".equalsIgnoreCase(d.getStatut())) badgeClass = "badge-refuse";

                html.append(String.format("""
                    <tr>
                        <td>
                            <strong>%s</strong><br>
                            <small class="text-muted">%s</small>
                        </td>
                        <td><small class="text-secondary">%s</small></td>
                        <td><span class="badge-status %s">%s</span></td>
                        <td>
                            <div class="btn-group btn-group-sm">
                                <a href="validerDemande?id=%d&action=accept" class="btn btn-success" title="Accepter la candidature"><i class="bi bi-check-lg"></i></a>
                                <a href="validerDemande?id=%d&action=refuse" class="btn btn-outline-danger" title="Refuser"><i class="bi bi-x-lg"></i></a>
                            </div>
                        </td>
                    </tr>
                """, d.getNom(), d.getEmail(), d.getMotivation(), badgeClass, d.getStatut(), i, i));
            }
        }

        html.append("""
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-lg-5">
                    <div class="custom-card">
                        <div class="custom-card-header">
                            <h5><i class="bi bi-journal-text text-primary me-2"></i> Dernières Publications</h5>
                            <a href="listerPublications" class="btn btn-sm btn-outline-secondary">Tout voir</a>
                        </div>
                        <div class="list-group list-group-flush">
        """);

        if (dernieresPublis.isEmpty()) {
            html.append("<div class='p-3 text-center text-muted'>Aucune publication enregistrée.</div>");
        } else {
            for (int i = 0; i < Math.min(3, dernieresPublis.size()); i++) {
                Publication p = dernieresPublis.get(i);
                html.append(String.format("""
                    <div class="list-group-item py-3">
                        <div class="d-flex justify-content-between mb-1">
                            <span class="badge bg-light text-dark border small">%d</span>
                            <span class="badge bg-info bg-opacity-10 text-info small">%s</span>
                        </div>
                        <div class="fw-bold text-dark small mb-1">%s</div>
                        <small class="text-muted"><i class="bi bi-person me-1"></i> %s</small>
                    </div>
                """, p.getAnnee(), p.getEquipe(), p.getTitre(), p.getAuteur()));
            }
        }

        html.append("""
                        </div>
                    </div>
                </div>
            </div>
        """);

        // SCRIPTS CHART.JS
        html.append(String.format("""
            <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
            <script>
                document.addEventListener('DOMContentLoaded', function() {
                    // Chart Statuts
                    const ctxStatuts = document.getElementById('chartStatuts');
                    if (ctxStatuts) {
                        new Chart(ctxStatuts, {
                            type: 'doughnut',
                            data: {
                                labels: ['Permanents', 'Associés', 'Doctorants', 'Retraités'],
                                datasets: [{
                                    data: [%d, %d, %d, %d],
                                    backgroundColor: ['#1e40af', '#f59e0b', '#10b981', '#94a3b8'],
                                    borderWidth: 2
                                }]
                            },
                            options: {
                                responsive: true,
                                maintainAspectRatio: false,
                                plugins: { legend: { position: 'bottom' } }
                            }
                        });
                    }

                    // Chart Activités
                    const ctxActivites = document.getElementById('chartActivites');
                    if (ctxActivites) {
                        new Chart(ctxActivites, {
                            type: 'bar',
                            data: {
                                labels: ['Publications', 'Événements', 'Matériels', 'Documents'],
                                datasets: [{
                                    label: 'Total Enregistré',
                                    data: [%d, %d, %d, %d],
                                    backgroundColor: ['#3b82f6', '#f59e0b', '#10b981', '#06b6d4'],
                                    borderRadius: 6
                                }]
                            },
                            options: {
                                responsive: true,
                                maintainAspectRatio: false,
                                plugins: { legend: { display: false } },
                                scales: { y: { beginAtZero: true } }
                            }
                        });
                    }
                });
            </script>
        """, nbPermanents, nbAssocies, nbDoctorants, nbRetraites, nbPublications, nbEvenements, nbMateriels, nbDocuments));

        html.append(PageRenderer.renderFooter(request));
        response.getWriter().println(html.toString());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
