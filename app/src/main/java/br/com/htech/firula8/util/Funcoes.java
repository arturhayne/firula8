package br.com.htech.firula8.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by artur.oliveira on 28/12/2017.
 */

public class Funcoes {


    /**
     * Criando um alerta generico
     * @param context
     * @param titulo
     * @param mensagem
     */
    public static void alertBuilder(Context context, String titulo, String mensagem){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_NoActionBar_MinWidth);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }

    public static String getFormatedDate(String data){

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date date = format.parse(data);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            // use UTC as timezone
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            return sdf.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "00/00/0000";

    }
}
