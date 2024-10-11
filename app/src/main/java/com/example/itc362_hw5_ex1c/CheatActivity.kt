package com.example.itc362_hw5_ex1c

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.itc362_hw5_ex1c.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.example.itc362_hw5_ex1b.answer_is_true"
const val EXTRA_ANSWER_SHOWN = "com.example.itc362_hw5_ex1b.answer_shown"

class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private val cheatViewModel: CheatViewModel by viewModels()
    private var answerIsTrue = false
    private var answerText = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        //Moved outside showAnswerButton.setOnClickListener for accessibility
        answerText = when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
        }

        binding.showAnswerButton.setOnClickListener {
            binding.answerTextView.setText(answerText)
            cheatViewModel.isAnswerShown = true
            setAnswerShownResult(cheatViewModel.isAnswerShown)
        }

        //Check  from saved state if answer is shown upon activity creation
        if (cheatViewModel.isAnswerShown) {
            //If answer was shown
            binding.answerTextView.setText(answerText) //set answer text in activity_cheat
            setAnswerShownResult(cheatViewModel.isAnswerShown) //set the EXTRA_ANSWER_SHOWN to the saved state
        }
    }

    private fun setAnswerShownResult(isAnswerShown:Boolean) {
        val data = Intent().apply {

            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)

    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}