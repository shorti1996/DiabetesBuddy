package com.wojciszke.diabetesbuddy.di.component

import com.wojciszke.diabetesbuddy.di.module.DbModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DbModule::class])
@Singleton
interface DbComponent