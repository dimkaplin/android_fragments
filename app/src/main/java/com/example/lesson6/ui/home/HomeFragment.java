package com.example.lesson6.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson6.DataLike;
import com.example.lesson6.ImplLikeSource;
import com.example.lesson6.ItemAdapter;
import com.example.lesson6.NavigationActivity;
import com.example.lesson6.R;
import com.example.lesson6.databinding.FragmentHomeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private ImplLikeSource source;
    private List<String> innerList() {
        List<String> res = new ArrayList<>(5);
        res.add("дрель");
        res.add("шуроповерт");
        res.add("ножевка");
        res.add("гвоздодер");
        res.add("лопата");
        return res;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        RecyclerView rView = root.findViewById(R.id.recyclingView);
        rView.setHasFixedSize(true);
        LinearLayoutManager lManager = new LinearLayoutManager(getContext());
        rView.setLayoutManager(lManager);
        DividerItemDecoration decorator = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        rView.addItemDecoration(decorator);
        setHasOptionsMenu(true);
        //ItemAdapter iAdapter = new ItemAdapter(innerList());
        source = new ImplLikeSource(getResources()).init();
        //ItemAdapter iAdapter = new ItemAdapter(new ImplLikeSource(getResources()).init());
        ItemAdapter iAdapter = new ItemAdapter(source);
        rView.setAdapter(iAdapter);

        iAdapter.setListener(new ItemAdapter.ItemClickListerner() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "pop:", Toast.LENGTH_LONG).show();
            }
        });

        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_like_edit, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.like_add:
                source.addDataLike(new DataLike("111", "222", R.drawable.house, false));
                return true;
            case R.id.like_edit:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}