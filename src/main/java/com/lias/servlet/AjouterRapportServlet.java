package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseRapports;
import com.lias.data.SampleData;
import com.lias.model.RapportAnnuel;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterRapport")
public class AjouterRapportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterRapportServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Rédiger un Rapport Annuel", "rapports"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Rédiger une Synthèse de Rapport Annuel</h2>
                            <p class="text-secondary small mb-0">Production du bilan d'activité scientifique (Cahier des Charges &sect; 11).</p>
                        </div>
                        <a href="listerRapports" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Rapports</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterRapport">
                            <div class="mb-3">
                                <label class="form-label-custom">Année Universitaire d'Exercice <span class="text-danger">*</span></label>
                                <input type="number" name="annee" class="form-control-custom" value="2026" min="2010" max="2035" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Résumé Exécutif des Réalisations Scientifiques <span class="text-danger">*</span></label>
                                <textarea name="resume" rows="6" class="form-control-custom" placeholder="Synthèse des activités du laboratoire : nombre d'articles publiés, soutenances doctorales, colloques organisés, dotations de calcul et partenariats signés..." required></textarea>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerRapports" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-file-earmark-check me-1"></i> Enregistrer le Rapport
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
        int annee = 2026;
        try {
            annee = Integer.parseInt(request.getParameter("annee"));
        } catch (Exception ignored) {}

        String resume = request.getParameter("resume");
        if (resume != null && !resume.isBlank()) {
            BaseRapports.ajouter(new RapportAnnuel(annee, resume));
        }

        response.sendRedirect("listerRapports");
    }
}
