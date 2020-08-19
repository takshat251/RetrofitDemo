package com.example.retrofitdemo
import org.koin.android.ext.android.get
import com.example.retrofitdemo.MainViewModel
import com.example.retrofitdemo.Student
import com.example.retrofitdemo.SchoolCourse
import com.example.retrofitdemo.Friend
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module{
    single{SchoolCourse()}
    factory{Friend()}
    factory { Student(get(),get()) }
}
val viewModelModule:Module = module{
    viewModel{MainViewModel(get(),get())}
}
