package com.example.voteinformed.ui.search;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voteinformed.R;
import com.example.voteinformed.data.entity.Politician;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class PoliticianSearchDialog extends DialogFragment {

    public interface OnPoliticianSelectedListener {
        void onPoliticianSelected(Politician politician);
    }

    private OnPoliticianSelectedListener callback;
    private TextInputEditText inputSearch;
    private SearchViewModel viewModel;
    private PoliticianSearchAdapter adapter;

    public static PoliticianSearchDialog newInstance() {
        return new PoliticianSearchDialog();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnPoliticianSelectedListener) {
            callback = (OnPoliticianSelectedListener) context;
        } else if (getParentFragment() instanceof OnPoliticianSelectedListener) {
            callback = (OnPoliticianSelectedListener) getParentFragment();
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), R.style.AppTheme_Dialog);
        builder.setView(R.layout.dialog_politician_search);
        AlertDialog dialog = builder.create();
        dialog.setOnShowListener(d -> setupDialogViews(dialog));
        return dialog;
    }

    private void setupDialogViews(AlertDialog dialog) {
        inputSearch = dialog.findViewById(R.id.inputSearchDialog);
        RecyclerView recycler = dialog.findViewById(R.id.recyclerSearchResultsDialog);

        if (recycler != null) {
            recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            adapter = new PoliticianSearchAdapter(getContext(), this::onPoliticianClicked);
            recycler.setAdapter(adapter);
        }

        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        viewModel.getResults().observe(this, this::updateResults);

        if (inputSearch != null) {
            inputSearch.setOnEditorActionListener((v, actionId, event) -> {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN)) {
                    performSearch();
                    return true;
                }
                return false;
            });
        }

        performSearch();

        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(params);
        }
    }

    private void performSearch() {
        String query = "";
        if (inputSearch != null && inputSearch.getText() != null) {
            query = inputSearch.getText().toString().trim();
        }
        viewModel.search(query);
    }

    private void updateResults(List<Politician> list) {
        if (adapter != null) {
            adapter.submitList(list);
        }
    }

    private void onPoliticianClicked(Politician p) {
        if (callback != null) {
            callback.onPoliticianSelected(p);
        }
        dismiss();
    }
}
