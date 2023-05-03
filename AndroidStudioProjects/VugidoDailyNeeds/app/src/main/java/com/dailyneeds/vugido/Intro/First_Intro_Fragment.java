package com.dailyneeds.vugido.Intro;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.dailyneeds.vugido.R;


public class First_Intro_Fragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

     String mParam1;
     String mParam2;

    private OnFragmentInteractionListener mListener;

    public First_Intro_Fragment() {
        // Required empty public constructor
    }

    public static First_Intro_Fragment newInstance(String param1, String param2) {
        First_Intro_Fragment fragment = new First_Intro_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first__intro_, container, false);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*  @Override
      public void onAttach(Context context) {
          super.onAttach(context);
          if (context instanceof OnFragmentInteractionListener) {
              mListener = (OnFragmentInteractionListener) context;
          } else {
              throw new RuntimeException(context.toString()
                      + " must implement OnFragmentInteractionListener");
          }
      }
  */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
