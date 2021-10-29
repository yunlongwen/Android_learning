package com.yurnero.project.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.yurnero.base.fragment.BaseFragment
import com.yurnero.project.R
import com.yurnero.project.databinding.FragmentProjectBinding
import com.yurnero.project.model.ProjectBean
import com.yurnero.project.viewmodel.ProjectViewModel

class ProjectFragment : BaseFragment<FragmentProjectBinding, ProjectViewModel>() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_project
    }

    override fun createViewModel(): ProjectViewModel {
        return ViewModelProvider(
            requireActivity(),
            SavedStateViewModelFactory(requireActivity().application, requireActivity(), arguments)
        ).get(ProjectViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fresh()
    }

    override fun bindData(data: ArrayList<*>) {
        val result = data as ArrayList<ProjectBean>
        viewDataBinding.projectName.text = result[0].data[0].name
    }
}