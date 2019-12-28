package com.pasc.pulzion19;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class WorkshopDetails extends AppCompatActivity implements Serializable, NavigationView.OnNavigationItemSelectedListener {

    Map<String, String> map;
    ArrayList<String> arrayList;

    TextView event_name, event_description, quote, event_rules, team_distribution, fees, contact1, contact2;
    ImageView imageView;
    Bundle bundle;
    MaterialButton RegisterButton;


    StringBuilder final1 = new StringBuilder(" "), final2 = new StringBuilder(" ");
    StringBuilder temp1 = new StringBuilder(" "), temp2 = new StringBuilder(" ");
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RegisterButton = findViewById(R.id.RegisterButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EventRegistrationActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        WorkshopSnapshot temp = (WorkshopSnapshot) getIntent().getSerializableExtra("WORKSHOP_DETAILS");
        event_name = findViewById(R.id.eventName);

        if (temp.getImageView() != 0)
            temp.setImageView(temp.getImageView());
        event_description = findViewById(R.id.eventDescription);
        quote = findViewById(R.id.textView1);
        fees = findViewById(R.id.fees);
        contact1 = findViewById(R.id.contact1);
        contact2 = findViewById(R.id.contact2);
        imageView = findViewById(R.id.eventImage);
        Glide.with(this).load(getResources().getDrawable(temp.getImageView())).into(imageView);
        //imageView.setImageResource(temp.getImageView());
        event_name.setText(temp.getEvent_name());
        String s1;
        if (temp.getEvent_name().equals("Cerebro"))
            s1 = temp.getEvent_name_description();
        else {
            s1 = temp.getEvent_name_description();
            //s2.replaceAll("\\n", "\n");
            if (s1 != null)
                if (s1.contains("\\n")) {
                    s1 = s1.replace("\\n", "\n");
                }
        }
        event_description.setText(s1);
        quote.setText(temp.getEvent_name_quote());
        String s2;
        if (temp.getEvent_name().equals("Cerebro"))
            s2 = temp.getEvent_name_description();
        else {
            s2 = temp.getEvent_rules_description();
            //s2.replaceAll("\\n", "\n");
            if (s2 != null)
                if (s2.contains("\\n")) {
                    s2 = s2.replace("\\n", "\n");
                }
        }


        fees.setText(temp.getEvent_fees());
        String con1 = "", con2 = "";
        int n1, n2;
        int cursor1 = 0, cursor2 = 0;
        n1 = temp.contact1.length();
        n2 = temp.contact2.length();
        for (int i = 0; i < n1; i++) {
            if (temp.contact1.charAt(i) == ' ' && temp.contact1.charAt(i - 1) == ':') {
                cursor1 = i + 1;
                break;
            } else
                temp1.append(temp.contact1.charAt(i));
        }

        for (int i = 0; i < n2; i++) {
            if (temp.contact2.charAt(i) == ' ' && temp.contact2.charAt(i - 1) == ':') {
                cursor2 = i + 1;
                break;
            } else
                temp2.append(temp.contact2.charAt(i));

        }

        for (int i = cursor1; i < n1; i++)
            final1.append(temp.contact1.charAt(i));
        for (int i = cursor2; i < n2; i++)
            final2.append(temp.contact2.charAt(i));

        //TextView textView = (TextView) view.findViewById(R.id.textview);
        SpannableString content = new SpannableString(final1);
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        String str1 = temp1.toString() + content;
        contact1.setText(str1);

        SpannableString content2 = new SpannableString(final2);
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        String str2 = temp2.toString() + content2;
        contact2.setText(str2);


        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + final1.toString().trim()));
                startActivity(intent);
            }
        });

        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + final2.toString().trim()));
                startActivity(intent);
            }
        });

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id) {
            case R.id.Home:
                Intent i = new Intent(WorkshopDetails.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_register:
                Intent h2 = new Intent(WorkshopDetails.this, EventRegistrationActivity.class);
                startActivity(h2);
                break;
            case R.id.nav_receipts:
                Intent h = new Intent(WorkshopDetails.this, Receipts.class);
                startActivity(h);
                break;
            case R.id.nav_events:
                Intent h1 = new Intent(WorkshopDetails.this, Events.class);
                startActivity(h1);
                break;
            case R.id.workshops:
                Intent i1 = new Intent(WorkshopDetails.this, Workshops.class);
                startActivity(i1);
                break;
            case R.id.nav_sponsors:
                Intent g = new Intent(WorkshopDetails.this, Sponsors.class);
                startActivity(g);
                break;
            case R.id.nav_aboutus:
                Intent s = new Intent(WorkshopDetails.this, AboutUs.class);
                startActivity(s);
                break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(WorkshopDetails.this, Workshops.class);
        startActivity(intent1);
    }
}
