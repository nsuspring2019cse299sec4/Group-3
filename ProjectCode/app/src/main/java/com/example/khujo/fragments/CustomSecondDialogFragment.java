package com.example.play.bazarmind.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.play.bazarmind.contants.DataSource;
import com.example.play.bazarmind.models.Category;
import com.example.play.bazarmind.models.Item;
import com.example.play.bazarmind.R;
import com.example.play.bazarmind.utility.CustomFilterArrayAdapter;
import com.example.play.bazarmind.utility.InputHelper;

import java.util.ArrayList;
import java.util.List;

public class CustomSecondDialogFragment extends DialogFragment implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "CustomSecondDialogFragm";

    private AutoCompleteTextView mInput;
    private TextView mActionOk, mActionCancel;
    private AutoCompleteTextView mCategories;
    private int rowPosition = -1;
    private String listId = "", title = "";
    private Category mCategory;
    private CustomFilterArrayAdapter mItemAdapter;

    public CustomSecondDialogFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_custom_second_dialog, container, false);

        if (getArguments() != null) {
            if (getArguments().containsKey("id")) {
                //DataSource.listId = getArguments().getString("listId");
                listId = getArguments().getString("id");
                //DataSource.title = getArguments().getString("title");
                title = getArguments().getString("title");
                Toast.makeText(getContext(), DataSource.title, Toast.LENGTH_SHORT).show();
            }
        }

        Toast.makeText(getContext(), listId, Toast.LENGTH_SHORT).show();

        //Toast.makeText(getContext(), ((MyApplication) getActivity().getApplication()).getListId(), Toast.LENGTH_SHORT).show();

        mActionCancel = view.findViewById(R.id.action_cancel);
        mActionOk = view.findViewById(R.id.action_ok);
        mInput = view.findViewById(R.id.editTextNewItem);
        mCategories = view.findViewById(R.id.item_type_input);
        if (!DataSource.categories.isEmpty()) {
            List<String> categoriesNames = new ArrayList<>();
            for (int i = 0; i < DataSource.categories.size(); i++) {
                categoriesNames.add(InputHelper.capitalizeFirstLetter(DataSource.categories.get(i).getCategory()));
            }
            CustomFilterArrayAdapter arrayAdapter = new CustomFilterArrayAdapter(view.getContext(), android.R.layout.simple_spinner_dropdown_item, categoriesNames);
            mCategories.setAdapter(arrayAdapter);
            mCategories.setOnItemSelectedListener(this);
        }


        if (getArguments() != null) {
            if (getArguments().containsKey("position")) {
                rowPosition = getArguments().getInt("position");
                mInput.setText(DataSource.items.get(rowPosition).getName());
                Category category = getCategory(DataSource.items.get(rowPosition).getCategory());
                if (category != null) {
                    mCategories.setText(InputHelper.capitalizeFirstLetter(category.getCategory()));
                }

            }
        }


        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mInput.getText().toString();

                if (rowPosition != -1 && !input.equals("")) {
                    DataSource.items.get(rowPosition).setName(input);
                    Category category = getCategory(mCategories.getText().toString());
                    if (category != null) {
                        DataSource.items.get(rowPosition).setCategory(category.getCategory());
                        DataSource.items.get(rowPosition).setPlaceType(category.getPlaceType());
                    }
                } else if (!input.equals("")) {
                    Item item = new Item();
                    item.setName(input);
                    if (mCategory != null) {
                        item.setCategory(mCategory.getCategory());
                        item.setPlaceType(mCategory.getPlaceType());
                    } else {
                        Category category = getCategory(mCategories.getText().toString());
                        if (category != null) {
                            item.setCategory(category.getCategory());
                            item.setPlaceType(category.getPlaceType());
                        }
                    }
                    DataSource.items.add(item);
                }

                getDialog().dismiss();
                ShowListFragment fragment = new ShowListFragment();
                Bundle bundle = new Bundle();
                Toast.makeText(getContext(), listId, Toast.LENGTH_SHORT).show();
                bundle.putString("from", "edit");
                bundle.putString("listId", listId);
                bundle.putString("title", title);
                fragment.setArguments(bundle);
                loadFragment(fragment);
            }
        });

        if (!DataSource.existingItems.isEmpty()) {
            List<String> itemNames = new ArrayList<>();
            for (int i = 0; i < DataSource.existingItems.size(); i++) {
                itemNames.add(DataSource.existingItems.get(i).getName());
            }
            mItemAdapter = new CustomFilterArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemNames);
            if (mInput != null) {
                mInput.setAdapter(mItemAdapter);
                mInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.e(TAG, "onItemSelected: HERE BOIIIII ");
                        if (mItemAdapter != null && mItemAdapter.getItem(i) != null) {
                            Item item = getItem(mItemAdapter.getItem(i).toString());
                            if (item != null && mInput != null && mCategories != null) {
                                mInput.setText(item.getName());
                                mCategories.setText(item.getCategory());
                            }
                        }
                    }
                });
            }
        }

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private Category getCategory(String category) {
        for (int i = 0; i < DataSource.categories.size(); i++) {
            if (DataSource.categories.get(i).getCategory().equalsIgnoreCase(category)) {
                return DataSource.categories.get(i);
            }
        }
        return null;
    }

    private Item getItem(String name) {
        for (int i = 0; i < DataSource.existingItems.size(); i++) {
            if (DataSource.existingItems.get(i).getName().equalsIgnoreCase(name)) {
                return DataSource.existingItems.get(i);
            }
        }
        return null;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mCategory = getCategory(adapterView.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
