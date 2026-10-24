package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseMandats;
import com.lias.data.SampleData;
import com.lias.model.Mandat;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ajouterMandat")
public class AjouterMandatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Nouveau Mandat de Gouvernance", "mandats"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Enregistrer une Période de Gouvernance</h2>
                            <p class="text-secondary small mb-0">Définition des mandats de direction et chefs d'équipe (Cahier des Charges &sect; 5.3).</p>
                        </div>
                        <a href="listerMandats" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Mandats</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterMandat">
                            <div class="mb-3">
                                <label class="form-label-custom">Directeur du Laboratoire (Mandat Actif) <span class="text-danger">*</span></label>
                                <input type="text" name="directeur" class="form-control-custom" placeholder="Ex: Pr. Hassan El Idrissi" required>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Vice-Directeur(trice) <span class="text-danger">*</span></label>
                                    <input type="text" name="viceDirecteur" class="form-control-custom" placeholder="Ex: Dr. Fatima Zahra Bennani" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Chef(s) d'Équipe <span class="text-danger">*</span></label>
                                    <input type="text" name="chefEquipe" class="form-control-custom" placeholder="Ex: Pr. Mohamed Alami" required>
                                </div>
                            </div>

                            <div class="row g-3 mb-4">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date de Début du Mandat <span class="text-danger">*</span></label>
                                    <input type="date" name="dateDebut" class="form-control-custom" required>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date d'Échéance / Fin <span class="text-danger">*</span></label>
                                    <input type="date" name="dateFin" class="form-control-custom" required>
                                </div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerMandats" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-award me-1"></i> Enregistrer le mandat
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

        Mandat m = new Mandat(
                request.getParameter("directeur"),
                request.getParameter("viceDirecteur"),
                request.getParameter("chefEquipe"),
                request.getParameter("dateDebut"),
                request.getParameter("dateFin")
        );

        BaseMandats.ajouter(m);
        response.sendRedirect("listerMandats");
    }
}
