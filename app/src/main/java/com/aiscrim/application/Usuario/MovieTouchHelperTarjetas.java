package com.aiscrim.application.Usuario;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class MovieTouchHelperTarjetas extends ItemTouchHelper.SimpleCallback {
    private AdaptadorTarjetas mMovieAdapter;

    public MovieTouchHelperTarjetas(AdaptadorTarjetas movieAdapter){
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mMovieAdapter = movieAdapter;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //TODO: Not implemented here
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //Remove item
        mMovieAdapter.remove(viewHolder.getAdapterPosition());

    }

}
