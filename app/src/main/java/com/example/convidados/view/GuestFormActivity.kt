package com.example.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.convidados.viewmodel.GuestFormViewModel
import com.example.convidados.R
import com.example.convidados.constants.Constants.GUEST.GUEST_ID
import com.example.convidados.databinding.ActivityGuestFormBinding
import com.example.convidados.model.GuestModel

class GuestFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)

        binding.btSalvar.setOnClickListener(this)
        binding.present.isChecked = true

        observe()

        loadData()


    }



    private fun loadData() {
        val bundle = intent.extras
        if (bundle != null){
            guestId = bundle.getInt(GUEST_ID)
            viewModel.get(guestId)
        }
    }

    private fun observe() {
        viewModel.guest.observe(this, Observer {
            binding.etNome.setText(it.name)
            if (it.presence) {
                binding.present.isChecked = true
            } else {
                binding.absent.isChecked = true
            }
        })

        viewModel.saveGuest.observe(this, Observer {
            if(it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }

    override fun onClick(v: View) {
         if (v.id == R.id.bt_salvar){

             val name = binding.etNome.text.toString()
             val presence = binding.present.isChecked

             var model = GuestModel().apply {
                 this.id = guestId
                 this.name = name
                 this.presence = presence
             }
             viewModel.save(model)
         }
    }
}