package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseRoles;
import com.lias.data.SampleData;
import com.lias.model.RoleResponsabilite;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterRole")
public class AjouterRoleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Affecter un Rôle", "roles"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Affecter une Responsabilité</h2>
                            <p class="text-secondary small mb-0">Gestion des rôles sans suppression de données historiques (Cahier des Charges &sect; 7).</p>
                        </div>
                        <a href="listerRoles" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Rôles</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterRole">
                            <div class="mb-3">
                                <label class="form-label-custom">Nom &amp; Prénom du Membre <span class="text-danger">*</span></label>
                                <input type="text" name="membre" class="form-control-custom" placeholder="Ex: Pr. Mohamed Alami" required>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Rôle / Titre Institutionnel <span class="text-danger">*</span></label>
                                    <select name="role" class="form-select-custom">
                                        <option value="Directeur">Directeur du Laboratoire</option>
                                        <option value="Vice-directeur">Vice-Directeur(trice)</option>
                                        <option value="Chef d'équipe">Chef d'Équipe de Recherche</option>
                                        <option value="Membre d'équipe">Membre d'Équipe / Chercheur</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date de prise de fonction <span class="text-danger">*</span></label>
                                    <input type="date" name="dateDebut" class="form-control-custom" required>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerRoles" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-person-check me-1"></i> Valider l'affectation
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String membre = request.getParameter("membre");
        String role = request.getParameter("role");
        String dateDebut = request.getParameter("dateDebut");

        if (membre != null && role != null) {
            BaseRoles.ajouter(new RoleResponsabilite(membre, role, dateDebut));
        }

        response.sendRedirect("listerRoles");
    }
}
