package com.yurnero.login.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.yurnero.base.fragment.BaseFragment
import com.yurnero.login.R
import com.yurnero.login.databinding.FragmentLoginBinding
import com.yurnero.login.model.LoginData
import com.yurnero.login.viewmodel.LoginViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, requireActivity(), arguments)
        ).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fresh()
    }

    override fun onDataBack(data: ArrayList<*>) {
        val loginData = data as ArrayList<LoginData>
        (viewDataBinding as FragmentLoginBinding).loginData = loginData[0]

    }

}