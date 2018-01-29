package br.com.htech.firula8.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;


public class TestarConexao {

	public static AlertDialog.Builder alertDialogBuilder;
	public static boolean dialogShow = false;
	/**
	 * Função que verifica estatus da conexão do dispositivo
	 * @param contexto
	 * @param t
	 * @param tag
	 * @return
	 */
	public static boolean VerificaConexao(Activity contexto, TentarNovamente t, String tag){
	      
	    ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Activity.CONNECTIVITY_SERVICE);//Pego a conectividade do contexto o qual o metodo foi chamado
	      
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();//Crio o objeto netInfo que recebe as informacoes da NEtwork
	      
	      
		if ( (netInfo != null) && (netInfo.isConnectedOrConnecting()) && (netInfo.isAvailable()) ) //Se o objeto for nulo ou nao tem conectividade retorna false
			return true;
		else {
			calldialog(contexto,t,tag);
			return false;
		}
	      
	}

	/**
	 * Dialog que informa erro de conexão
	 * @param c
	 * @param t
	 * @param tag
	 */
	public static void calldialog(final Activity c, final TentarNovamente t, final String tag) {


        alertDialogBuilder = new AlertDialog.Builder(c);
        alertDialogBuilder.setTitle("");
        alertDialogBuilder.setMessage("Ops, ocorreu algum erro na comunicação, verifique sua conexão e tente novamente.");
        alertDialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                dialogShow  =false;
            }
        });

        alertDialogBuilder.setPositiveButton("TENTAR NOVAMENTE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                t.tentarNovamente(tag);
            }
        });


		try{

			if (!dialogShow) {
                dialogShow = true;
                alertDialogBuilder.show();
            }

		}catch (Exception e){

		}

	}



    public interface TentarNovamente {
		void tentarNovamente(String tag);
	}

}
