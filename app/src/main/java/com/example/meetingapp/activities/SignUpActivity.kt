package com.example.meetingapp.activities

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.TransitionManager
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.meetingapp.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        TransitionManager.beginDelayedTransition(binding.constraintLayout)
        binding.backBtn.setOnClickListener { onBackPressed() }
        binding.btnSignUp.setOnClickListener {
            Toast.makeText(this,"Sign Up",Toast.LENGTH_SHORT).show()


        var layoutParam = binding.btnSignUp.layoutParams as ConstraintLayout.LayoutParams
        var start = layoutParam.circleAngle
        var end = layoutParam.circleAngle + 180

        val ani = ValueAnimator.ofFloat(start,end)
        ani.addUpdateListener {
            val anim = ValueAnimator.ofFloat(start, end)
            anim.addUpdateListener { valueAnimator ->

                //3
                val animatedValue = valueAnimator.animatedValue as Float
                val layoutParams = binding.btnSignUp.layoutParams as ConstraintLayout.LayoutParams
                layoutParams.circleAngle = animatedValue
                binding.btnSignUp.layoutParams = layoutParams

                //4
                binding.btnSignUp.rotation = (animatedValue % 360 - 270)
        }
            anim.duration =2000

            //6
            anim.interpolator = LinearInterpolator()
            anim.start()
        }
    }
}}