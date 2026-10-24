package com.lias.data;

import java.util.ArrayList;
import com.lias.model.RoleResponsabilite;

public class BaseRoles {

    private static ArrayList<RoleResponsabilite> roles = new ArrayList<>();

    public static void ajouter(RoleResponsabilite r) {
        roles.add(r);
    }

    public static ArrayList<RoleResponsabilite> getRoles() {
        return roles;
    }
}
