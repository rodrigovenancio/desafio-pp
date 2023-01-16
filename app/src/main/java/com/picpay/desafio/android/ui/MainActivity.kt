package com.picpay.desafio.android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.adapter.ContactListAdapter
import com.picpay.desafio.android.model.Contact
import com.picpay.desafio.android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var contactListAdapter: ContactListAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initMainViewModel()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getUserList().observe(this) {
            contactListAdapter.users = it
        }

    }

    private fun initViewModel() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            contactListAdapter = ContactListAdapter()
            adapter = contactListAdapter
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getUserList().observe(this, Observer<List<Contact>> {
            contactListAdapter.users = it
            contactListAdapter.notifyDataSetChanged()
        })

        viewModel.retrieveUserListFromAPI()
    }
}