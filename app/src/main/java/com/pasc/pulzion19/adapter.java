package com.pasc.pulzion19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.Viewholder> {

    private ArrayList<model_class> model_classList;
    private TextView name;
    private TextView contact;
    private TextView id;
    private TextView amt_paid;

    public adapter(ArrayList<model_class> model_classList) {
        this.model_classList = model_classList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_receipt, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        String name=model_classList.get(position).getName();
        String contact=model_classList.get(position).getContact();
        String id=model_classList.get(position).getId();
        String amt_paid=model_classList.get(position).getAmt_paid();
        holder.setData(name,contact,id,amt_paid);
    }

    @Override
    public int getItemCount() {
        return model_classList.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        public Viewholder(@NonNull View itemView) {
            super(itemView);


            name = itemView.findViewById(R.id.textView6);
            contact = itemView.findViewById(R.id.textView5);
            id = itemView.findViewById(R.id.textView8);
            amt_paid = itemView.findViewById(R.id.textView10);
        }

        private void setData(String name1,String contact1,String id1,String amt_paid1){
            name.setText(name1);
            contact.setText(contact1);
            id.setText(id1);
            amt_paid.setText(amt_paid1);
        }
    }


}