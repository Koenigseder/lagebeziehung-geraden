package de.kojyx.lagebeziehunggeraden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**         og1           rg1
       g: x = ( og2 ) + a * ( rg2 )
                og3           rg3

             oh1           rh1
    h: x = ( oh2 ) + b * ( rh2 )
             oh3           rh3

    Kreuzprodukt der Richtungsvektoren von g und h
          k1       rg2 * rh3 - rg3 * rh2
    k = ( k2 ) = ( rg3 * rh1 - rg1 * rh3 )
          k3       rg1 * rh2 - rg2 * rh1

    Verbindungsvektor der Aufpunkte von g und h
           vb1       og1 - oh1
    vb = ( vb2 ) = ( og2 - oh2)
           vb3       og3 - oh3

    Spatprodukt
    sp = (rg x rh) ° vb = k ° vb = k1 * vb1 + k2 * vb2 + k3 * vb3
    */

    // Deklaration TextView
    private TextView textView;

    // Deklaration Button
    private Button button, button_del;

    // Deklaration EditText
    private EditText et_og1, et_og2, et_og3, et_oh1, et_oh2, et_oh3;
    private EditText et_rg1, et_rg2, et_rg3, et_rh1, et_rh2, et_rh3;

    // Deklaration double
    // Ortvektorwerte von g
    private double og1;
    private double og2;
    private double og3;

    // Richtungvektorwerte von g
    private double rg1;
    private double rg2;
    private double rg3;

    // Ortvektorwerte von h
    private double oh1;
    private double oh2;
    private double oh3;

    // Richtungvektorwerte von g
    private double rh1;
    private double rh2;
    private double rh3;

    // Kreuzproduktwerte und Verbindungsvektorwerte
    private double k1, k2, k3;
    private double vb1, vb2, vb3;

    // Wert Spatprodukt
    private double sp;

    // Variable zur Überprüfung der Richtungsvektoren
    private double m1, m2, m3;

    // Variable zur Überprüfung des Aufpunktes g in h
    private double y1, y2, y3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisierung TextView
        textView = (TextView) findViewById(R.id.textView);

        // Initialisierung Button
        button = (Button) findViewById(R.id.button);
        button_del = (Button) findViewById(R.id.button_del);

        // Initialisierung EditText
        et_og1 = (EditText) findViewById(R.id.et_og1);
        et_og2 = (EditText) findViewById(R.id.et_og2);
        et_og3 = (EditText) findViewById(R.id.et_og3);

        et_oh1 = (EditText) findViewById(R.id.et_oh1);
        et_oh2 = (EditText) findViewById(R.id.et_oh2);
        et_oh3 = (EditText) findViewById(R.id.et_oh3);

        et_rg1 = (EditText) findViewById(R.id.et_rg1);
        et_rg2 = (EditText) findViewById(R.id.et_rg2);
        et_rg3 = (EditText) findViewById(R.id.et_rg3);

        et_rh1 = (EditText) findViewById(R.id.et_rh1);
        et_rh2 = (EditText) findViewById(R.id.et_rh2);
        et_rh3 = (EditText) findViewById(R.id.et_rh3);

        // Zuordnen eines OnClickListeners an Button
        button.setOnClickListener(this);
        button_del.setOnClickListener(this);
    }

    // OnClick-Methode
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                // Beim Betätigen wird überprüft, ob alle EditText gefüllt sind
                // → nicht gefüllt: Liefern eines Errors
                // → gefüllt: Werte in double umwandeln und richtungsvektor-Methode aufrufen
                if (TextUtils.isEmpty(et_og1.getText().toString())) {
                    et_og1.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_og2.getText().toString())) {
                    et_og2.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_og3.getText().toString())) {
                    et_og3.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_oh1.getText().toString())) {
                    et_oh1.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_oh2.getText().toString())) {
                    et_oh2.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_oh3.getText().toString())) {
                    et_oh3.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_rg1.getText().toString())) {
                    et_rg1.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_rg2.getText().toString())) {
                    et_rg2.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_rg3.getText().toString())) {
                    et_rg3.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_rh1.getText().toString())) {
                    et_rh1.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_rh2.getText().toString())) {
                    et_rh2.setError("Das Feld darf nicht leer bleiben!");
                } else if (TextUtils.isEmpty(et_rh3.getText().toString())) {
                    et_rh3.setError("Das Feld darf nicht leer bleiben!");
                } else {
                    og1 = Double.parseDouble(et_og1.getText().toString());
                    og2 = Double.parseDouble(et_og2.getText().toString());
                    og3 = Double.parseDouble(et_og3.getText().toString());

                    oh1 = Double.parseDouble(et_oh1.getText().toString());
                    oh2 = Double.parseDouble(et_oh2.getText().toString());
                    oh3 = Double.parseDouble(et_oh3.getText().toString());

                    rg1 = Double.parseDouble(et_rg1.getText().toString());
                    rg2 = Double.parseDouble(et_rg2.getText().toString());
                    rg3 = Double.parseDouble(et_rg3.getText().toString());

                    rh1 = Double.parseDouble(et_rh1.getText().toString());
                    rh2 = Double.parseDouble(et_rh2.getText().toString());
                    rh3 = Double.parseDouble(et_rh3.getText().toString());

                    if ((rg1 == 0 && rg2 == 0 && rg3 == 0) || (rh1 == 0 && rh2 == 0 && rh3 == 0)) {
                        textView.setText("Der Richtungsvektor darf nicht 0 sein!");
                    } else {
                        richtungsvektor();
                    }
                }
                break;

            case R.id.button_del:
                // Beim Betätigen wird jeglicher Inhalt der EditText und TextView gelöscht
                et_og1.setText("");
                et_og2.setText("");
                et_og3.setText("");
                et_oh1.setText("");
                et_oh2.setText("");
                et_oh3.setText("");
                et_rg1.setText("");
                et_rg2.setText("");
                et_rg3.setText("");
                et_rh1.setText("");
                et_rh2.setText("");
                et_rh3.setText("");

                textView.setText("");
                break;
        }
    }

    // Methode zur Überprüfung der Richtungsvektoren
    private void richtungsvektor() {
        // rg = m * rh      / :rh
        // m = rg : rh

        m1 = rg1 / rh1;
        m2 = rg2 / rh2;
        m3 = rg3 / rh3;

        // Werte in Arrays setzen (mit Überlauf)
        double[] rg = {rg1, rg2, rg3, rg1};
        double[] rh = {rh1, rh2, rh3, rh1};
        double[] m = {m1, m2, m3, m1, m2};

        // Check, ob an einer Stelle unendlich Werte entstehen
        // → Diese Werte mit gültige Werte ersetzen
        for (int i = 0; i <= 2; i++) {
            if (rg[i] == 0 && rh[i] == 0 && rg[i + 1] == 0 && rh[i + 1] == 0) {
                m[i] = m[i + 2];
                m[i + 1] = m[i + 2];
            } else if (rg[i] == 0 && rh[i] == 0) {
                m[i] = m[i + 1];
            }
        }

        // Werte übertragen
        m1 = m[0];
        m2 = m[1];
        m3 = m[2];

        // Check, ob an einer Stelle keine Werte entstehen → windschief/schneidend
        if ((rg1 != 0 && rh1 == 0) || (rg2 != 0 && rh2 == 0) || (rg3 != 0 && rh3 == 0)) {
            nichtParallel();
        } else {
            // Wenn Ergebnis überall gleich → Geraden sind parallel
            // Sonst → Geraden sind nicht parallel
            if (m1 == m2 && m2 == m3) {
                parallel();
            } else {
                nichtParallel();
            }
        }
    }

    // Methode zur Überprüfung, ob Geraden identisch oder echt parallel sind
    private void parallel() {
        // Aufpunkt von g in h einsetzen
        // og = oh + y * rh     / -oh
        // og - oh = y * rh     / :rh
        // y = (og - oh) : rh

        y1 = (og1 - oh1) / rh1;
        y2 = (og2 - oh2) / rh2;
        y3 = (og3 - oh3) / rh3;

        // Werte in Arrays setzen (mit Überlauf)
        double[] og = {og1, og2, og3, og1};
        double[] oh = {oh1, oh2, oh3, oh1};
        double[] rh = {rh1, rh2, rh3, rh1};
        double[] y = {y1, y2, y3, y1, y2};

        // Check, ob an einer Stelle unendlich Werte entstehen
        // → Diese Werte mit gültige Werte ersetzen
        for (int i = 0; i <= 2; i++) {
            if ((og[i] - oh[i]) == 0 && rh[i] == 0 && (og[i + 1] - oh[i + 1]) == 0 && rh[i + 1] == 0) {
                y[i] = y[i + 2];
                y[i + 1] = y[i + 2];
            } else if ((og[i] - oh[i]) == 0 && rh[i] == 0) {
                y[i] = y[i + 1];
            }
        }

        // Werte übertragen
        y1 = y[0];
        y2 = y[1];
        y3 = y[2];

        // Check, ob an einer Stelle keine Werte entstehen → echt parallel
        if ((og1 != oh1 && rh1 == 0) || (og2 != oh2 && rh2 == 0) || (og3 != oh3 && rg3 == 0)) {
            textView.setText("echt parallel");
        } else {
            // Wenn Ergebnis überall gleich → Geraden sind identisch
            // Sonst → Geraden sind echt parallel
            if (y1 == y2 && y2 == y3) {
                textView.setText("unecht parallel / identisch");
            } else {
                textView.setText("echt parallel");
            }
        }
    }

    // Methode zur Überprüfung, ob Geraden windschief oder schneidend sind
    private void nichtParallel() {
        // Spatprodukt bilden (zuerst Kreuzprodukt, dann Skalarprodukt)

        vb1 = og1 - oh1;
        vb2 = og2 - oh2;
        vb3 = og3 - oh3;

        k1 = rg2 * rh3 - rg3 * rh2;
        k2 = rg3 * rh1 - rg1 * rh3;
        k3 = rg1 * rh2 - rg2 * rh1;

        sp = k1 * vb1 + k2 * vb2 + k3 * vb3;

        // Wenn Spatprodukt ungleich 0 → Geraden sind windschief
        // Sonst → schneidend
        if (sp != 0) {
            textView.setText("windschief");
        } else {
            textView.setText("schneidend");
        }
    }
}