package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ProfilActivity;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {


    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Get all Favoris Neighbours
     */
    List<Neighbour> getFavorisNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * change favoris statut
     * @param neighbour
     */
    void changeFavoris (Neighbour neighbour);
}
