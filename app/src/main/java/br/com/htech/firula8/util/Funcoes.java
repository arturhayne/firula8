package br.com.htech.firula8.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;

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
}
