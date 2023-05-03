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
import com.vugido.vugidoccs.models.OrderCountUsers.DATAItem;

import java.util.List;

public class OrderCountUsersAdapter extends RecyclerView.Adapter<OrderCountUsersAdapter.MyViewHolder> {

    List<DATAItem> dataItemList;
    private Context context;

    public OrderCountUsersAdapter(List<DATAItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_count_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final DATAItem dataItem=dataItemList.get(position);

        holder.UserName.setText(dataItem.getUSERNAME().toUpperCase()+" ("+dataItem.getUID()+")");
        holder.WhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    //String text="నమస్కారం \""+dataItem.getUSERNAME().toUpperCase() +" \"గారు\uD83D\uDE4F\uD83C\uDFFD\n"+" మన వూగిడో(ఆన్\u200Cలైన్ కూరగాయలు, కిరాణా మరియు పండ్ల )యాప్ ఇన్\u200Cస్టాల్ చేసిన తర్వాత మీరు కనీసం ఒక్కసారైనా ఆర్డర్ చేయలేద\u200C\u200Cని మేము గమనించాము. \n"+"మీకు ఏ విధమైన సమస్యలు అనగా, కావలసిన ఉత్పత్తులు లేకపోవడం , లొకేషన్ సమస్య మొదలైనవి\uD83D\uDCCB ఏమైనా ఉంటే మాకు ఈ మెసేజ్ ద్వారా తెలియచేయండి\uD83D\uDE0A.. \n"+"**********\n"+"ఒక వేళ మీరు యాప్ ను వాడటనికి కుదరకపోతే.క్రింది నెంబర్లకు వాట్స్ యాప్ లేద ఫోన్ కాల్ ద్వారా ఆర్డర్ చేయగలరు\n"+"********\n"+"సంప్రదించండి:\n"+"7447337566\n" + "8331833648\n" + "యాప్ లింక్ :App Download Link :"+"http://play.google.com/store/apps/details?id=com.dailyneeds.vugido\n" + "Email Id:vugido@vugido.com\n" + "***********\n" + "ధన్యవాదాలు \n" + "--Team Vugido";

                    String text="***********\n" +
                            "నమస్కారం \""+dataItem.getUSERNAME().toUpperCase() +" \"గారు\uD83D\uDE4F\uD83C\uDFFD\n" +
                            " మన వూగిడో(ఆన్\u200Cలైన్ కూరగాయలు, కిరాణా మరియు పండ్ల )యాప్ ఇన్\u200Cస్టాల్ చేసిన తర్వాత మీరు కేవలం ఒక్కసారె(1) ఆర్డర్  చేశారు\n" +
                            "మీకు ఏ విధమైన సమస్యలు అనగా\n" +
                            "\n" +
                            "**కావలసిన ఉత్పత్తులు లేకపోవడం \n" +
                            "\n" +
                            "**లొకేషన్ సమస్య\n" +
                            "\n" +
                            ".... మొదలైనవి\uD83D\uDCCB ఏమైనా ఉంటే మాకు ఈ మెసేజ్ ద్వారా తెలియచేయండి\uD83D\uDE0A.. \n" +
                            "***********\n" +
                            "ఒక వేళ మీరు యాప్ ను వాడటనికి కుదరకపోతే.క్రింది నెంబర్లకు వాట్స్ యాప్ లేద ఫోన్ కాల్ ద్వారా ఆర్డర్ చేయగలరు\n" +
                            "***********\n" +
                            "సంప్రదించండి:\n" +
                            "7447337566\n" +
                            "8331833648\n" +
                            "యాప్ లింక్ :\n" +
                            "http://play.google.com/store/apps/details?id=com.dailyneeds.vugido\n" +
                            "Email Id:vugido@vugido.com\n" +
                            "***********\n" +
                            "ధన్యవాదాలు \n" +
                            "--Team Vugido";
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
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView UserName;
        Button WhatsApp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            UserName=itemView.findViewById(R.id.user_name);
            WhatsApp=itemView.findViewById(R.id.button3);
        }
    }
}
