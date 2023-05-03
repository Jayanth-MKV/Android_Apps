package com.vugido.vugidoccs.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vugido.vugidoccs.R;
import com.vugido.vugidoccs.models.InActiveUsers.DATAItem;

import java.util.List;

public class InActiveUsersAdapter extends RecyclerView.Adapter<InActiveUsersAdapter.MyViewHolder> {

    private Context context;
    private List<DATAItem> dataItemList;
    String from;
   // private MarkUsers markUsers;

    public InActiveUsersAdapter(Context context, List<DATAItem> dataItemList,String from) {
        this.context = context;
        this.dataItemList = dataItemList;
        this.from=from;
       // markUsers= (MarkUsers) context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.in_active_user_item_design,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final DATAItem dataItem=dataItemList.get(position);

        holder.UID.setText("UID : "+dataItem.getUSERID());
        holder.LastOid.setText("Last OID : "+dataItem.getORDERID());
        holder.DateTime.setText("Date Time : "+dataItem.getDATE()+","+dataItem.getTIME());
        holder.WhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   String text="హలో,"+dataItem.getUSERNAME().toUpperCase()+" \uD83D\uDC81\u200D♂\nమీరు మన  VUGIDO(వుగిడో) నుంచి ఆర్డర్ చేసి "+from+" రోజులు అవుతుంది. మీ వైపు నుండి ఎలాంటి సమస్యలు వున్న  మాకు తెలియజేయగలరు, తద్వారా మేము సరిదిద్దగలము మరియు మేము పరిష్కారాలతో మీ వద్దకు వస్తాము \uD83D\uDE0A.\n*********\nనాణ్యతలో లోపంగాని ,డెలివరీ లేటుగా రావడంగాని ,మీ అభిప్రాయాలను /సమస్యలను ఈ  మెసేజ్ ద్వారా మాకు తెలియజేయగలరు\uD83D\uDE0A\n**********\nమన App Download Link :\nhttp://play.google.com/store/apps/details?id=com.dailyneeds.vugido\n*********\nధన్యవాదాలు\uD83D\uDE4F\uD83C\uDFFD\n---Team VUGIDO";
                  /*  String text = "Dear "+usersItem.getUSERNAME().toUpperCase()+"\nFrom Vugido Dailyneeds , \n" +
                            "Now Order Online Vegetableswith less price and good quality\n"+"* Save Time\n" +
                            "* Save Money\n" +
                            "* Good Quality\n" +
                            "* Customer Satisfaction\n" +
                            "* On Time Delivery\n" +
                            "Visit Our App:\n"+"https://play.google.com/store/apps/details?id=com.dailyneeds.vugido\n"+
                            "Try Once !";*/

                    //  String toNumber = "919381776137"; // Replace with mobile phone number without +Sign or leading zeros, but with country code
                    //Suppose your country is India and your phone number is “xxxxxxxxxx”, then you need to send “91xxxxxxxxxx”.


                    Toast.makeText(context,text,Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"91"+dataItem.getPHONE() +"&text="+text));
                    context.startActivity(i);
                }
                catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        holder.Mark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //
                //markUsers.markUser(dataItem.getUSERID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        Button WhatsApp,Mark;
        TextView UID,LastOid,DateTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            UID=itemView.findViewById(R.id.product_title);
            LastOid=itemView.findViewById(R.id.product_quantity);
            DateTime=itemView.findViewById(R.id.product_price);
            WhatsApp=itemView.findViewById(R.id.button3);
            Mark=itemView.findViewById(R.id.button);
        }
    }

    public interface MarkUsers{

        void markUser(int uid);
    }
}
