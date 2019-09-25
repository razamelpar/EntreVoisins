package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ProfilActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


    /**
     * delocalisation de ma methode de creation de liste de favoris
     * @return
     */
    @Override
    public List<Neighbour> getFavorisNeighbours() {

        List<Neighbour> favorisNeighbours = new ArrayList<>();

        for (Neighbour liste : neighbours) {
            if (liste.isFavoris()) {
                favorisNeighbours.add(liste);
            }
        }

        return favorisNeighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * methode de services pour changer le statut favoris
     * @param neighbour
     */
    @Override
    public void changeFavoris(Neighbour neighbour) {
        neighbours.get(neighbours.indexOf(neighbour)).setFavoris(!neighbour.isFavoris());
    }
}
