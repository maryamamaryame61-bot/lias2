package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BasePV;
import com.lias.data.SampleData;
import com.lias.model.PV;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterPV")
public class AjouterPVServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterPVServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Rédiger un Procès-Verbal", "pv"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Enregistrer un Procès-Verbal (PV)</h2>
                            <p class="text-secondary small mb-0">Comptes-rendus des séances du conseil de laboratoire (Cahier des Charges &sect; 17).</p>
                        </div>
                        <a href="listerPV" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> PVs</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterPV">
                            <div class="mb-3">
                                <label class="form-label-custom">Instance ou Intitulé de la Réunion <span class="text-danger">*</span></label>
                                <input type="text" name="reunion" class="form-control-custom" placeholder="Ex: Réunion ordinaire du Conseil de Laboratoire" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Date de la réunion <span class="text-danger">*</span></label>
                                <input type="date" name="date" class="form-control-custom" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Ordre du Jour &amp; Décisions arrêtées <span class="text-danger">*</span></label>
                                <textarea name="ordreJour" rows="4" class="form-control-custom" placeholder="1. Validation des thèses en cours, 2. Arbitrage des dotations de calcul, 3. Préparation des colloques..." required></textarea>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerPV" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-file-earmark-check me-1"></i> Archiver le PV
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
        String reunion = request.getParameter("reunion");
        String date = request.getParameter("date");
        String ordreJour = request.getParameter("ordreJour");

        if (reunion != null && ordreJour != null) {
            BasePV.ajouter(new PV(reunion, date, ordreJour));
        }

        response.sendRedirect("listerPV");
    }
}
