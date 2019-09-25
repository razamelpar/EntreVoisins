package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.service.autofill.FieldClassification;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourApiService;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import junit.extensions.ActiveTestSuite;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.openLink;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Created by Alexandre Vanneçon "Razamelpar" on 02/09/2019.
 */
public class MyNeighbourRecyclerViewAdapterTest {

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mListNeighbourActivityActivityTestRule =
            new ActivityTestRule<>(ListNeighbourActivity.class);

    private ListNeighbourActivity mActivity;
    private NeighbourApiService mService;
    private DummyNeighbourApiService mDummyNeighbourApiService;

    @Test
    /** test item clicable dans la liste de voisins **/
    public void onBindViewHolder() throws Exception
    {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txtProfil)).check(matches(isDisplayed()));

    }

    @Test
    /** test affichage de profil **/
    public void profilIsPopulated() throws Exception
    {
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.txtProfil)).check(matches(withText("Caroline")));
    }

    @Test
    /** test mise en favoris des profil **/
    public void FavorisListIsPopulated() throws Exception
    {
        //click sur un profil
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        // click sur le bouton favoris dans la fiche profil
        onView(withId(R.id.favorisButton)).perform(click());
        // retour en arriere
        pressBack();
        // bascule sur l'onglet favoris
        swipeLeft();
        // click sur le profil depuis l'onglet favoris
        onView(allOf(withId(R.id.list_neighbours),isDisplayed())).perform(actionOnItemAtPosition(0, click()));
        // on verifie que le nom du profil favoris est bien le meme que celui choisi dans la liste des voisins
        onView(withId(R.id.txtProfil)).check(matches(withText("Caroline")));
    }

    @Before
    public void setUp() {
        mActivity = mListNeighbourActivityActivityTestRule.getActivity();
        mService = DI.getNewInstanceApiService();

    }
}