package com.foreseer.reflexo.Main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.foreseer.reflexo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GameChooseFragment extends Fragment{

    private OnGameChooseFragmentInteractionListener mListener;

    @BindView(R.id.button_fourSquares)
    Button button_fourSquares;

    @BindView(R.id.button_twoSquares)
    Button button_twoSquares;

    private Unbinder unbinder;

    public GameChooseFragment() {
        // Required empty public constructor
    }

    public static GameChooseFragment newInstance() {
        GameChooseFragment fragment = new GameChooseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_choose, container, false);
        unbinder = ButterKnife.bind(this, view);

        button_twoSquares.setOnClickListener(v -> onButtonPressed(v));
        button_fourSquares.setOnClickListener(v -> onButtonPressed(v));

        return view;
    }

    public void onButtonPressed(View view) {
        if (mListener != null) {
            Button button = (Button) view;
            String text = button.getText().toString();
            mListener.onGameSelected(text);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGameChooseFragmentInteractionListener) {
            mListener = (OnGameChooseFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGameChooseFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnGameChooseFragmentInteractionListener {
        // TODO: Update argument type and name
        void onGameSelected(String gameName);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
