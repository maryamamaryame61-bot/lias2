package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseBesoins;
import com.lias.data.SampleData;
import com.lias.model.BesoinMateriel;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterBesoin")
public class AjouterBesoinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterBesoinServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Exprimer un Besoin Matériel", "besoins"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Formuler une Demande de Matériel</h2>
                            <p class="text-secondary small mb-0">Expression des besoins pour la recherche et arbitrage directionnel (Cahier des Charges &sect; 15).</p>
                        </div>
                        <a href="listerBesoins" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Besoins</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterBesoin">
                            <div class="mb-3">
                                <label class="form-label-custom">Demandeur (Nom &amp; Prénom du Chercheur / Doctorant) <span class="text-danger">*</span></label>
                                <input type="text" name="demandeur" class="form-control-custom" placeholder="Ex: Amina Mansouri" required>
                            </div>

                            <div class="mb-3">
                                <label class="form-label-custom">Matériel ou Équipement souhaité <span class="text-danger">*</span></label>
                                <input type="text" name="materiel" class="form-control-custom" placeholder="Ex: Disque dur SSD NVMe 2To pour dataset d'apprentissage" required>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Quantité demandée <span class="text-danger">*</span></label>
                                <input type="number" name="quantite" class="form-control-custom" value="1" min="1" required>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerBesoins" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-send me-1"></i> Transmettre la demande
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
        String demandeur = request.getParameter("demandeur");
        String materiel = request.getParameter("materiel");
        int quantite = 1;
        try {
            quantite = Integer.parseInt(request.getParameter("quantite"));
        } catch (Exception ignored) {}

        if (demandeur != null && materiel != null) {
            BaseBesoins.ajouter(new BesoinMateriel(demandeur, materiel, quantite));
        }

        response.sendRedirect("listerBesoins");
    }
}
