package com.pasc.pulzion19;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class Workshops extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseFirestore db;
    CardView hack, ai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshops);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        db = FirebaseFirestore.getInstance();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        hack = findViewById(R.id.cv_hack);
        ai = findViewById(R.id.cv_ai);
        hack.setOnClickListener(new View.OnClickListener() {
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

                db.collection("Workshop_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("EthicalHacking")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            String con1 = d1.getString("contact1");
                                            String con2 = d1.getString("contact2");


                                            WorkshopSnapshot eventDetails = new WorkshopSnapshot(event_name[0], quote[0], event_description[0], fees[0], R.drawable.hacking, con1, con2);
                                            Intent intent = new Intent(Workshops.this, WorkshopDetails.class);
                                            intent.putExtra("WORKSHOP_DETAILS", eventDetails);
                                            startActivity(intent);
                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(Workshops.this, "Load Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }


                            }
                        });


            }
        });


        ai.setOnClickListener(new View.OnClickListener() {
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

                db.collection("Workshop_Details").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if (!queryDocumentSnapshots.isEmpty()) {
                                    int temp1 = 0;
                                    List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                    for (DocumentSnapshot d1 : list) {
                                        if (d1.getId().equals("AI")) {
                                            temp1 = 1;
                                            event_name[0] = d1.getString("name");
                                            event_description[0] = d1.getString("event_description");
                                            fees[0] = d1.getString("fees");
                                            quote[0] = d1.getString("quote");
                                            String con1 = d1.getString("contact1");
                                            String con2 = d1.getString("contact2");


                                            WorkshopSnapshot eventDetails = new WorkshopSnapshot(event_name[0], quote[0], event_description[0], fees[0], R.drawable.ai, con1, con2);
                                            Intent intent = new Intent(Workshops.this, WorkshopDetails.class);
                                            intent.putExtra("WORKSHOP_DETAILS", eventDetails);
                                            startActivity(intent);
                                            break;
                                        } else
                                            temp1 = 0;
                                    }
                                    if (temp1 == 0) {
                                        Toast.makeText(Workshops.this, "Load Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }


                            }
                        });


            }
        });


    }



    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Workshops.this, MainActivity.class);
        startActivity(intent1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.bug_report:
                Intent intent1 = new Intent(Workshops.this, BugReport.class);
                startActivity(intent1);
                return true;
            case R.id.share1:
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String link = "https://play.google.com/store/apps/details?id=com.pasc.pulzion19";
                intent.putExtra(android.content.Intent.EXTRA_TEXT, link);
                startActivity(Intent.createChooser(intent, "Share via"));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.Home:
                Intent i = new Intent(Workshops.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_register:
                Intent h2 = new Intent(Workshops.this, EventRegistrationActivity.class);
                startActivity(h2);
                break;
            case R.id.nav_receipts:
                Intent h = new Intent(Workshops.this, Receipts.class);
                startActivity(h);
                break;
            case R.id.nav_events:
                Intent h1 = new Intent(Workshops.this, Events.class);
                startActivity(h1);
                break;
            case R.id.workshops:
                Intent i1 = new Intent(Workshops.this, Workshops.class);
                startActivity(i1);
                break;
            case R.id.nav_sponsors:
                Intent g = new Intent(Workshops.this, Sponsors.class);
                startActivity(g);
                break;
            case R.id.nav_aboutus:
                Intent s = new Intent(Workshops.this, AboutUs.class);
                startActivity(s);
                break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
