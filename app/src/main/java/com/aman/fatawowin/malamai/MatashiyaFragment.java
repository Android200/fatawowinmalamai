package com.aman.fatawowin.malamai;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aman.fatawowin.malamai.Entities.Matashiya;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ms.square.android.expandabletextview.ExpandableTextView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MatashiyaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MatashiyaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatashiyaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FirebaseDatabase mFirebase;
    DatabaseReference mFirebaseRefData;
    private ChildEventListener mChildEventLister;
    FirebaseRecyclerAdapter<Matashiya, MatashiyaViewHolder> mAdpater;
    RecyclerView mRecycler;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MatashiyaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MatashiyaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatashiyaFragment newInstance(String param1, String param2) {
        MatashiyaFragment fragment = new MatashiyaFragment();
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
        View mView = inflater.inflate(R.layout.fragment_matashiya, container, false);
        mFirebase = FirebaseDatabase.getInstance();
        mFirebaseRefData = mFirebase.getReference().child("matashiya");
        mRecycler = (RecyclerView) mView.findViewById(R.id.matashiyaRecycler);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdpater = new FirebaseRecyclerAdapter<Matashiya, MatashiyaViewHolder>
                (Matashiya.class,R.layout.matashiya, MatashiyaViewHolder.class, mFirebaseRefData) {
            @Override
            protected void populateViewHolder(MatashiyaViewHolder viewHolder, Matashiya model, int position) {
                viewHolder.heading.setText(model.getHeading());
                viewHolder.body.setText(model.getBody());
            }

        };

        mRecycler.setAdapter(mAdpater);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public static class MatashiyaViewHolder extends RecyclerView.ViewHolder {
        TextView heading;
        ExpandableTextView body;
        public MatashiyaViewHolder(View itemView) {
            super(itemView);
            heading = (TextView) itemView.findViewById(R.id.heading);
            // sample code snippet to set the text content on the ExpandableTextView
            body = (ExpandableTextView) itemView.findViewById(R.id.sample)
                    .findViewById(R.id.expand_text_view);
        }

    }
}