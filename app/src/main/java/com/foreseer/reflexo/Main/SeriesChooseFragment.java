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


public class SeriesChooseFragment extends Fragment {

    private OnSeriesChooseFragmentInteractionListener mListener;

    @BindView(R.id.button_oneSeries)
    Button button_oneSeries;

    @BindView(R.id.button_threeSeries)
    Button button_threeSeries;

    private Unbinder unbinder;

    public SeriesChooseFragment() {
        // Required empty public constructor
    }

    public static SeriesChooseFragment newInstance() {
        SeriesChooseFragment fragment = new SeriesChooseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_series_choose, container, false);

        unbinder = ButterKnife.bind(this, view);

        button_oneSeries.setOnClickListener(v -> onButtonPressed(v));
        button_threeSeries.setOnClickListener(v -> onButtonPressed(v));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(View view) {
        if (mListener != null) {
            Button button = (Button) view;
            String seriesChosen = button.getText().toString();
            mListener.onSeriesChosen(seriesChosen);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSeriesChooseFragmentInteractionListener) {
            mListener = (OnSeriesChooseFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSeriesChooseFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnSeriesChooseFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSeriesChosen(String seriesChosen);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
