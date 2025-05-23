package za.co.varsitycollege.st10489659_assignment2

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {

    //Questions
    val questions: Array<String>
        get() = arrayOf(
            "Bananas grow on trees",
            "The word \"bookkeeper\" has three consecutive double letters",
            " The Great Fire of London killed thousands of people",
            "“Uncopyrightable” is the longest English word without a repeating letter",
            "Humans glow in the dark, but it's invisible to the naked eye"
        )

    val answers = booleanArrayOf(false, true, false, true, true)

    var currentIndex = 0
    var score = 0


    private lateinit var textviewQuestion: TextView
    private lateinit var feedbackTextView: TextView
    private lateinit var falseButton: Button
    private lateinit var trueButton: Button
    private lateinit var nextButton: Button

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        //Initialise UI components
        textviewQuestion = findViewById<TextView>(R.id.textviewQuestions)
        falseButton = findViewById<Button>(R.id.falseButton)
        trueButton = findViewById<Button>(R.id.trueButton)
        nextButton = findViewById<Button>(R.id.nextButton)
        feedbackTextView = findViewById<TextView>(R.id.feedbackTextView)

        // Load the current question
        @SuppressLint("SetTextI18n")
        fun updateQuestion() {
            textviewQuestion.text = questions[currentIndex]
            feedbackTextView.text = ""
        }

        // Check user answer and more to next question or result
        @SuppressLint("SetTextI18n")
        fun checkAnswer(userAnswer: Boolean) {
            val correct = answers[currentIndex]
            if (userAnswer == correct) {
                feedbackTextView.text = "Correct, well done!"
                score++
            } else {
                feedbackTextView.text = "Incorrect, try again next time!"
            }

        // Show the first question
        updateQuestion()


        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        nextButton.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                updateQuestion()
            } else {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL", questions.size)
                startActivity(intent)
                finish()
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
                }
            }
        }
    }
}

