package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseConventions;
import com.lias.data.SampleData;
import com.lias.model.Convention;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListerConventionsServlet")
public class ListerConventionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerConventionsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<Convention> conventions = BaseConventions.getConventions();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Conventions & Collaborations", "conventions"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Conventions &amp; Collaborations &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Partenariats industriels, académiques et institutionnels (Cahier des Charges &sect; 16).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterConvention" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-plus-lg me-1"></i> Nouvelle Convention
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-handshake text-primary me-2"></i> Accords &amp; Partenariats Actifs</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(conventions.size()).append(" convention(s) signée(s)</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th style="width: 35%;">Partenaire Institutionnel / Industriel</th>
                                <th style="width: 40%;">Objet du Partenariat Scientifique</th>
                                <th>Date de Signature</th>
                                <th>Durée Validité</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (conventions.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucune convention enregistrée.</td></tr>");
        } else {
            for (Convention c : conventions) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-building text-primary me-2"></i>%s</strong>
                        </td>
                        <td>
                            <span class="small text-secondary">%s</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-pen me-1 text-muted"></i>%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-dark border">%s</span>
                        </td>
                        <td>
                            <span class="badge-status badge-accepte">En vigueur</span>
                        </td>
                    </tr>
                """, c.getPartenaire(), c.getObjet(), c.getDateSignature(), c.getDuree()));
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
