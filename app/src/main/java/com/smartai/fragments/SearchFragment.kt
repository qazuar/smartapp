package com.smartai.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.smartai.model.Connector
import com.smartai.model.Request
import com.smartai.smartAI.R
import com.smartai.smartAI.databinding.FragmentSearchBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val ui by userInterface(FragmentSearchBinding::bind)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ui.searchSubmit.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val connector = Connector("kundecenter", "kundecenter")
                val request = Request.newInstance()

                request.server = "https://google.com/"

                connector.get(request)

                request.response?.let {
                    //ui.editSearchOrigin.setText("HAH")
                } ?: run {
                    println("Went bad")
                }

                request.exception?.let {
                    println(it.message)
                }
            }

        }

    }

}