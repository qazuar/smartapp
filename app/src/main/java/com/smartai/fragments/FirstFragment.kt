package com.smartai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smartai.smartAI.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_random).setOnClickListener {
            val textviewCount = view.findViewById<TextView>(R.id.textview_count_text)
            val textCount = textviewCount.text.toString().toInt()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(textCount)
            findNavController().navigate(action)
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.button_toast).setOnClickListener {
            val toastMsg: Toast = Toast.makeText(
                context,
                getResources().getString(R.string.message_toast),
                Toast.LENGTH_SHORT
            )
            toastMsg.show()
        }

        view.findViewById<Button>(R.id.button_count).setOnClickListener {
            countMe(view)
        }

        view.findViewById<TextView>(R.id.textview_test).setText("Learning Course")
    }

    private fun countMe(view: View) {
        val counterView = view.findViewById<TextView>(R.id.textview_count_text)
        val numberStr = counterView.text.toString()

        var currentCount = numberStr.toInt()
        currentCount ++

        counterView.text = currentCount.toString()
    }
}