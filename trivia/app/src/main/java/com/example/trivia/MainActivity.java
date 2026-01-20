package com.example.trivia;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;

import com.example.trivia.api.RetrofitClient;
import com.example.trivia.api.TriviaApiService;
import com.example.trivia.models.Question;
import com.example.trivia.models.TriviaResponse;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tvQuestion, tvScore;
    private Button btnOption1, btnOption2, btnOption3, btnOption4, btnNext;
    private ProgressBar progressBar;
    private Group gameGroup;
    private MaterialCardView cardScoreHeader;
    private List<Button> optionButtons;

    private Group gameOverGroup;
    private TextView tvFinalScore;
    private Button btnRestart;

    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private boolean isAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        startNewGame();

        btnNext.setOnClickListener(v -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questionList.size()) {
                showQuestion();
            } else {
                showGameOverScreen();
            }
        });

        btnRestart.setOnClickListener(v -> startNewGame());
    }

    private void initViews() {
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScore = findViewById(R.id.tvScore);
        cardScoreHeader = findViewById(R.id.cardScoreHeader);
        btnOption1 = findViewById(R.id.btnOption1);
        btnOption2 = findViewById(R.id.btnOption2);
        btnOption3 = findViewById(R.id.btnOption3);
        btnOption4 = findViewById(R.id.btnOption4);
        btnNext = findViewById(R.id.btnNext);
        progressBar = findViewById(R.id.progressBar);
        gameGroup = findViewById(R.id.gameGroup);

        gameOverGroup = findViewById(R.id.gameOverGroup);
        tvFinalScore = findViewById(R.id.tvFinalScore);
        btnRestart = findViewById(R.id.btnRestart);

        optionButtons = new ArrayList<>();
        optionButtons.add(btnOption1);
        optionButtons.add(btnOption2);
        optionButtons.add(btnOption3);
        optionButtons.add(btnOption4);
    }

    private void startNewGame() {
        score = 0;
        currentQuestionIndex = 0;
        tvScore.setText("Puntos: 0");

        gameOverGroup.setVisibility(View.GONE);
        gameGroup.setVisibility(View.GONE);
        cardScoreHeader.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        fetchQuestions();
    }

    private void fetchQuestions() {
        TriviaApiService service = RetrofitClient.getService();
        Call<TriviaResponse> call = service.getQuestions(10, null, "medium", "multiple");

        call.enqueue(new Callback<TriviaResponse>() {
            @Override
            public void onResponse(Call<TriviaResponse> call, Response<TriviaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    questionList = response.body().getResults();
                    if (!questionList.isEmpty()) {
                        progressBar.setVisibility(View.GONE);
                        gameGroup.setVisibility(View.VISIBLE);
                        showQuestion();
                    } else {
                        showError("La API no devolvió preguntas.");
                    }
                } else {
                    showError("Error en la respuesta: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<TriviaResponse> call, Throwable t) {
                showError("Error de conexión: " + t.getMessage());
            }
        });
    }

    private void showQuestion() {
        isAnswered = false;
        btnNext.setVisibility(View.INVISIBLE);
        resetButtonColors();

        Question q = questionList.get(currentQuestionIndex);

        tvQuestion.setText(Html.fromHtml(q.getQuestionText(), Html.FROM_HTML_MODE_LEGACY));

        List<String> answers = new ArrayList<>(q.getIncorrectAnswers());
        answers.add(q.getCorrectAnswer());
        Collections.shuffle(answers);

        for (int i = 0; i < optionButtons.size(); i++) {
            Button btn = optionButtons.get(i);
            if (i < answers.size()) {
                btn.setVisibility(View.VISIBLE);
                String answerText = answers.get(i);
                btn.setText(Html.fromHtml(answerText, Html.FROM_HTML_MODE_LEGACY));
                btn.setOnClickListener(v -> checkAnswer(btn, answerText, q.getCorrectAnswer()));
            } else {
                btn.setVisibility(View.GONE);
            }
        }
    }

    private void checkAnswer(Button selectedBtn, String selectedAnswer, String correctAnswer) {
        if (isAnswered) return;
        isAnswered = true;

        String decodedCorrect = Html.fromHtml(correctAnswer, Html.FROM_HTML_MODE_LEGACY).toString();
        String decodedSelected = Html.fromHtml(selectedAnswer, Html.FROM_HTML_MODE_LEGACY).toString();

        MaterialButton matBtn = (MaterialButton) selectedBtn;

        if (decodedSelected.equals(decodedCorrect)) {
            score += 10;
            tvScore.setText("Puntos: " + score);
            matBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.color_correct)));
            matBtn.setTextColor(Color.WHITE);
            matBtn.setStrokeWidth(0);
        } else {
            matBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.color_wrong)));
            matBtn.setTextColor(Color.WHITE);
            matBtn.setStrokeWidth(0);
            showCorrectButton(decodedCorrect);
        }

        btnNext.setVisibility(View.VISIBLE);
    }

    private void showCorrectButton(String correctAnswer) {
        for (Button btn : optionButtons) {
            if (btn.getText().toString().equals(correctAnswer)) {
                MaterialButton matBtn = (MaterialButton) btn;
                matBtn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.color_correct)));
                matBtn.setTextColor(Color.WHITE);
                matBtn.setStrokeWidth(0);
            }
        }
    }

    private void resetButtonColors() {
        for (Button btn : optionButtons) {
            MaterialButton matBtn = (MaterialButton) btn;
            matBtn.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            matBtn.setTextColor(Color.WHITE);
            matBtn.setStrokeColor(ColorStateList.valueOf(Color.WHITE));
            matBtn.setStrokeWidth(5);
        }
    }

    private void showGameOverScreen() {
        gameGroup.setVisibility(View.GONE);
        cardScoreHeader.setVisibility(View.GONE);

        tvFinalScore.setText(String.valueOf(score));
        gameOverGroup.setVisibility(View.VISIBLE);
    }

    private void showError(String message) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

//-------PROPUESTA FUTURA---------
//traducir la app entera a traves de la api.
//crear un modo multijugador
//posibilidad de manejo de turnos a traves de hilos