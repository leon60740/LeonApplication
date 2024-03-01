package me.ikvarxt.leonapplicaion.j2v8demo

interface ScriptTester : IV8MockObject {

  val script: String

  fun getMainArgs(): Array<String>

  fun attach(manager: V8Manager, name: String) {
    manager.registerNativeObject(this, name)
    manager.loadScript(script)
    manager.executeScript("main", *getMainArgs())
  }
}