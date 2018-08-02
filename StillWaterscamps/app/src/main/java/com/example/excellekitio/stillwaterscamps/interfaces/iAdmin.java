package com.example.excellekitio.stillwaterscamps.interfaces;

import com.example.excellekitio.stillwaterscamps.entitiees.Admin;
import com.example.excellekitio.stillwaterscamps.entitiees.Alldemande;

import java.util.List;

/**
 * Created by lesli on 13/12/2017.
 */

public interface iAdmin {
    public abstract Admin createAdmin(String password, String name);
    public abstract  Admin connect(String password, String name);
    public abstract boolean validerDemande(String email_user, int id_camp, int id_admin, Boolean valider, Boolean choix);


}
