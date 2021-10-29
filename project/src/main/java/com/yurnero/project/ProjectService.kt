package com.yurnero.project

import androidx.fragment.app.Fragment
import com.google.auto.service.AutoService
import com.yurnero.common.autoservice.IProjectService
import com.yurnero.project.view.ProjectFragment

@AutoService(IProjectService::class)
open class ProjectService : IProjectService {
    override fun getProjectFragment(): Fragment {
        return ProjectFragment()
    }
}