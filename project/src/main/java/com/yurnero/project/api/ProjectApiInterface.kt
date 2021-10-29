package com.yurnero.project.api

import com.yurnero.project.model.ProjectListParam
import com.yurnero.project.model.ProjectResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface ProjectApiInterface {
    @POST("/1.1/functions/get_projects")
    fun getProjects(@Body loginParam: ProjectListParam): Observable<ProjectResponse>
}