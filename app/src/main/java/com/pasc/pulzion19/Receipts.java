package com.pasc.pulzion19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Receipts extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;
    FirebaseFirestore db,db1;
    ArrayList model_classList;

    LinearLayout l;
    Toolbar toolbar=null;
    FloatingActionButton fab_plus,fab_QR,fab_manually;
    Animation Fabopen,Fabclose,Fabrclockwise,FabRanticlockwise;
    TextView fab_QR_tV, fab_manually_tV;

    Boolean isOpen = false;
    adapter adapter = new adapter(model_classList);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    //test


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipts);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_plus=(FloatingActionButton)findViewById(R.id.fab_plus);
        fab_QR=(FloatingActionButton)findViewById(R.id.fab_QR);
        fab_manually=(FloatingActionButton)findViewById(R.id.fab_manual);

        fab_QR_tV = (TextView) findViewById(R.id.fab_QR_tV);
        fab_manually_tV = (TextView) findViewById(R.id.fab_manual_tV);

        Fabopen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        Fabclose= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        Fabrclockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);


        recyclerView = findViewById(R.id.recycler_view);


        loadData();
        buildRecyclerView();
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);



        final Intent intent = getIntent();
        if (intent != null) {
            final String data = intent.getStringExtra("my_receipt");
            //Temp
            final String[] name = new String[1];
            final String[] contact = new String[1];
            final String[] mail = new String[1];
            final String[] cost = new String[1];
            db = FirebaseFirestore.getInstance();
            db.collection("Combined").get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (!queryDocumentSnapshots.isEmpty()) {
                                int temp = 0;
                                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                for (DocumentSnapshot d : list) {

                                    // User p=d.toObject(User.class);
                                    String get_id = data;
                                    String f_id = d.getString("id");

                                    if (data != null && data.equals(f_id)) {

                                        name[0] = d.getString("participant1");
                                        contact[0] = f_id;
                                        cost[0] = String.valueOf(d.get("cost"));
                                        mail[0] = intent.getStringExtra("eventName");
                                        model_classList.add(new model_class(name[0], contact[0], cost[0], mail[0]));

                                        recyclerView.setAdapter(adapter);
                                        adapter.notifyDataSetChanged();
                                        temp = 1;
                                        saveData();
                                        break;
                                    }
                                }
                            }

                        }
                    });

        }


        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOpen){
                    fab_QR.startAnimation(Fabclose);
                    fab_QR_tV.startAnimation(Fabclose);

                    fab_manually.startAnimation(Fabclose);
                    fab_manually_tV.startAnimation(Fabclose);
                    fab_plus.startAnimation(FabRanticlockwise);
                    fab_QR.setClickable(false);
                    fab_manually.setClickable(false);
                    isOpen=false;
                }
                else{
                    fab_QR.startAnimation(Fabopen);
                    fab_manually.startAnimation(Fabopen);
                    fab_QR_tV.startAnimation(Fabopen);
                    fab_manually_tV.startAnimation(Fabopen);
                    fab_plus.startAnimation(Fabrclockwise);
                    fab_QR.setClickable(true);
                    fab_manually.setClickable(true);
                    isOpen=true;
                }
            }
        });

        DrawerLayout drawer = this.findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


        fab_QR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(Receipts.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();
            }
        });

        fab_manually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(Receipts.this);
                final View mView = getLayoutInflater().inflate(R.layout.ticket_item, null);
                mAlertDialog.setView(mView);
                Button verify = mView.findViewById(R.id.addTicket);
                final EditText password = mView.findViewById(R.id.etId);
                TextView textView = mView.findViewById(R.id.textView);
                //               textView.setText("To Pay:- "+amount);
                final AlertDialog dialog = mAlertDialog.create();
                dialog.show();

                final String[] name = new String[1];
                final String[] contact = new String[1];
                final String[] mail = new String[1];
                final String[] cost = new String[1];

                verify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        db=FirebaseFirestore.getInstance();
                        db.collection("Combined").get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        if(!queryDocumentSnapshots.isEmpty())
                                        {
                                            int temp=0;
                                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                            for(DocumentSnapshot d:list)
                                            {

                                                // User p=d.toObject(User.class);
                                                String get_id=password.getText().toString();
                                                String f_id =d.getString("id");


                                                if(get_id.equals(f_id))
                                                {
                                                    dialog.dismiss();
                                                    name[0] =d.getString("participant1");
                                                    contact[0] = d.getString("id");
                                                    cost[0] =String.valueOf(d.get("cost"));
                                                    model_classList.add(new model_class(name[0],contact[0],cost[0],mail[0]));

                                                    recyclerView.setAdapter(adapter);
                                                    adapter.notifyDataSetChanged();
                                                    temp=1;
                                                    saveData();
                                                    break;

                                                }


                                            }
                                            if(temp==0)
                                            {
                                                Toast.makeText(Receipts.this, "Incorrect ID", Toast.LENGTH_SHORT).show();
                                            }

                                        }

                                    }
                                });

                    }
                });
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(model_classList);
        editor.putString("task list", json);
        editor.apply();
    }

    void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared_preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<model_class>>() {
        }.getType();
        model_classList = gson.fromJson(json, type);

        if (model_classList == null) {
            model_classList = new ArrayList<>();
        }

    }

    private void buildRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new adapter(model_classList);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        final IntentResult result =IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()==null)
            {
                Toast.makeText(this, "Scan Cancelled", Toast.LENGTH_SHORT).show();
            }
            else
            {
                recyclerView=findViewById(R.id.recycler_view);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

                recyclerView.setLayoutManager(linearLayoutManager);

                final String[] name1 = new String[1];
                final String[] contact1 = new String[1];
                final String[] mail1 = new String[1];
                final String[] cost1 = new String[1];
                String res= result.getContents();
                final boolean[] flagEvent = {false};
                db1=FirebaseFirestore.getInstance();
                db1.collection("Combined").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                if(!queryDocumentSnapshots.isEmpty())
                                {
                                    int temp1=0;
                                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                                    for(DocumentSnapshot d1:list)
                                    {
                                        String res= result.getContents();
                                        String f_id1 =d1.getString("id");
                                        if(res.equals(f_id1))
                                        {
                                            flagEvent[0] = true;
                                            //l.setVisibility(View.VISIBLE);
                                            name1[0] =d1.getString("participant1");
                                            contact1[0] = d1.getString("id");
                                            cost1[0] =String.valueOf(d1.get("cost"));
                                            mail1[0] = d1.get("events").toString().replaceAll("_", " ");
                                         model_classList.add(new model_class(name1[0],contact1[0],cost1[0],mail1[0]));

                                            recyclerView.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();
                                            temp1=1;
                                            saveData();
                                            break;

                                        }
                                    }
                                    if(temp1==0)
                                    {
                                        Toast.makeText(Receipts.this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                    db1 = FirebaseFirestore.getInstance();
                    db1.collection("Workshop").get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    if (!queryDocumentSnapshots.isEmpty()) {
                                        int temp1 = 0;
                                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                                        for (DocumentSnapshot d1 : list) {
                                            String res = result.getContents();
                                            String f_id1 = d1.getString("id");
                                            if (res.equals(f_id1)) {
                                                //                 flagEvent=true;
                                                //l.setVisibility(View.VISIBLE);
                                                name1[0] = d1.getString("participant1");
                                                contact1[0] = d1.getString("id");
                                                cost1[0] = String.valueOf((long) d1.get("cost") - (long) d1.get("pending"));
                                                mail1[0] = d1.get("events").toString().replaceAll("_", " ");
                                                model_classList.add(new model_class(name1[0], contact1[0], cost1[0], mail1[0]));

                                                recyclerView.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                                temp1 = 1;
                                                saveData();
                                                break;

                                            }
                                        }
                                        if (temp1 == 0) {
                                            Toast.makeText(Receipts.this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });


//                }
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(Receipts.this, MainActivity.class);
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
                Intent intent1 = new Intent(Receipts.this, BugReport.class);
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
                Intent i = new Intent(Receipts.this, MainActivity.class);
                startActivity(i);
                break;
            case R.id.nav_register:
                Intent h2 = new Intent(Receipts.this, EventRegistrationActivity.class);
                startActivity(h2);
                break;
            case R.id.nav_receipts:
                Intent h = new Intent(Receipts.this, Receipts.class);
                startActivity(h);
                break;
            case R.id.nav_events:
                Intent h1 = new Intent(Receipts.this, Events.class);
                startActivity(h1);
                break;
            case R.id.workshops:
                Intent i1 = new Intent(Receipts.this, Workshops.class);
                startActivity(i1);
                break;
            case R.id.nav_sponsors:
                Intent g = new Intent(Receipts.this, Sponsors.class);
                startActivity(g);
                break;
            case R.id.nav_aboutus:
                Intent s = new Intent(Receipts.this, AboutUs.class);
                startActivity(s);
                break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}