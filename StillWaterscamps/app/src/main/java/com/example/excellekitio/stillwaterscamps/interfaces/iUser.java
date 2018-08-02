package com.example.excellekitio.stillwaterscamps.interfaces;

import com.example.excellekitio.stillwaterscamps.entitiees.User;

import java.util.List;

/**
 * Created by lesli on 13/12/2017.
 */

public interface iUser {
    public abstract boolean Insertion_user(String email, String name, String img, int sexe, String pays, String date_naissance);
    public abstract List<User> allUser();

}
