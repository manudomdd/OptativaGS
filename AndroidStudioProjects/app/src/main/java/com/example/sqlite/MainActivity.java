package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView result;
    private EditText textoCorreo;
    private EditText textoPassword;
    private Button btnRegistro;
    private Button btnMostrar;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
        initFunctions();
    }

    private void initComponents() {
        result = findViewById(R.id.textView3);
        textoCorreo = findViewById(R.id.editTextTextPersonName);
        textoPassword = findViewById(R.id.editTextTextPersonName2);
        btnRegistro = findViewById(R.id.button);
        btnMostrar = findViewById(R.id.button2);
        btnLogin = findViewById(R.id.button3);
    }

    private void initFunctions() {
        registrar();
        mostrarRegistros();
        login();
    }

    private void registrar() {
        btnRegistro.setOnClickListener(v -> {
            String correo = textoCorreo.getText().toString();
            String pass = textoPassword.getText().toString();

            try {
                DBHelper dbHelper = new DBHelper(this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(DBHelper.COLUMN_CORREO, correo);
                values.put(DBHelper.COLUMN_PASSWORD, pass);

                long result = db.insert(DBHelper.TABLE_USUARIO, null, values);

                if (result == -1) {
                    Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                }

                db.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void mostrarRegistros() {
        btnMostrar.setOnClickListener(v -> {
            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor miCursor = null;

            try {
                miCursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_USUARIO, null);
                if (miCursor.moveToFirst()) {
                    do {
                        String correo = miCursor.getString(0);
                        String pass = miCursor.getString(1);
                        result.append(correo + " - " + pass + "\n");
                    } while (miCursor.moveToNext());
                } else {
                    Toast.makeText(this, "No hay registros en la base de datos", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (miCursor != null) miCursor.close();
                if (db != null) db.close();
            }
        });
    }

    private void login() {
        btnLogin.setOnClickListener(v -> {
            String correo = textoCorreo.getText().toString();
            String pass = textoPassword.getText().toString();
            String[] resultado = {correo, pass};

            DBHelper dbHelper = new DBHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Cursor miCursor = null;

            try {
                miCursor = db.rawQuery("SELECT * FROM " + DBHelper.TABLE_USUARIO + " WHERE " + DBHelper.COLUMN_CORREO + " =? AND " + DBHelper.COLUMN_PASSWORD + " =? ", resultado);
                if (miCursor.moveToFirst()) {
                    Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Login incorrecto", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                db.close();
                miCursor.close();
            }
        });
    }
}