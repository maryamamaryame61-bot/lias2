package com.lias.servlet;

import java.io.IOException;
import java.util.ArrayList;

import com.lias.data.BaseDocuments;
import com.lias.data.SampleData;
import com.lias.model.DocumentLabo;
import com.lias.ui.PageRenderer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listerDocuments")
public class ListerDocumentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListerDocumentsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        ArrayList<DocumentLabo> documents = BaseDocuments.getDocuments();

        StringBuilder html = new StringBuilder();
        html.append(PageRenderer.renderHeader(request, "Archivage Documentaire", "documents"));

        html.append("""
            <div class="d-flex flex-column flex-md-row justify-content-between align-items-md-center gap-3 mb-4">
                <div>
                    <h2 class="fw-bold text-dark mb-1">Archivage Documentaire &bull; LIAS</h2>
                    <p class="text-secondary small mb-0">Capitalisation de la mémoire du laboratoire : attestations, financements et rapports (Cahier des Charges &sect; 10).</p>
                </div>
                <div class="d-flex gap-2">
                    <a href="ajouterDocument" class="btn btn-primary-lias btn-sm">
                        <i class="bi bi-cloud-upload me-1"></i> Archiver un document
                    </a>
                </div>
            </div>

            <div class="custom-card mb-4">
                <div class="custom-card-header">
                    <div class="position-relative flex-grow-1 me-3" style="max-width: 400px;">
                        <i class="bi bi-search position-absolute top-50 start-0 translate-middle-y ms-3 text-muted"></i>
                        <input type="text" class="form-control form-control-sm ps-5 table-search-input" data-table="docsTable" placeholder="Recherche par titre, type, date...">
                    </div>
                    <span class="badge bg-light text-dark border">
        """);

        html.append(documents.size()).append(" documents archivés</span>\n</div>\n");

        html.append("""
                <div class="table-responsive">
                    <table class="table table-custom align-middle mb-0" id="docsTable">
                        <thead>
                            <tr>
                                <th style="width: 45%;">Titre du Document</th>
                                <th>Catégorie / Type</th>
                                <th>Date d'Archivage</th>
                                <th>Fichier</th>
                                <th class="text-end">Action</th>
                            </tr>
                        </thead>
                        <tbody>
        """);

        if (documents.isEmpty()) {
            html.append("<tr><td colspan='5' class='text-center text-muted py-5'>Aucun document archivé.</td></tr>");
        } else {
            for (DocumentLabo d : documents) {
                html.append(String.format("""
                    <tr>
                        <td>
                            <strong class="text-dark"><i class="bi bi-file-earmark-pdf text-danger me-2"></i>%s</strong>
                        </td>
                        <td>
                            <span class="badge bg-info bg-opacity-10 text-info">%s</span>
                        </td>
                        <td>
                            <span class="text-muted small"><i class="bi bi-calendar3 me-1"></i>%s</span>
                        </td>
                        <td>
                            <span class="badge bg-light text-secondary border small">%s</span>
                        </td>
                        <td class="text-end">
                            <button class="btn btn-sm btn-outline-primary" onclick="alert('Téléchargement du document : %s');">
                                <i class="bi bi-download me-1"></i> Télécharger
                            </button>
                        </td>
                    </tr>
                """, d.getTitre(), d.getType(), d.getDate(), d.getLien(), d.getTitre().replace("'", "\\'")));
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
