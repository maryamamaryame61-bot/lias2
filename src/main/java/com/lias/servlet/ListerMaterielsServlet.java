package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseMateriels;
import com.lias.data.BaseMembres;
import com.lias.data.SampleData;
import com.lias.model.Materiel;
import com.lias.model.Membre;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerMateriels")
public class ListerMaterielsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerMaterielsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Materiel> materiels = BaseMateriels.getMateriels();
        ArrayList<Membre> membres = BaseMembres.getMembres();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Gestion du Matériel & Arrivages", "materiels"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Parc Matériel &amp; Arrivages &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Suivi des équipements, traçabilité des attributions et distribution équitable (Cahier des Charges &sect; 14).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterMateriel" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Déclarer un arrivage
                    </a>
                    <a href="listerBesoins" class="btn btn-outline-warning text-dark btn-sm">
                        <i class="bi bi-cart-plus me-1"></i> Besoins exprimés
                    </a>
                </div>
            </div>

            <!-- REGISTRE DES ÉQUIPEMENTS REÇUS -->
            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-cpu text-primary me-2"></i> Inventaire des Arrivages de Matériel</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(materiels.size()).append(" lots d'équipements</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Équipement / Désignation</th>
                                <th>Quantité</th>
                                <th>Date d'Arrivée</th>
                                <th>Bénéficiaire Officiel (Qui a reçu quoi)</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (materiels.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucun matériel enregistré dans le parc.</td></tr>");
        } else {
            for (Materiel m : materiels) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-laptop text-primary me-2"></i>%s</strong>
                        </td>
                        <td>
                            <span class="badge bg-primary fs-6 px-3 py-1">%d unité(s)</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-calendar3 me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border p-2"><i class="bi bi-person-check-fill text-success me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="badge-status badge-accepte">Distribué</span>
                        </td>
                    </tr>
                """, m.getNom(), m.getQuantite(), m.getDateArrivee(), m.getBeneficiaire()));
            }
        }

        html.append("""
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- ANALYSE DE LA DISTRIBUTION ÉQUITABLE -->
            <div class="custom-card p-4">
                <h5 class="fw-bold text-dark mb-3"><i class="bi bi-pie-chart-fill text-info me-2"></i> Suivi de la Distribution Équitable (&sect; 14)</h5>
                <p class="text-secondary small mb-3">Conformément aux règles du laboratoire, le système surveille les dotations attribuées pour garantir l'équité entre chercheurs et doctorants :</p>

                <div class="row g-3">
                    <div class="col-md-6">
                        <div class="p-3 bg-light rounded-3 border">
                            <h6 class="fw-bold text-success mb-2"><i class="bi bi-check-circle-fill me-1"></i> Équipes / Membres ayant reçu du matériel</h6>
                            <ul class="list-unstyled small text-muted mb-0">
                                <li>&bull; <strong>Équipe SDIA :</strong> 2 Serveurs GPU NVIDIA A100 80GB (Priorité calcul IA)</li>
                                <li>&bull; <strong>Doctorants LIAS :</strong> 6 Stations de calcul Dell Precision i9</li>
                                <li>&bull; <strong>Équipe SEIS :</strong> 15 Kits capteurs IoT &amp; LoRaWAN</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="p-3 bg-light rounded-3 border">
                            <h6 class="fw-bold text-warning mb-2"><i class="bi bi-hourglass-split me-1"></i> Besoins en attente de dotation</h6>
                            <ul class="list-unstyled small text-muted mb-0">
                                <li>&bull; <strong>Équipe GLSIA :</strong> Demande d'abonnements Cloud &bull; <a href="listerBesoins" class="text-primary text-decoration-none">Voir arbitrage &rarr;</a></li>
                                <li>&bull; <strong>Mehdi Chraibi :</strong> Disque SSD NVMe 2To &bull; <span class="badge bg-warning text-dark">Arbitrage Direction</span></li>
                            </ul>
                        </div>
                    </div>
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
