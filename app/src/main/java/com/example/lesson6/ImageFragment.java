package com.example.lesson6;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class ImageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String PARAM_INDEX = "image";
    private int image = 0;


    /*// TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;*/

    public ImageFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(int image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putInt(PARAM_INDEX, image);
        fragment.setArguments(args);
        return fragment;
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image = getArguments().getInt(PARAM_INDEX, 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView iv = view.findViewById(R.id.image_item);
        int[] image_list = {
                R.drawable.documentation,
                R.drawable.leonard,
                R.drawable._0thshades
        };
        iv.setImageResource(image_list[image]);
        setHasOptionsMenu(true);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Activity activity  = requireActivity();
                PopupMenu popupMenu = new PopupMenu(activity, view);
                activity.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                Menu menu = popupMenu.getMenu();
                menu.findItem(R.id.item1_popup);
                popupMenu.show();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        if (item.getItemId() == R.id.id_options_menu) {
            Toast.makeText(getContext(), "imageFrame", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}