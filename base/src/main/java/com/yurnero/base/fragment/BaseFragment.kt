package com.yurnero.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.yurnero.base.viewmodel.BaseViewModel
import com.yurnero.base.viewmodel.ViewStatus

abstract class BaseFragment<VIEW : ViewDataBinding, VIEW_MODEL : BaseViewModel<*>> : Fragment(),
    Observer<Any> {
    protected lateinit var viewDataBinding: VIEW

    protected lateinit var viewModel: BaseViewModel<*>

    override fun onChanged(t: Any) {
        if (t is ViewStatus) {

        } else if (t is ArrayList<*>) {
            if (t.size > 0)
                bindData(t)
        } else if (t is String) {
            if (t.isNotBlank())
                Toast.makeText(activity, t, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
        viewModel.resultData.observe(viewLifecycleOwner, this)
        viewModel.errorMessage.observe(viewLifecycleOwner, this)
        viewModel.status.observe(viewLifecycleOwner, this)
    }

    abstract fun getLayoutId(): Int

    abstract fun createViewModel(): BaseViewModel<*>

    abstract fun bindData(data: ArrayList<*>)
}