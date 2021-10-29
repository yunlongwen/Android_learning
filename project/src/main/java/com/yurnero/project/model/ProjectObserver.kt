package com.yurnero.project.model

import com.yurnero.base.model.BaseDataObserver
import com.yurnero.network.observer.BaseObserver

class ProjectObserver(dataObserver: BaseDataObserver<ProjectResponse>, model: ProjectModel) :
    BaseObserver<ProjectResponse>(dataObserver, model) {
    override fun onNext(t: ProjectResponse) {
        dataObserver?.onTransformToViewModels(t,false)
    }

    override fun onComplete() {
    }
}