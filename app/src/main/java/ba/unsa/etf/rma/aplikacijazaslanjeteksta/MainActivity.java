package ba.unsa.etf.rma.aplikacijazaslanjeteksta;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private final String ACTION_SEND = "ŠALJI";
    private TextView tvSalji;
    private EditText editSalji;
    private Button dugmePosalji;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSalji = (TextView) findViewById(R.id.porukaSlanja);
        editSalji = (EditText) findViewById(R.id.tekstSlanja);
        dugmePosalji = (Button) findViewById(R.id.dugmeZaSlanje);


        dugmePosalji.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // I - pozivanje podrazumijevanog konstruktota Intenta
                Intent otvoriAplikacijuZaSlanjeTeksta = new Intent();
                // II - postavljanje akcija koja se treba obaviti
                otvoriAplikacijuZaSlanjeTeksta.setAction(ACTION_SEND);

                // III - postavljanje podataka koji se trebaju obaviti
                if(editSalji.getText().toString().trim().length() != 0)
                    otvoriAplikacijuZaSlanjeTeksta.putExtra(Intent.EXTRA_TEXT, editSalji.getText().toString() );
                else
                {
                    editSalji.setText("nije nista upisano");
                    otvoriAplikacijuZaSlanjeTeksta.putExtra(Intent.EXTRA_TEXT, editSalji.getText().toString() );
                }

                // IV - postavljanje tipa intenta
                otvoriAplikacijuZaSlanjeTeksta.setType("text/plain");

                // V - provjera da li postoji aplikacija koja moze odgovoriti na zahtjev
                if(otvoriAplikacijuZaSlanjeTeksta.resolveActivity(getPackageManager()) != null)
                {
                    startActivity(otvoriAplikacijuZaSlanjeTeksta);
                }
                else
                {
                    AlertDialog upozori = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Nema odgovarajuce aplikacije")
                            .setMessage("Ne postoji aplikacija koja moze otvoriti ovaj sadrzaj")

                            // Specifying a listener allows you to take an action before dismissing the dialog.
                            // The dialog is automatically dismissed when a dialog button is clicked.
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    // Continue with delete operation
                                }
                            })

                            // A null listener allows the button to dismiss the dialog and take no further action.
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
            }
        });


    }
}
