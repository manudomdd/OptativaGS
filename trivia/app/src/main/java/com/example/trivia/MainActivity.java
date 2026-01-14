package com.example.trivia; // Tu paquete

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trivia.api.RetrofitClient;
import com.example.trivia.api.TriviaApiService;
import com.example.trivia.models.Question;
import com.example.trivia.models.TriviaResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TriviaApiService service = RetrofitClient.getService();

        // 2. Preparamos la llamada: 5 preguntas, cualquier categoría, dificultad media
        Call<TriviaResponse> call = service.getQuestions(5, null, "medium", "multiple");

        // 3. Ejecutamos la llamada en segundo plano (enqueue)
        call.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(Call<TriviaResponse> call, Response<TriviaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // ¡ÉXITO! Hemos recibido datos
                    List<Question> preguntas = response.body().getResults();

                    // Imprimimos la primera pregunta en la consola para verificar
                    for (Question q : preguntas) {
                        Log.d("API_TEST", "Pregunta recibida: " + q.getQuestionText());
                        Log.d("API_TEST", "Respuesta correcta: " + q.getCorrectAnswer());
                    }
                } else {
                    Log.e("API_TEST", "Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TriviaResponse> call, Throwable t) {
                // Error de conexión (sin internet, url mal, etc)
                Log.e("API_TEST", "Fallo la conexión: " + t.getMessage());
            }
        });
        // --- FIN DE LA PRUEBA ---
    }
}