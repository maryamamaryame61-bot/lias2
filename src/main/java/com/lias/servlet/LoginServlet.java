package com.lias.servlet;

import java.io.IOException;

import com.lias.data.SampleData;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SampleData.init();
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("""
    <!DOCTYPE html>
    <html lang="fr">
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Membre | Laboratoire LIAS - FSBM Casablanca</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700;800&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="css/style.css">

    <style>
    body {
        margin: 0;
        min-height: 100vh;
        font-family: 'Inter', Arial, sans-serif;
        background: linear-gradient(135deg, #07253d 0%, #0b3b60 50%, #155e96 100%);
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }

    .welcome-card {
        width: 100%;
        max-width: 520px;
        padding: 45px;
        text-align: center;
        color: white;
        border-radius: 25px;
        background: rgba(255, 255, 255, 0.12);
        box-shadow: 0 20px 60px rgba(0,0,0,0.35);
        backdrop-filter: blur(14px);
        border: 1px solid rgba(255,255,255,0.25);
    }

    .btn-action {
        display: flex;
        align-items: center;
        justify-content: center;
        gap: 10px;
        margin: 14px auto;
        padding: 14px 28px;
        width: 100%;
        border-radius: 12px;
        text-decoration: none;
        color: white;
        font-size: 16px;
        font-weight: 600;
        transition: all 0.25s ease;
    }

    .btn-connect {
        background: linear-gradient(135deg, #0284c7, #0369a1);
        box-shadow: 0 6px 20px rgba(2, 132, 199, 0.35);
    }
    .btn-connect:hover {
        background: linear-gradient(135deg, #0369a1, #075985);
        transform: translateY(-2px);
        color: white;
    }

    .btn-register {
        background: rgba(255, 255, 255, 0.18);
        border: 1px solid rgba(255, 255, 255, 0.3);
    }
    .btn-register:hover {
        background: rgba(255, 255, 255, 0.28);
        transform: translateY(-2px);
        color: white;
    }

    .btn-portal {
        background: transparent;
        color: #e0f2fe;
        font-size: 14px;
        font-weight: 500;
    }
    .btn-portal:hover {
        color: #ffffff;
    }
    </style>
    </head>

    <body>
        <div class="welcome-card">
            <div class="mb-3">
                <img src="images/lias-logo.svg" alt="LIAS Logo" height="52" class="bg-white p-2 rounded-3 shadow">
            </div>
            <h2 class="fw-bold mb-1">Laboratoire LIAS</h2>
            <p class="text-light text-opacity-75 small mb-4">Faculté des Sciences Ben M'Sik &bull; Université Hassan II Casablanca</p>

            <div class="p-3 mb-4 rounded-3 text-start small" style="background: rgba(0,0,0,0.2); border: 1px solid rgba(255,255,255,0.15);">
                <i class="bi bi-shield-check text-warning me-1"></i> <strong>Accès sécurisé réservé aux membres :</strong><br>
                Permanents, associés, doctorants, chefs d'équipe et direction.
            </div>

            <a class="btn-action btn-connect" href="authentification">
                <i class="bi bi-box-arrow-in-right"></i> J'ai déjà un compte
            </a>
            <a class="btn-action btn-register" href="inscription">
                <i class="bi bi-person-plus"></i> Créer un nouveau compte
            </a>
            <a class="btn-action btn-portal" href="index.jsp">
                <i class="bi bi-globe"></i> Retour au portail public
            </a>
        </div>
    </body>
    </html>
    """);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login != null && login.equals("admin") && password != null && password.equals("admin123")) {
            HttpSession session = request.getSession();
            session.setAttribute("user", login);
            response.sendRedirect("accueil");
        } else {
            response.sendRedirect("authentification?error=1");
        }
    }
}
