package com.pasc.pulzion19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class NonTechEvents extends Fragment {

    CardView cardDextrous, cardCerebro, cardFandom, cardQuizToBid, cardPhotoshopRoyale, cardInsight;

    String con1,con2;

    LinearLayout linearLayout;
    ImageView idextrous, icerebro, ifandom, iquiz2bid, iphotoshop, iInsight;


    FirebaseFirestore db;

    String cons;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_non_tech,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        linearLayout=getActivity().findViewById(R.id.layout);
        db = FirebaseFirestore.getInstance();
        cardDextrous = getActivity().findViewById(R.id.dextrous);
        cardCerebro = getActivity().findViewById(R.id.cerebro);
        cardFandom = getActivity().findViewById(R.id.fandom);
        cardQuizToBid = getActivity().findViewById(R.id.quiztobid);
        cardPhotoshopRoyale = getActivity().findViewById(R.id.psroyale);
        cardInsight = getActivity().findViewById(R.id.insight);
        idextrous = getActivity().findViewById(R.id.idextrous);
        iInsight = getActivity().findViewById(R.id.iinsight);
        icerebro = getActivity().findViewById(R.id.icerebro);
        ifandom = getActivity().findViewById(R.id.ifandom);
        iquiz2bid = getActivity().findViewById(R.id.iquiztobid);
        iphotoshop = getActivity().findViewById(R.id.ipsroyale);


        cardDextrous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact;
                final String[] event_name = new String[1];
                final String[] event_description = new String[1];
                final String[] fees = new String[1];
                final String[] quote = new String[1];
                final String[] rule_description = new String[1];
                final String[] team = new String[1];
                //final String team_distribution;

                db.collection("Event_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("dextrous")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            rule_description[0] = d1.getString("rule_description");
                                            team[0] = d1.getString("team_distribution");
                                            //cons=d1.getString("contacts");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");


                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_dextrous,con1,con2);

                                            Intent intent = new Intent(getActivity(), EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS", eventDetails);
                                            startActivity(intent);


                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

            }
        });

        cardCerebro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact;
                final String[] event_name = new String[1];
                final String[] event_description = new String[1];
                final String[] fees = new String[1];
                final String[] quote = new String[1];
                final String[] rule_description = new String[1];
                final String[] team = new String[1];
                //final String team_distribution;

                db.collection("Event_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("cerebro")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            rule_description[0] = d1.getString("rule_description");
                                            team[0] = d1.getString("team_distribution");
//                                            cons=d1.getString("contacts");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");
                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_cerebro,con1,con2);

                                            Intent intent = new Intent(getActivity(), EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS", eventDetails);
                                            startActivity(intent);


                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        cardFandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact;
                final String[] event_name = new String[1];
                final String[] event_description = new String[1];
                final String[] fees = new String[1];
                final String[] quote = new String[1];
                final String[] rule_description = new String[1];
                final String[] team = new String[1];
                //final String team_distribution;

                db.collection("Event_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("fandom_quiz")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            rule_description[0] = d1.getString("rule_description");
                                            team[0] = d1.getString("team_distribution");
//                                            cons=d1.getString("contacts");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");

                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_fandomquiz,con1,con2);

                                            Intent intent = new Intent(getActivity(), EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS", eventDetails);
                                            startActivity(intent);


                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        cardPhotoshopRoyale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact;
                final String[] event_name = new String[1];
                final String[] event_description = new String[1];
                final String[] fees = new String[1];
                final String[] quote = new String[1];
                final String[] rule_description = new String[1];
                final String[] team = new String[1];
                //final String team_distribution;

                db.collection("Event_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("photoshop_royale")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            rule_description[0] = d1.getString("rule_description");
                                            team[0] = d1.getString("team_distribution");
//                                            cons=d1.getString("contacts");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");

                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_psroyale,con1,con2);

                                            Intent intent = new Intent(getActivity(), EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS", eventDetails);
                                            startActivity(intent);


                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });

        cardQuizToBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String contact;
                final String[] event_name = new String[1];
                final String[] event_description = new String[1];
                final String[] fees = new String[1];
                final String[] quote = new String[1];
                final String[] rule_description = new String[1];
                final String[] team = new String[1];
                //final String team_distribution;

                db.collection("Event_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("quiztobid")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            rule_description[0] = d1.getString("rule_description");
                                            team[0] = d1.getString("team_distribution");
//                                            cons=d1.getString("contacts");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");

                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_quiz_to_bid,con1,con2);
                                            Intent intent = new Intent(getActivity(), EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS", eventDetails);
                                            startActivity(intent);


                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        cardInsight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String contact;
                final String[] event_name = new String[1];
                final String[] event_description = new String[1];
                final String[] fees = new String[1];
                final String[] quote = new String[1];
                final String[] rule_description = new String[1];
                final String[] team = new String[1];
                //final String team_distribution;

                db.collection("Event_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("insight")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            rule_description[0] = d1.getString("rule_description");
                                            team[0] = d1.getString("team_distribution");
                                            //cons=d1.getString("contacts");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");


                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_insight,con1,con2);
                                            Intent intent = new Intent(getActivity(), EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS", eventDetails);
                                            startActivity(intent);


                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

            }
        });


    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(R.anim.slidingleft, enter, R.anim.slidingright);
    }


}