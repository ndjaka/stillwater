package com.example.excellekitio.stillwaterscamps.interfaces;

import com.example.excellekitio.stillwaterscamps.entitiees.Campeur;

import java.util.List;

/**
 * Created by lesli on 13/12/2017.
 */

public interface iCampeur {
    public abstract List<Campeur> allDemande();
    public abstract boolean demande(String email, int id_camp);

}
