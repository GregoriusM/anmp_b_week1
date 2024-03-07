package com.ubaya.anmp_week1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.anmp_week1.databinding.FragmentGameBinding
import com.ubaya.anmp_week1.databinding.FragmentMainBinding
import kotlin.random.Random

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private var score = 0
    private var randomNum1 = 0
    private var randomNum2 = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateQuestion()

        if(arguments != null) {
            val name = GameFragmentArgs.fromBundle(requireArguments()).playerName
            binding.txtTurn.text = "$name's turn"
        }

        binding.btnSubmit.setOnClickListener {

            val answer = binding.txtAnswer.text.toString().toIntOrNull()

            if(answer != null){
                val correctAnswer = randomNum1 + randomNum2

                if(answer == correctAnswer){
                    score++
                    binding.txtAnswer.text.clear()
                    generateQuestion()
                }else{
                    val action = GameFragmentDirections.actionResultFragment(score)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }
    }
    private fun generateQuestion(){
        randomNum1 = Random.nextInt(1,11)
        randomNum2 = Random.nextInt(1,11)

        binding.txtNum1.text = randomNum1.toString()
        binding.txtNum2.text = randomNum2.toString()
    }
}