package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseRoles;
import com.lias.data.SampleData;
import com.lias.model.RoleResponsabilite;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerRoles")
public class ListerRolesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<RoleResponsabilite> roles = BaseRoles.getRoles();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Rôles & Responsabilités", "roles"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Rôles &amp; Responsabilités &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Types : Directeur, Vice-directeur, Chef d'équipe, Membre d'équipe (Cahier des Charges &sect; 7).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterRole" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-person-plus-fill me-1"></i> Affecter un rôle
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <h5><i class="bi bi-person-badge text-primary me-2"></i> Tableau d'Affectation des Responsabilités</h5>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(roles.size()).append(" affectations actives</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0">
                        <thead>
                            <tr>
                                <th>Membre du LIAS</th>
                                <th>Rôle / Responsabilité Institutionnelle</th>
                                <th>Date d'Affectation</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (roles.isEmpty()) {
            html.append("<tr><td colspan='4' class='text-center text-muted py-5'>Aucun rôle affecté.</td></tr>");
        } else {
            for (RoleResponsabilite r : roles) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-person-fill text-muted me-1"></i>%s</strong>
                        </td>
                        <td>
                            <span class="badge bg-primary bg-opacity-10 text-primary fs-6 px-3 py-1">%s</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-calendar3 me-1"></i>Depuis le %s</span>
                        </td>
                        <td>
                            <span class="badge-status badge-accepte">Actif</span>
                        </td>
                    </tr>
                """, r.getMembre(), r.getRole(), r.getDateDebut()));
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
