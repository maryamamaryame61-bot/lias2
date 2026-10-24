package com.lias.data;

import java.util.ArrayList;
import com.lias.model.PV;

public class BasePV {

    private static ArrayList<PV> pvs = new ArrayList<>();

    public static void ajouter(PV pv) {
        pvs.add(pv);
    }

    public static ArrayList<PV> getPvs() {
        return pvs;
    }
}
