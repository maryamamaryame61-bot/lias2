package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseBesoins;
import com.lias.data.SampleData;
import com.lias.model.BesoinMateriel;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerBesoins")
public class ListerBesoinsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerBesoinsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<BesoinMateriel> besoins = BaseBesoins.getBesoins();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Expression des Besoins en Matériel", "besoins"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Expression des Besoins en Matériel &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Demandes d'équipements formulées par les chercheurs et arbitrage directionnel (Cahier des Charges &sect; 15).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterBesoin" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Formuler un besoin
                    </a>
                    <a href="listerMateriels" class="btn btn-outline-secondary btn-sm">
                        <i class="bi bi-box-seam me-1"></i> Voir parc matériel
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-cart-check text-warning me-2"></i> Registre des Besoins Déclarés</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(besoins.size()).append(" besoins en cours d'examen</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Chercheur / Demandeur</th>
                                <th>Équipement Demandé</th>
                                <th>Quantité</th>
                                <th>Statut d'Arbitrage</th>
                                <th class="text-end">Validation Direction</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (besoins.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucun besoin matériel exprimé.</td></tr>");
        } else {
            for (BesoinMateriel b : besoins) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-person-fill text-muted me-1"></i>%s</strong>
                        </td>
                        <td>
                            <span class="text-dark fw-semibold">%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border px-3 py-1">%d unité(s)</span>
                        </td>
                        <td>
                            <span class="badge-status badge-attente"><i class="bi bi-hourglass-split me-1"></i>En examen</span>
                        </td>
                        <td class="text-end">
                            <button class="btn btn-sm btn-success" onclick="alert('Demande approuvée pour : %s');">
                                <i class="bi bi-check-lg me-1"></i> Approuver
                            </button>
                        </td>
                    </tr>
                """, b.getDemandeur(), b.getMateriel(), b.getQuantite(), b.getDemandeur().replace("'", "\\'")));
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
