package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseDocuments;
import com.lias.data.SampleData;
import com.lias.model.DocumentLabo;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/ajouterDocument")
@MultipartConfig
public class AjouterDocumentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AjouterDocumentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Archiver un Document", "documents"));

        html.append("""
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <div>
                            <h2 class="fw-bold text-dark mb-1">Archiver un Document Officiel</h2>
                            <p class="text-secondary small mb-0">Demandes de financement, programmes, attestations, rapports (Cahier des Charges &sect; 10).</p>
                        </div>
                        <a href="listerDocuments" class="btn btn-outline-secondary btn-sm"><i class="bi bi-arrow-left me-1"></i> Archives</a>
                    </div>

                    <div class="custom-card p-4 p-md-5">
                        <form method="post" action="ajouterDocument" enctype="multipart/form-data">
                            <div class="mb-3">
                                <label class="form-label-custom">Intitulé du document <span class="text-danger">*</span></label>
                                <input type="text" name="titre" class="form-control-custom" placeholder="Ex: Formulaire de mission ou attestation de recherche" required>
                            </div>

                            <div class="row g-3 mb-3">
                                <div class="col-md-6">
                                    <label class="form-label-custom">Typologie du document <span class="text-danger">*</span></label>
                                    <select name="type" class="form-select-custom">
                                        <option value="Demande de financement">Demande de Financement</option>
                                        <option value="Programme">Programme de colloque / workshop</option>
                                        <option value="Attestation">Attestation de recherche</option>
                                        <option value="Rapport">Rapport d'activité / scientifique</option>
                                        <option value="Document administratif">Document Administratif</option>
                                    </select>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label-custom">Date du document <span class="text-danger">*</span></label>
                                    <input type="date" name="date" class="form-control-custom" required>
                                </div>
                            </div>

                            <div class="mb-4">
                                <label class="form-label-custom">Fichier à téléverser (PDF, Word...)</label>
                                <input type="file" name="fichier" class="form-control-custom">
                                <div class="form-text small">Taille maximale recommandée : 10 Mo. Format PDF privilégié pour l'archivage pérenne.</div>
                            </div>

                            <div class="d-flex justify-content-between align-items-center pt-3 border-top">
                                <a href="listerDocuments" class="btn btn-outline-secondary">Annuler</a>
                                <button type="submit" class="btn btn-primary-lias px-4">
                                    <i class="bi bi-cloud-upload me-1"></i> Archiver le document
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titre = request.getParameter("titre");
        String type = request.getParameter("type");
        String date = request.getParameter("date");
        String nomFichier = "document.pdf";

        try {
            Part filePart = request.getPart("fichier");
            if (filePart != null && filePart.getSubmittedFileName() != null && !filePart.getSubmittedFileName().isBlank()) {
                nomFichier = filePart.getSubmittedFileName();
            }
        } catch (Exception ignored) {}

        if (titre != null && !titre.isBlank()) {
            BaseDocuments.ajouter(new DocumentLabo(titre, type, date, nomFichier));
        }

        response.sendRedirect("listerDocuments");
    }
}
