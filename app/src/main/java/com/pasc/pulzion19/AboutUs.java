package com.pasc.pulzion19;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AboutUs extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseFirestore db;
    StringBuilder final1=new StringBuilder(" "),final2=new StringBuilder(" ");
    StringBuilder temp3=new StringBuilder(" "),temp2=new StringBuilder(" ");
    TextView eventDetails,contact1,contact2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        eventDetails=findViewById(R)
        db=FirebaseFirestore.getInstance();

        eventDetails=findViewById(R.id.eventDetails);
        contact1=findViewById(R.id.textView1);
        contact2=findViewById(R.id.textView2);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        final String[] details = new String[1];
        final String[] con1 = new String[1];
        final String[] con2 = new String[1];


        db.collection("Event_Details").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            int temp1 = 0;
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d1 : list) {
                                if (d1.getId().equals("about_us")) {
                                    temp1 = 1;
                                    details[0] =d1.getString("details");
                                    con1[0] =d1.getString("contact1");
                                    con2[0] =d1.getString("contact2");

                                    String s1=details[0];
                                    if(s1.contains("\\n"))
                                    {
                                        s1=s1.replace("\\n","\n");
                                    }
                                    eventDetails.setText(s1);
                                    //Test
                                    int n1,n2;
                                    int cursor1=0,cursor2=0;
                                    n1=con1[0].length();
                                    n2=con2[0].length();
                                    for(int i=0;i<n1;i++)
                                    {
                                        if(con1[0].charAt(i)==' '&&con1[0].charAt(i-1)==':') {
                                            cursor1 = i + 1;
                                            break;
                                        }
                                        else
                                            temp3.append(con1[0].charAt(i));
                                    }

                                    for(int i=0;i<n2;i++)
                                    {
                                        if(con2[0].charAt(i)==' '&&con2[0].charAt(i-1)==':') {
                                            cursor2 = i + 1;
                                            break;
                                        }
                                        else
                                            temp2.append(con2[0].charAt(i));

                                    }

                                    for(int i=cursor1;i<n1;i++)
                                        final1.append(con1[0].charAt(i));
                                    for(int i=cursor2;i<n2;i++)
                                        final2.append(con2[0].charAt(i));

                                    //TextView textView = (TextView) view.findViewById(R.id.textview);
                                    SpannableString content = new SpannableString(final1);
                                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                                    String str1=temp3.toString()+content;
                                    contact1.setText(str1);

                                    SpannableString content2 = new SpannableString(final2);
                                    content2.setSpan(new UnderlineSpan(), 0, content2.length(),0 );
                                    String str2=temp2.toString()+content2;
                                    contact2.setText(str2);

                                    contact1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent=new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:"+final1.toString().trim()));
                                            startActivity(intent);
                                        }
                                    });

                                    contact2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent=new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:"+final2.toString().trim()));
                                            startActivity(intent);
                                        }
                                    });



                                    break;
                                } else
                                    temp1 = 0;
                            }
                            if (temp1 == 0) {
                                Toast.makeText(AboutUs.this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
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
                Intent intent1 = new Intent(AboutUs.this, BugReport.class);
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
                Intent i = new Intent(AboutUs.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_register:
                Intent h2 = new Intent(AboutUs.this, EventRegistrationActivity.class);
                startActivity(h2);
                break;
            case R.id.nav_receipts:
                Intent h = new Intent(AboutUs.this, Receipts.class);
                startActivity(h);
                break;
            case R.id.nav_events:
                Intent h1 = new Intent(AboutUs.this, Events.class);
                startActivity(h1);
                break;
            case R.id.workshops:
                Intent i1 = new Intent(AboutUs.this, Workshops.class);
                startActivity(i1);
                break;
            case R.id.nav_sponsors:
                Intent g = new Intent(AboutUs.this, Sponsors.class);
                startActivity(g);
                break;
            case R.id.nav_aboutus:
                Intent s = new Intent(AboutUs.this, AboutUs.class);
                startActivity(s);
                break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
