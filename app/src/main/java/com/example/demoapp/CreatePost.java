package com.example.demoapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreatePost.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreatePost#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreatePost extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText editTextCreatePost;
    TextView usernameCreatePost;
    ImageView profilePicCreatePost;
    Button postButtonCreatePost;
    FirebaseDatabase database;
    DatabaseReference ref;
    Profile profile;
    Uri imgPath=Uri.parse("android.resource://com.example.demoapp/"+R.drawable.pic);

    private OnFragmentInteractionListener mListener;

    public CreatePost() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreatePost.
     */
    // TODO: Rename and change types and number of parameters
    public static CreatePost newInstance(String param1, String param2) {
        CreatePost fragment = new CreatePost();
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
        View view=inflater.inflate(R.layout.fragment_create_post, container, false);
        editTextCreatePost=(EditText)view.findViewById(R.id.editext_createpost);
        usernameCreatePost=(TextView)view.findViewById(R.id.username_createpost);
        profilePicCreatePost=(ImageView)view.findViewById(R.id.profile_picture_createpost);
        postButtonCreatePost=(Button)view.findViewById(R.id.post_button_createpost);
        postButtonCreatePost.setOnClickListener(this);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            usernameCreatePost.setText(acct.getDisplayName());
            imgPath=acct.getPhotoUrl();
            profilePicCreatePost.setImageURI(imgPath);
        }

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("Profiles");
        profile=new Profile();
        return view;
    }

    private void getValues(){
        profile.setContent(editTextCreatePost.getText().toString());
        profile.setUsername(usernameCreatePost.getText().toString());
        profile.setProfilepic(imgPath.toString());
    }

    @Override
    public void onClick(View v) {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getValues();
                ref.child("User1").setValue(profile);
                Toast.makeText(getContext(),"Posted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
}
