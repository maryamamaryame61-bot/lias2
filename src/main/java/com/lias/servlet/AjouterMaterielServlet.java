package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseMateriels;
import com.lias.data.SampleData;
import com.lias.model.Materiel;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterMateriel")
public class AjouterMaterielServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterMaterielServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Déclarer un Arrivage Matériel", "materiels"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Enregistrer un Arrivage de Matériel</h2>
                            <p class="text-secondary small mb-0">Gestion de la dotation matérielle et attribution équitable (Cahier des Charges &sect; 14).</p>
                        </div>
                        <a href="listerMateriels" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Parc</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterMateriel">
                            <div class="mb-3">
                                <label class="form-label-custom">Désignation de l'équipement <span class="text-danger">*</span></label>
                                <input type="text" name="nom" class="form-control-custom" placeholder="Ex: Serveur de calcul GPU NVIDIA RTX 4090" required>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Quantité reçue <span class="text-danger">*</span></label>
                                    <input type="number" name="quantite" class="form-control-custom" value="1" min="1" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date de Réception / Arrivée <span class="text-danger">*</span></label>
                                    <input type="date" name="dateArrivee" class="form-control-custom" required>
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Bénéficiaire Officiel (Équipe ou Chercheur) <span class="text-danger">*</span></label>
                                <input type="text" name="beneficiaire" class="form-control-custom" placeholder="Ex: Équipe SDIA ou Doctorants LIAS" required>
                                <div class="form-text small">Permet le suivi rigoureux du principe : « Qui a reçu quoi, qui n'a pas reçu ».</div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerMateriels" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-box-seam me-1"></i> Enregistrer l'équipement
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
        String nom = request.getParameter("nom");
        String dateArrivee = request.getParameter("dateArrivee");
        String beneficiaire = request.getParameter("beneficiaire");
        int quantite = 1;
        try {
            quantite = Integer.parseInt(request.getParameter("quantite"));
        } catch (Exception ignored) {}

        if (nom != null && beneficiaire != null) {
            BaseMateriels.ajouter(new Materiel(nom, quantite, dateArrivee, beneficiaire));
        }

        response.sendRedirect("listerMateriels");
    }
}
