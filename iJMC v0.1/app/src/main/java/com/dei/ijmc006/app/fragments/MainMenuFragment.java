package com.dei.ijmc006.app.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import com.dei.ijmc006.app.R;
import com.dei.ijmc006.app.adapters.MainScreenMenuSectionAdapter;
import com.dei.ijmc006.app.app.MainActivity;
import com.dei.ijmc006.app.config.Config;

import java.util.ArrayList;

/**
 * Created by user on 8/21/2014.
 */

public class MainMenuFragment extends Fragment {
    private View mainMenu;
    private Config config;
    private FragmentTransaction ft;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.config = new Config();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainMenu = inflater.inflate(R.layout.mainmenu_list, container, false);
        ft = getFragmentManager().beginTransaction();

        final ArrayList<String> menus = this.config.getMenu();
        MainScreenMenuSectionAdapter adapter = new MainScreenMenuSectionAdapter(getActivity(), menus);

       ListView listView = (ListView)mainMenu.findViewById(R.id.list);
       listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        ft.replace(R.id.container, new JmcProfileFragment(getActivity()));
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("JmcProfileFragment");
                        ft.commit();
                        break;
                    case 1:
                        ft.replace(R.id.container, new VmgFragment(getActivity()));
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("VmgFragment");
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.container, new JmcHymnFragment(getActivity()));
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("JmcHymnFragment");
                        ft.commit();
                        break;
                    case 3:
                        ft.replace(R.id.container, new JmcFacultyFragment(getActivity()));
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("JmcFacultyFragment");
                        ft.commit();
                        break;
                    case 4:
                        //Toast.makeText(getActivity(), "Clicked " + str, Toast.LENGTH_SHORT).show();
                        ft.replace(R.id.container, new TextualContent());
                        ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        ft.addToBackStack("TextualContent");
                        ft.commit();
                        break;
                }
            }
        });

        return mainMenu;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Log.e("MAIN FRAGMENT", "SETTINGS");
                SharedPreferences prefs = getActivity().getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putBoolean(config.LOGIN, false);
                editor.commit();

                Intent intent = new Intent(this.getActivity(), MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
