package br.ufc.qxd.componentesbasicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, PopupMenu.OnMenuItemClickListener {

    private static final String[] DEZFELINOS = {"Tigre", "Leão", "Onça Pintada", "Suçuarana", "Puma",
            "Leopardo", "Pantera Nebulosa", "Guepardo", "Caracal", "Leopardo das Neves"};

    private ToggleButton toggBtn1, toggBtn2;
    private Button btnDisp, btnFelino;
    private EditText textName;
    private AutoCompleteTextView textFelino;
    private Spinner zodiacoChn;
    private ListView listItens;
    private MediaPlayer player;


    public void Tocar(View view) {
        if (player == null) {
            player = MediaPlayer.create(MainActivity.this, R.raw.song);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    if(player != null){
                        player.release();
                        player = null;
                    }
                }
            });
        }
        player.start();
    }
    public void Parar(View view){
        if(player != null){
            player.release();
            player = null;
        }

    }
    @Override
    protected void onStop(){
        super.onStop();
        if(player != null){
            player.release();
            player = null;
        }
    }

    public String topDezFelinos(String[] felinosArr) {
        String texto = "";
        int posicao;
        for (int i = 0; i < 10; i++) {
            posicao = i + 1;
            if (posicao < 10) {
                texto = texto + 0 + posicao + " - " + felinosArr[i] + "\n";
            } else {
                texto = texto + posicao + " - " + felinosArr[i] + "\n";
            }
        }
        return texto;
    }

    public void showPopUp(View view) {
        PopupMenu popup = new PopupMenu(this, view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.pop_up_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.comedia:
                Toast.makeText(this, "Comédia selecionada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.romance:
                Toast.makeText(this, "Romance selecionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.acao:
                Toast.makeText(this, "Ação selecionada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.aventura:
                Toast.makeText(this, "Aventura selecionada", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.terror:
                Toast.makeText(this, "Terror selecionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void addListnerOnButton() {
        toggBtn1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggBtn2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisp = (Button) findViewById(R.id.btnDisplay);
        textName = (EditText) findViewById(R.id.textNome);
        btnFelino = (Button) findViewById(R.id.btnArray);
        textFelino = (AutoCompleteTextView) findViewById(R.id.autoTextView);
        zodiacoChn = (Spinner) findViewById(R.id.spinner);
        listItens = (ListView) findViewById(R.id.listaItens);

        ArrayAdapter<String> adapterDez = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, DEZFELINOS);
        textFelino.setAdapter(adapterDez);

        ArrayAdapter<CharSequence> adapterZod = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.zodiaco_chines, android.R.layout.simple_selectable_list_item);
        adapterZod.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zodiacoChn.setAdapter(adapterZod);
        zodiacoChn.setOnItemSelectedListener(MainActivity.this);

        ArrayAdapter<CharSequence> adapterItens = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.item_array, android.R.layout.simple_list_item_1);
        listItens.setAdapter(adapterItens);

        btnDisp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();
                result.append("editText : ").append(textName.getText().toString());
                result.append("\ntoggleButton1 : ").append(toggBtn1.getText());
                result.append("\ntoggleButton2 : ").append(toggBtn2.getText());

                Toast.makeText(MainActivity.this, result.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnFelino.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, topDezFelinos(DEZFELINOS),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addListnerOnButton();

        TextView textView = (TextView) findViewById(R.id.segredos);
        registerForContextMenu(textView);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.menu_secreto, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.comp_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selecionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 selecionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 selecionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem1:
                Toast.makeText(this, "Sub-Item 1 selecionado", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem2:
                Toast.makeText(this, "Sub-Item 2 selecionado", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}