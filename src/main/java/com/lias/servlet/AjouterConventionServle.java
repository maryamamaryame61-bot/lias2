package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseConventions;
import com.lias.data.SampleData;
import com.lias.model.Convention;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterConvention")
public class AjouterConventionServle extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterConventionServle() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Nouvelle Convention", "conventions"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Enregistrer une Convention de Partenariat</h2>
                            <p class="text-secondary small mb-0">Partenaires académiques et industriels (Cahier des Charges &sect; 16).</p>
                        </div>
                        <a href="ListerConventionsServlet" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Conventions</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterConvention">
                            <div class="mb-3">
                                <label class="form-label-custom">Partenaire Institutionnel ou Entreprise <span class="text-danger">*</span></label>
                                <input type="text" name="partenaire" class="form-control-custom" placeholder="Ex: CNESTEN, Technopark ou Université partenaire" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Objet &amp; Cadre Scientifique de la Coopération <span class="text-danger">*</span></label>
                                <input type="text" name="objet" class="form-control-custom" placeholder="Ex: Recherche collaborative en traitement de données massives" required>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date de Signature <span class="text-danger">*</span></label>
                                    <input type="date" name="dateSignature" class="form-control-custom" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Durée de Validité <span class="text-danger">*</span></label>
                                    <input type="text" name="duree" class="form-control-custom" placeholder="Ex: 3 ans (renouvelable)" required>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="ListerConventionsServlet" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-handshake me-1"></i> Enregistrer l'accord
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
        String partenaire = request.getParameter("partenaire");
        String objet = request.getParameter("objet");
        String dateSignature = request.getParameter("dateSignature");
        String duree = request.getParameter("duree");

        if (partenaire != null && objet != null) {
            BaseConventions.ajouter(new Convention(partenaire, objet, dateSignature, duree));
        }

        response.sendRedirect("ListerConventionsServlet");
    }
}
