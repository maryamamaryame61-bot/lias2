package com.lias.servlet;

import java.io.IOException;

import com.lias.data.BaseDemandes;
import com.lias.data.BaseMembres;
import com.lias.model.DemandeAdhesion;
import com.lias.model.Membre;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/validerDemande")
public class ValiderDemandeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int index = Integer.parseInt(request.getParameter("id"));
            String action = request.getParameter("action");

            DemandeAdhesion d = BaseDemandes.getDemande(index);

            if (d != null) {
                if ("accept".equalsIgnoreCase(action)) {
                    d.accepter();
                    // Cahier des Charges § 6 : Décision Acceptée -> création automatique du membre
                    BaseMembres.ajouter(new Membre(d.getNom(), "", d.getEmail(), "doctorant"));
                } else {
                    d.refuser();
                }
            }
        } catch (Exception ignored) {}

        response.sendRedirect("listerDemandes");
    }
}
