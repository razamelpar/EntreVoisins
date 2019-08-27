package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class NeighbourFragment extends Fragment {

    private NeighbourApiService mApiService;
    private List<Neighbour> mNeighbours;
    private List<Neighbour> favoris_Neighbours = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private String page;
    private static final String ONGLET = "page";


    /**
     * Create and return a new instance
     * @return @{@link NeighbourFragment}
     * @param page
     */
    public static NeighbourFragment newInstance(String page) {
        NeighbourFragment fragment = new NeighbourFragment();

        /** nommer fragment ONGLET page **/
        Bundle args = new Bundle();
        args.putString(ONGLET, page);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        /** recuperation du nom de l'onglet **/
        page = getArguments().getString(ONGLET);

        initList();
        return view;
    }

    /**
     * Init the List of neighbours
     * creation de ma deuxieme liste pour les voisins mis en favoris
     */
    private void initList() {
        mNeighbours = mApiService.getNeighbours();
        if (page == "favoris"){

            for (Neighbour liste : mNeighbours) {
                if (liste.isFavoris())
                    favoris_Neighbours.add(liste);
            }
            mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(favoris_Neighbours));
            }

        else {
                mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mNeighbours));

            }
    }

    /** je clear la liste avant de la charger pour eviter la multiplication d'item **/
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        favoris_Neighbours.clear();
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        mApiService.deleteNeighbour(event.neighbour);
        favoris_Neighbours.clear();
        initList();
    }
}
