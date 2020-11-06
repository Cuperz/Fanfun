package com.example.fanfun.model

class Model {
    class Pending(var reason: String? = null, var name: String? = null, var time: String? = null)
    class Sketch(var reason: String? = null, name: String? = null, var time: String? = null)
    class Sent(var reason: String? = null, name: String? = null, var time: String? = null)
}