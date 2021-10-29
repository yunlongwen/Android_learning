package com.yurnero.project.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.yurnero.base.model.BaseModel
import com.yurnero.base.model.IBaseModelListener
import com.yurnero.base.viewmodel.BaseViewModel
import com.yurnero.project.model.ProjectBean
import com.yurnero.project.model.ProjectModel

class ProjectViewModel(savedStateHandle: SavedStateHandle) :
    BaseViewModel<ProjectBean>(savedStateHandle) {
    override fun createModel(savedStateHandle: SavedStateHandle): BaseModel<*, ProjectBean> {
        return ProjectModel(this as IBaseModelListener<ProjectBean>)
    }
}