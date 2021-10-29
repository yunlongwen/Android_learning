package com.yurnero.project.model

import android.util.Log
import com.yurnero.base.model.BaseModel
import com.yurnero.base.model.IBaseModelListener
import com.yurnero.base.model.ResponseThrowable
import com.yurnero.network.HmiNetworkApi
import com.yurnero.project.api.ProjectApiInterface
import io.reactivex.Observer

class ProjectModel(listener: IBaseModelListener<ProjectBean>) :
    BaseModel<ProjectResponse, ProjectBean>(
        listener = listener,
        isPaging = false,
        cacheKey = "key_projects"
    ) {
    override fun onTransformToViewModels(data: ProjectResponse, isCache: Boolean) {
        Log.d("wyl", "onTransformToViewModels")
        iBaseModelListener?.onLoadSuccess(model = this, data = arrayListOf(data.result))

    }

    override fun onFailure(e: ResponseThrowable) {

    }

    override fun load() {
        HmiNetworkApi.getService(ProjectApiInterface::class.java)
            .getProjects(ProjectListParam(PageInfoRequest(0, 10)))
            .subscribe(getObserver())
    }

    private fun getObserver(): Observer<ProjectResponse> {
        return ProjectObserver(this, this)
    }
}