package com.pasc.pulzion19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class TechEvents extends Fragment implements Serializable {

    ArrayList<String> contactList;
    Map<String,String> map;
    LinearLayout linearLayout;
    ImageView ibugoff,icodebuddy,ijustcoding,irecodeit,idataquest,iwebandappdev,ielectroquest;
    FirebaseFirestore db;
    //CollectionReference Event_Details;
    CardView cardViewBugoff,cardViewCodeBuddy,cardJustCoding,cardRecodeIt,cardDataQuest,cardWebAppDev,cardElectroQuest;
    String con1,con2;


    androidx.appcompat.widget.Toolbar toolbar=null;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tech,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        linearLayout= Objects.requireNonNull(getActivity()).findViewById(R.id.layout);
        this.setEnterTransition(R.anim.slidingleft);
        this.setExitTransition(R.anim.slidingright);
        this.getEnterTransition();
        ibugoff=getActivity().findViewById(R.id.ibugoff);
        icodebuddy=getActivity().findViewById(R.id.icodebuddy);
        irecodeit=getActivity().findViewById(R.id.irecodeit);
        ijustcoding=getActivity().findViewById(R.id.ijustcoding);
        idataquest=getActivity().findViewById(R.id.idataquest);
        ielectroquest=getActivity().findViewById(R.id.ielectroquest);
        iwebandappdev=getActivity().findViewById(R.id.iwebandappdev);


        db= FirebaseFirestore.getInstance();
        //Event_Details=db.collection("Event_Details");
        cardViewBugoff = getActivity().findViewById(R.id.bugoff);
        cardViewCodeBuddy=getActivity().findViewById(R.id.codeBuddy);
        cardJustCoding=getActivity().findViewById(R.id.just_coding);
        cardRecodeIt=getActivity().findViewById(R.id.recode_it);
        cardDataQuest=getActivity().findViewById(R.id.dataquest);
        cardWebAppDev = getActivity().findViewById(R.id.webandappdev);
        cardElectroQuest=getActivity().findViewById(R.id.electroquest);

        cardViewBugoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String contact;
                //startActivity(new Intent(getActivity(),StartActivity.class));
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
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("bugoff"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            //map= (Map<String, String>) d1.get("contact");
                                            //contactList=d1.getString(contactname);
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");




                                            EventSnapshot eventDetails=new EventSnapshot(event_name[0],quote[0],event_description[0],rule_description[0],team[0],fees[0],R.drawable.ev_bugoff,con1,con2);
                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);
                                            startActivity(intent);
//                                            ObjectAnimator anim = ObjectAnimator.ofFloat(ibugoff, "ScaleY", 1, 0);
//                                            anim.setDuration(2000);
//                                            anim.start();





                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        cardViewCodeBuddy.setOnClickListener(new View.OnClickListener() {
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
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("code_buddy"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");


                                            EventSnapshot eventDetails=new EventSnapshot(event_name[0],quote[0],event_description[0],rule_description[0],team[0],fees[0],R.drawable.ev_codebuddy,con1,con2);
                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);
                                            startActivity(intent);





                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });

            }
        });
        cardJustCoding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
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

                            //ActivityOptionsCompat optionsCompat = (ActivityOptionsCompat) ActivityOptionsCompat.makeSceneTransitionAnimation(Objects.requireNonNull(getActivity()), view, Objects.requireNonNull(ViewCompat.getTransitionName(view)));
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("just_coding"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");


                                            EventSnapshot eventDetails=new EventSnapshot(event_name[0],quote[0],event_description[0],rule_description[0],team[0],fees[0],R.drawable.ev_justcoding,con1,con2);


                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);

                                            startActivity(intent);
                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        cardRecodeIt.setOnClickListener(new View.OnClickListener() {
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
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("recodeit"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");


                                            EventSnapshot eventDetails = new EventSnapshot(event_name[0], quote[0], event_description[0], rule_description[0], team[0], fees[0], R.drawable.ev_recodeit, con1, con2);
                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);
                                            startActivity(intent);





                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        cardDataQuest.setOnClickListener(new View.OnClickListener() {
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
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("dataquest"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");


                                            EventSnapshot eventDetails=new EventSnapshot(event_name[0],quote[0],event_description[0],rule_description[0],team[0],fees[0],R.drawable.ev_dataquest,con1,con2);


                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);
                                            startActivity(intent);





                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        cardWebAppDev.setOnClickListener(new View.OnClickListener() {
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
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("webappdev"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");

                                            EventSnapshot eventDetails=new EventSnapshot(event_name[0],quote[0],event_description[0],rule_description[0],team[0],fees[0],R.drawable.ev_webandroid,con1,con2);
                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);
                                            startActivity(intent);





                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
        cardElectroQuest.setOnClickListener(new View.OnClickListener() {
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
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        if(d1.getId().equals("electroquest"))
                                        {
                                            temp1=1;
                                            event_name[0] =d1.getString("name");
                                            event_description[0] =d1.getString("event_description");
                                            fees[0] =d1.getString("fees");
                                            quote[0] =d1.getString("quote");
                                            rule_description[0] =d1.getString("rule_description");
                                            team[0] =d1.getString("team_distribution");
                                            con1=d1.getString("contact1");
                                            con2=d1.getString("contact2");

                                            EventSnapshot eventDetails=new EventSnapshot(event_name[0],quote[0],event_description[0],rule_description[0],team[0],fees[0],R.drawable.ev_electroquest,con1,con2);
                                            Intent intent=new Intent(getActivity(),EventDetails.class);
                                            intent.putExtra("EVENT_DETAILS",eventDetails);
                                            startActivity(intent);





                                            break;
                                        }
                                        else
                                            temp1=0;
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(getActivity(), "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}