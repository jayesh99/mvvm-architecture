package com.demoapp.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.demoapp.R
import com.demoapp.databinding.PageFragmentBinding
import com.demoapp.di.Injectable
import com.demoapp.ui.base.BaseFragment
import com.demoapp.ui.home.adapter.UserAdapter
import com.demoapp.ui.home.viewmodel.UserViewModel
import com.demoapp.utills.Logger
import javax.inject.Inject

class UserFragment : BaseFragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val listViewModel: UserViewModel by viewModels {
        viewModelFactory
    }


    override fun getBaseViewModel() = listViewModel


    lateinit var mBinding: PageFragmentBinding

    val adapter = UserAdapter(onClick = { id, user ->

    })


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = PageFragmentBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding.viewModel = listViewModel
        mBinding.adapter = adapter
        setToolBarModeMain(getString(R.string.user_list))
        listViewModel.getAllUserList().observe(viewLifecycleOwner, Observer {
            Logger.log(">>>>" + it.size)
                adapter.update(it)
        })
    }


}